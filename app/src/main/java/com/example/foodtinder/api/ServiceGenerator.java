package com.example.foodtinder.api;

import java.io.IOException;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static RecipeAPI recipeAPI;


    public static RecipeAPI getRecipeAPI() {
        if (recipeAPI == null) {

            recipeAPI = new Retrofit.Builder()
                    .baseUrl("https://api.edamam.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RecipeAPI.class);
        }
        return recipeAPI;
    }
}
