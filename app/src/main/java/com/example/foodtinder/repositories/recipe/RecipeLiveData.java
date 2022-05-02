package com.example.foodtinder.repositories.recipe;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.models.api.Recipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecipeLiveData extends LiveData<ArrayList<Recipe>> {
    private final ValueEventListener listener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<Recipe> recipes = (ArrayList<Recipe>) snapshot.getValue();
            setValue(recipes);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


}
