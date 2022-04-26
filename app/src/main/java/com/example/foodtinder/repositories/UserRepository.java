package com.example.foodtinder.repositories;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    private final UserLiveData currentUser;
    private static UserRepository instance;

    public UserRepository() {
        currentUser = new UserLiveData();
    }

    public static synchronized UserRepository getInstance() {
        if(instance == null)
            instance = new UserRepository();
        return instance;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    @SuppressLint("RestrictedApi")
    public void signOut() {
        AuthUI.getInstance().signOut(AuthUI.getApplicationContext());
    }
}