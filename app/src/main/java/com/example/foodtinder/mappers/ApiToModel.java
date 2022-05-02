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
            RecipeItemModel recipeItemModel = map(recipeItem);
            recipeItemModels.add(recipeItemModel);
        }
        return recipeItemModels;
    }

    public static RecipeItemModel map(Hit recipeItems) {
            return new RecipeItemModel(recipeItems.recipe.label,
                    recipeItems.recipe.image,
                    recipeItems.recipe.url,
                    recipeItems.recipe.ingredients,
                    recipeItems.recipe.cautions,
                    recipeItems.recipe.calories,
                    recipeItems.recipe.totalTime,
                    getIdFromUri(recipeItems.recipe.uri));
    }
    private static String getIdFromUri(String uri) {
        return uri.split("#")[uri.split("#").length - 1];
    }
}
