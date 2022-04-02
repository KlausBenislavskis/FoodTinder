package com.example.foodtinder.api;

import com.example.foodtinder.models.api.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeAPI {


    @GET("api/recipes/v2")
    Call<RecipeResponse> getRecipes(@Query("type") String type,@Query("random")String random,@Query("q") String keyWords, @Query("app_id") String appId, @Query("app_key") String appKey);

}
