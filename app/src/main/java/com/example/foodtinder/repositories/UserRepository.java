package com.example.foodtinder.repositories;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserRepository {
    private final UserLiveData currentUser;
    private static UserRepository instance;
    FirebaseDatabase database;
    DatabaseReference reference;
    UserItemModel userItemModel;

    public UserRepository() {
        currentUser = new UserLiveData();
        database = FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference("users");

    }

    public static synchronized UserRepository getInstance() {
        if (instance == null)
            instance = new UserRepository();
        return instance;
    }


    public void addUser(UserItemModel userItemModel) {
        System.out.println("lol");
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

    public void signOut(Application app) {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }

    public void addRecipe(UserRecipe recipe) {
        reference.child(getSafeCurrentUserEmail(currentUser.getValue().getEmail())).get().addOnCompleteListener(v -> {
            if (v.isSuccessful()) {
                userItemModel = v.getResult().getValue(UserItemModel.class);
                userItemModel.setFavouriteRecipes(userItemModel.getFavouriteRecipes());
                userItemModel.getFavouriteRecipes().add(recipe);
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
    public String getSafeCurrentUserEmail(String email) {
        return email.replace('.', '-');
    }

}