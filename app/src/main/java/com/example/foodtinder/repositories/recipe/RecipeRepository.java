package com.example.foodtinder.repositories.recipe;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.foodtinder.Config;
import com.example.foodtinder.api.ServiceGenerator;
import com.example.foodtinder.models.api.Hit;
import com.example.foodtinder.models.api.RecipeResponse;
import com.example.foodtinder.repositories.userCurrent.CurrentUserRepository;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.internal.EverythingIsNonNull;

public class RecipeRepository {
    private static RecipeRepository instance;
    private DatabaseReference reference;
    private final MutableLiveData<ArrayList<Hit>> searchedRecipe;

    public static synchronized RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public void init(){

    }

    private RecipeRepository() {
        searchedRecipe = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Hit>> getSearchedRecipe() {
        return searchedRecipe;
    }

    public void searchRecipe(String query) {
        Call<RecipeResponse> call = ServiceGenerator.getRecipeAPI().getRecipes("public", "true", query, Config.app_id, Config.app_key);

        call.enqueue(new retrofit2.Callback<RecipeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RecipeResponse> call, retrofit2.Response<RecipeResponse> response) {
                searchedRecipe.setValue(response.body().hits);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}


