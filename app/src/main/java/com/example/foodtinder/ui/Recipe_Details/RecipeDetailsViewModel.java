package com.example.foodtinder.ui.Recipe_Details;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.repositories.recipe.RecipeLiveData;
import com.example.foodtinder.repositories.recipe.RecipeRepository;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;

public class RecipeDetailsViewModel extends AndroidViewModel {
    private final RecipeRepository repository;
    private final UserRecipeRepository userRepository;

    public RecipeDetailsViewModel(Application app){
        super(app);
        repository = RecipeRepository.getInstance();
        userRepository = UserRecipeRepository.getInstance();
    }

    public void init() {
        repository.init();
    }

    public String getRecipeId(){
        return userRepository.getId();
    }

    public RecipeItemModel getRecipeDetails(){
        return repository.searchRecipeById(getRecipeId());
    }
}
