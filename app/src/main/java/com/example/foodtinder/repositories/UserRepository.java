package com.example.foodtinder.repositories;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.foodtinder.models.UserItemModel;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserRepository {
    private final UserLiveData currentUser;
    private static UserRepository instance;
    private final Application app;
    FirebaseDatabase database;
    DatabaseReference reference;
    UserItemModel userItemModel;

    public UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
        database = FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference("users");

    }

    public static synchronized UserRepository getInstance(Application app) {
        if (instance == null)
            instance = new UserRepository(app);
        return instance;
    }


    public void addUser(UserItemModel userItemModel) {
        reference.child(getSafeCurrentUserEmail(userItemModel.getEmail())).get().addOnCompleteListener(v -> {
                    if (v.getResult().getValue() == null) {
                        reference.child(getSafeCurrentUserEmail(userItemModel.getEmail())).setValue(userItemModel);
                    }
                }
        );
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }
    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }

    public void addRecipe(String recipeId) {
        reference.child(getSafeCurrentUserEmail(currentUser.getValue().getEmail())).get().addOnCompleteListener(v -> {
            if (v.isSuccessful()) {
                userItemModel = v.getResult().getValue(UserItemModel.class);
                userItemModel.setFavouriteRecipes(userItemModel.getFavouriteRecipes());
                userItemModel.getFavouriteRecipes().add(recipeId);
                reference.child(getSafeCurrentUserEmail(userItemModel.getEmail())).setValue(userItemModel);
            }
        });
    }

    public void addFriend(String friendEmail) {
        reference.child(getSafeCurrentUserEmail(friendEmail)).get().addOnCompleteListener(v -> {
            if (v.isSuccessful()) {
                userItemModel = v.getResult().getValue(UserItemModel.class);
                userItemModel.getFriendsList().add(currentUser.getValue().getEmail());
                reference.child(getSafeCurrentUserEmail(friendEmail))
                        .child("friendsList")
                        .setValue(userItemModel.getFriendsList());
            }
        });
        reference.child(getSafeCurrentUserEmail(currentUser.getValue().getEmail())).get().addOnCompleteListener(v -> {
            if (v.isSuccessful()) {
                userItemModel = v.getResult().getValue(UserItemModel.class);
                userItemModel.getFriendsList().add(friendEmail);
                reference.child(getSafeCurrentUserEmail(currentUser.getValue().getEmail()))
                        .child("friendsList")
                        .setValue(userItemModel.getFriendsList());
            }
        });
    }




    @NonNull
    private String getSafeCurrentUserEmail(String email) {
        return email.replace('.', '-');
    }

}