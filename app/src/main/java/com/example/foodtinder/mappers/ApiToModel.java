package com.example.foodtinder.mappers;

import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.models.api.Hit;

import java.util.ArrayList;
import java.util.List;

public class ApiToModel {
    public static List<RecipeItemModel> map(List<Hit> recipeItems) {
        List<RecipeItemModel> recipeItemModels = new ArrayList<>();
        for (Hit recipeItem : recipeItems) {
            RecipeItemModel recipeItemModel = new RecipeItemModel(recipeItem.recipe.label,
                    recipeItem.recipe.image,
                    recipeItem.recipe.url,
                    recipeItem.recipe.ingredients,
                    recipeItem.recipe.cautions,
                    recipeItem.recipe.calories,
                    recipeItem.recipe.totalTime,
                    getIdFromUri(recipeItem.recipe.uri));
            recipeItemModels.add(recipeItemModel);
        }
        return recipeItemModels;
    }

    private static String getIdFromUri(String uri) {
        return uri.split("#")[uri.split("#").length - 1];
    }
}
