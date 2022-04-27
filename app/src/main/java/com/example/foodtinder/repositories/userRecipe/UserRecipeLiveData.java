package com.example.foodtinder.repositories.userRecipe;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.UserRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserRecipeLiveData extends LiveData<ArrayList<UserRecipe>> {
    private final ValueEventListener listener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<UserRecipe> recipes = snapshot.getValue(ArrayList.class);
            setValue(recipes);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    DatabaseReference databaseReference;

    public UserRecipeLiveData(DatabaseReference reference) {
        databaseReference = reference;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }


}