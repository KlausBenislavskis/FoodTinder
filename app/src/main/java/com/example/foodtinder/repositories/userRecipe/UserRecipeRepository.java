package com.example.foodtinder.repositories.userRecipe;

import androidx.annotation.NonNull;

import com.example.foodtinder.Config;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.userCurrent.CurrentUserRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserRecipeRepository {
    private static UserRecipeRepository instance;
    private DatabaseReference reference;
    private UserRecipeLiveData recipes;

    private UserRecipeRepository() {
    }

    public static synchronized UserRecipeRepository getInstance() {
        if (instance == null) {
            instance = new UserRecipeRepository();
        }
        return instance;
    }

    public void init(String email) {
            CurrentUserRepository currentUserRepository = CurrentUserRepository.getInstance();
            reference = getUserRecipeDbReference(FirebaseDatabase.getInstance(Config.firebase_db)
                    .getReference("users"),currentUserRepository.getSafeCurrentUserEmail(email));
        recipes = new UserRecipeLiveData(reference);
    }
    public void init() {
            reference = getUserRecipeDbReference(FirebaseDatabase.getInstance(Config.firebase_db)
                    .getReference("users"), getCurrentUserSafeEmail());
        recipes = new UserRecipeLiveData(reference);
    }
    public void saveRecipe(UserRecipe recipe) {
        ArrayList<UserRecipe> recipesList = recipes.getValue() != null ? recipes.getValue() : new ArrayList<>();
        recipesList.add(recipe);
        reference.setValue(recipesList);
    }

    public UserRecipeLiveData getRecipes() {
        return recipes;
    }

    @NonNull
    private DatabaseReference getUserRecipeDbReference(DatabaseReference myRef, String email) {
        return myRef
                .child(email)
                .child("favouriteRecipes");
    }

    private String getCurrentUserSafeEmail() {
        CurrentUserRepository currentUserRepository = CurrentUserRepository.getInstance();
        return currentUserRepository.getSafeCurrentUserEmail(currentUserRepository.getCurrentUser().getValue().getEmail());
    }

}
