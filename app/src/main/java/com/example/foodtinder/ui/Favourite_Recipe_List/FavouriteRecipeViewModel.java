package com.example.foodtinder.ui.Favourite_Recipe_List;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.userRecipe.UserRecipeLiveData;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;

public class FavouriteRecipeViewModel extends AndroidViewModel {
    private final UserRecipeRepository userRecipeRepository;


    public FavouriteRecipeViewModel(Application app) {
        super(app);
        userRecipeRepository = UserRecipeRepository.getInstance();
    }
    public void init(String email) {
        if (!email.equals("")) {
            userRecipeRepository.init(email);
        } else {
            userRecipeRepository.init();
        }
    }

    public UserRecipeLiveData getRecipes() {
        return userRecipeRepository.getRecipes();
    }
    public Bundle getRecipeDetailBundle(UserRecipe userRecipe) {
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", userRecipe.getId());
        fragment.setArguments(bundle);
        return bundle;
    }

}
