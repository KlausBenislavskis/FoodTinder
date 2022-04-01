package com.example.foodtinder.api;

import com.example.foodtinder.models.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeAPI {
    @GET("https://api.edamam.com/api/recipes/v2")
    Call<RecipeResponse> getRecipes();

}
