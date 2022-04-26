package com.example.foodtinder.repositories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodtinder.R;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserItemModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserRepository {
    private final UserLiveData currentUser;
    private static UserRepository instance;
    private final Application app;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser user;
    UserItemModel userItemModel;

    public UserRepository(Application app) {
        this.app = app;
        currentUser = new UserLiveData();
        database = FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/");
        reference = database.getReference("users");

    }

    public static synchronized UserRepository getInstance(Application app) {
        if(instance == null)
            instance = new UserRepository(app);
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public void addUser(UserItemModel userItemModel){
        reference.child(userItemModel.getEmail().replace('.','-')).get().addOnCompleteListener(v -> {
            if(v.getResult().getValue() == null) {
                reference.child(userItemModel.getEmail().replace('.', '-')).setValue(userItemModel);
            }
        }
        );
    }
    public void signOut() {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }
    public void addRecipe(RecipeItemModel itemModel)
    {
        reference.child(currentUser.getValue().getEmail().replace('.','-')).get().addOnCompleteListener(v -> {
            if(v.isSuccessful()) {
                userItemModel = v.getResult().getValue(UserItemModel.class);
                userItemModel.setFavouriteRecipes(userItemModel.getFavouriteRecipes());
                userItemModel.getFavouriteRecipes().add(itemModel);
                reference.child(userItemModel.getEmail().replace('.', '-')).setValue(userItemModel);
            }
        });
    }

    public void addFriend(String friendEmail)
    {
        reference.child(friendEmail.replace('.','-')).get().addOnCompleteListener(v->{
            if(v.isSuccessful()) {
                userItemModel.setFriendsList(userItemModel.getFriendsList());
                userItemModel.getFriendsList().add(v.getResult().getValue(UserItemModel.class));
                reference.child(currentUser.getValue().getEmail().replace('.', '-')).setValue(userItemModel);
            }
        });

    }

}