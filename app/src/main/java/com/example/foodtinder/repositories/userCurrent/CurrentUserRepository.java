package com.example.foodtinder.repositories.userCurrent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodtinder.Config;
import com.example.foodtinder.models.UserItemModel;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CurrentUserRepository {
    private final CurrentUserLiveData currentUser;
    private static CurrentUserRepository instance;
    FirebaseDatabase database;
    DatabaseReference reference;

    public CurrentUserRepository() {
        currentUser = new CurrentUserLiveData();
        database = FirebaseDatabase.getInstance(Config.firebase_db);
        reference = database.getReference("users");

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

    public void signOut(Application app) {
        AuthUI.getInstance()
                .signOut(app.getApplicationContext());
    }

    public static synchronized CurrentUserRepository getInstance() {
        if (instance == null)
            instance = new CurrentUserRepository();
        return instance;
    }

    @NonNull
    public String getSafeCurrentUserEmail(String email) {
        return email.replace('.', '-');
    }
}
