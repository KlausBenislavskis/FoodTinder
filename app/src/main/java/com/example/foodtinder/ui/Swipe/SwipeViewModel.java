package com.example.foodtinder.ui.Swipe;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.models.api.Hit;
import com.example.foodtinder.repositories.recipe.RecipeRepository;
import com.example.foodtinder.repositories.userRecipe.UserRecipeLiveData;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;

import java.util.ArrayList;

public class SwipeViewModel extends AndroidViewModel {
    private final UserRecipeRepository userRecipeRepository;
    private final RecipeRepository recipeRepository;

    public SwipeViewModel(Application app) {
        super(app);
        userRecipeRepository = UserRecipeRepository.getInstance();
        recipeRepository = RecipeRepository.getInstance();
    }

    public void init() {
        userRecipeRepository.init();
    }
    public void searchRecipe(String query) {
        recipeRepository.searchRecipe(query);
    }

    public void onCardSwipedRight(RecipeItemModel recipeItemModel) {
        userRecipeRepository.saveRecipe(new UserRecipe(recipeItemModel.getName(), recipeItemModel.getId()));
    }
    public MutableLiveData<ArrayList<Hit>> getRecipes() {
        return recipeRepository.getSearchedRecipes();
    }
    public UserRecipeLiveData getUserRecipes() {
        return userRecipeRepository.getRecipes();
    }
}