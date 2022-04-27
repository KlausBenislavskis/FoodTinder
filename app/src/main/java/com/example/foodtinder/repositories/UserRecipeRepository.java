package com.example.foodtinder.repositories;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRecipeRepository {
    private  static  UserRecipeRepository instance;
    private DatabaseReference reference;
    private UserRecipeLiveData recipes;

    private UserRecipeRepository(){}

    public static synchronized UserRecipeRepository getInstance(){
        if(instance == null){
            instance = new UserRecipeRepository();
        }
        return instance;
    }
    public void init(String userId) {
        reference = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        recipes = new UserRecipeLiveData(reference);
    }
    public UserRecipeLiveData getRecipes(){
        if(recipes == null){
            //recipes = new UserRecipeLiveData();
        }
        return recipes;
    }
}
