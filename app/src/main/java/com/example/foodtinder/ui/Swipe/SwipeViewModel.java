package com.example.foodtinder.ui.Swipe;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.models.api.Hit;
import com.example.foodtinder.models.api.Recipe;
import com.example.foodtinder.repositories.recipe.RecipeRepository;
import com.example.foodtinder.repositories.userRecipe.UserRecipeLiveData;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;
import com.yuyakaido.android.cardstackview.Direction;

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
        return recipeRepository.getSearchedRecipe();
    }
    public UserRecipeLiveData getUserRecipes() {
        return userRecipeRepository.getRecipes();
    }
}