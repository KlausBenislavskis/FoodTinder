package com.example.foodtinder.repositories;

import androidx.lifecycle.LiveData;

import com.example.foodtinder.models.api.Recipe;
import com.google.firebase.database.DatabaseReference;

public class UserRecipeLiveData extends LiveData<Recipe> {
    public UserRecipeLiveData(DatabaseReference reference) {
    }
}
