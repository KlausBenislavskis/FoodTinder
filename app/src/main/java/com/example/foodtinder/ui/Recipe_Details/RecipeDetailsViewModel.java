package com.example.foodtinder.ui.Recipe_Details;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.recipe.RecipeRepository;
import com.example.foodtinder.repositories.userRecipe.UserRecipeLiveData;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;

public class RecipeDetailsViewModel extends AndroidViewModel {
    private final RecipeRepository repository;
    private final UserRecipeRepository userRepository;

    public RecipeDetailsViewModel(Application app){
        super(app);
        repository = RecipeRepository.getInstance();
        userRepository = UserRecipeRepository.getInstance();

    }

    public void init(String id) {
        repository.init(id);
        userRepository.init();
    }

    public UserRecipeLiveData getRecipes() {
        return userRepository.getRecipes();
    }

    public void addRecipe(RecipeItemModel recipeItemModel) {
        userRepository.saveRecipe(new UserRecipe(recipeItemModel.getName(), recipeItemModel.getId()));
    }

    public MutableLiveData<RecipeItemModel> getRecipeDetails(){
        return repository.getSearchedRecipe();
    }
}
