package com.example.foodtinder.ui.Recipe_Details;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.foodtinder.repositories.recipe.RecipeLiveData;
import com.example.foodtinder.repositories.recipe.RecipeRepository;

public class RecipeDetailsViewModel extends AndroidViewModel {
    private final RecipeRepository repository;

    public RecipeDetailsViewModel(Application app){
        super(app);
        repository = RecipeRepository.getInstance();
    }

    public void init() {
        repository.init();
    }

    public RecipeLiveData getRecipeDetails(){
        //Return details about specific recipe
        return null;
    }
}
