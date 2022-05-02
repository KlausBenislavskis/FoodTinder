package com.example.foodtinder.repositories.recipe;

import androidx.lifecycle.MutableLiveData;

import com.example.foodtinder.Config;
import com.example.foodtinder.api.ServiceGenerator;
import com.example.foodtinder.mappers.ApiToModel;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.api.Hit;
import com.example.foodtinder.models.api.RecipeResponse;
import com.google.firebase.database.DatabaseReference;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.internal.EverythingIsNonNull;

public class RecipeRepository {
    private static RecipeRepository instance;
    private DatabaseReference reference;
    private final MutableLiveData<ArrayList<Hit>> searchedRecipes;

    public static synchronized RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public void init(){

    }

    private RecipeRepository() {
        searchedRecipes = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Hit>> getSearchedRecipes() {
        return searchedRecipes;
    }



    public void searchRecipe(String query) {
        Call<RecipeResponse> call = ServiceGenerator.getRecipeAPI().getRecipes("public", "true", query, Config.app_id, Config.app_key);

        call.enqueue(new retrofit2.Callback<RecipeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<RecipeResponse> call, retrofit2.Response<RecipeResponse> response) {
                searchedRecipes.setValue(response.body().hits);
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public  MutableLiveData<RecipeItemModel>  searchRecipeById(String id) {
        Call<Hit> call = ServiceGenerator.getRecipeAPI().getRecipeById(id, "public", Config.app_id, Config.app_key);
        MutableLiveData<RecipeItemModel> searchedRecipe = new MutableLiveData<>();
        call.enqueue(new retrofit2.Callback<Hit>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Hit> call, retrofit2.Response<Hit> response) {
                if (response.body() != null) {
                    searchedRecipe.setValue(ApiToModel.map(response.body()));
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Hit> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return searchedRecipe;
    }
}


