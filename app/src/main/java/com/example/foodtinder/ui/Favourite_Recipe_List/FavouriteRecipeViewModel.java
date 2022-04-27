package com.example.foodtinder.ui.Favourite_Recipe_List;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.foodtinder.repositories.userRecipe.UserRecipeLiveData;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;

public class FavouriteRecipeViewModel extends AndroidViewModel {
    private final UserRecipeRepository userRecipeRepository;


    public FavouriteRecipeViewModel(Application app) {
        super(app);
        userRecipeRepository = UserRecipeRepository.getInstance();
    }
    public void init() {
        userRecipeRepository.init();
    }


    public UserRecipeLiveData getRecipes() {
        return userRecipeRepository.getRecipes();
    }
}
