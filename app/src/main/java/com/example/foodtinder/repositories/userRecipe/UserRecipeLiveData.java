package com.example.foodtinder.repositories.userRecipe;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodtinder.models.UserRecipe;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRecipeLiveData extends LiveData<ArrayList<UserRecipe>> {
    private final ValueEventListener listener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
           ArrayList<UserRecipe> recipes = new ArrayList<>();
           if(snapshot.getValue()!=null){
            for(HashMap<String,String> recipe : (ArrayList<HashMap<String,String>>) snapshot.getValue()){
                    recipes.add(new UserRecipe(recipe.get("name"),recipe.get("id")));
            }}
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