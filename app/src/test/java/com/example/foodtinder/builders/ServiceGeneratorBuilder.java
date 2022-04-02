package com.example.foodtinder.builders;

import com.example.foodtinder.api.RecipeAPI;
import com.example.foodtinder.api.ServiceGenerator;

public class ServiceGeneratorBuilder {

    public RecipeAPI Build() {
        return ServiceGenerator.getRecipeAPI();
    }
}
