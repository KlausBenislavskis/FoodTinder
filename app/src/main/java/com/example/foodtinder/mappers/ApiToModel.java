package com.example.foodtinder.mappers;

import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.api.Hit;

import java.util.ArrayList;
import java.util.List;

public class ApiToModel {
    public static List<RecipeItemModel> map(
            List<Hit> recipeItems) {
        List<RecipeItemModel> recipeItemModels = new ArrayList<>();
        for (Hit recipeItem : recipeItems) {
            RecipeItemModel recipeItemModel = new RecipeItemModel(recipeItem.recipe.label,
                    recipeItem.recipe.image,
                    recipeItem.recipe.url);
            recipeItemModels.add(recipeItemModel);
        }
        return recipeItemModels;
    }
}
