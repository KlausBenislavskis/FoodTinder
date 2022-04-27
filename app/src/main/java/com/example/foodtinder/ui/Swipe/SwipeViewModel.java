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
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;
import com.yuyakaido.android.cardstackview.Direction;

public class SwipeViewModel extends AndroidViewModel {
    private final UserRecipeRepository userRecipeRepository;
    public SwipeViewModel(Application app) {
        super(app);
        userRecipeRepository = UserRecipeRepository.getInstance();
    }

    public void init() {
        userRecipeRepository.init();
    }

    public void onCardSwipedRight(RecipeItemModel recipeItemModel) {
        userRecipeRepository.saveRecipe(new UserRecipe(recipeItemModel.getName(), recipeItemModel.getId()));
    }
}