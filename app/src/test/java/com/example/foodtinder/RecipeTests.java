package com.example.foodtinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.foodtinder.api.RecipeAPI;
import com.example.foodtinder.api.ServiceGenerator;
import com.example.foodtinder.builders.ServiceGeneratorBuilder;
import com.example.foodtinder.models.api.RecipeResponse;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.internal.EverythingIsNonNull;

public class RecipeTests {
    @Test
    public void receivingResponseFromAPI() throws IOException {

        Call<RecipeResponse> call = ServiceGenerator.getRecipeAPI().getRecipes("public", "true", "chicken", Config.app_id, Config.app_key);
        retrofit2.Response<RecipeResponse> response = call.execute();
        assertNotNull(response.body());
        assertEquals(response.code(), 200);

//            @EverythingIsNonNull
//            @Override
//            public void onResponse(Call<RecipeResponse> call, retrofit2.Response<RecipeResponse> response) {
//                assertNotNull(response.body());
//            }
//
//            @EverythingIsNonNull
//            @Override
//            public void onFailure(Call<RecipeResponse> call, Throwable t) {
//                t.printStackTrace();
//
//                assertNull(t);
//            }

    }
}
