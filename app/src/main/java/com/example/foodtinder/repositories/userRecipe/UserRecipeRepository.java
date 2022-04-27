package com.example.foodtinder.repositories.userRecipe;

import androidx.annotation.NonNull;

import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.UserRepository;
import com.firebase.ui.auth.data.model.User;
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

    public void init() {
        reference = getUserRecipeDbReference(FirebaseDatabase.getInstance().getReference());
        recipes = new UserRecipeLiveData(reference);
    }

    public void saveRecipe(UserRecipe recipe) {
        ArrayList<UserRecipe> recipesList = recipes.getValue();
        if(recipesList == null) {
            recipesList = new ArrayList<>();
        }
        recipesList.add(recipe);
        reference.setValue(recipesList);
    }
    public UserRecipeLiveData getRecipes() {
        return recipes;
    }

    @NonNull
    private DatabaseReference getUserRecipeDbReference(DatabaseReference myRef) {
        return myRef
                .child(getCurrentUserSafeEmail())
                .child("favouriteRecipes");
    }

    private String getCurrentUserSafeEmail() {
        UserRepository userRepository = UserRepository.getInstance();
        return userRepository.getSafeCurrentUserEmail(userRepository.getCurrentUser().getValue().getEmail());
    }
}
