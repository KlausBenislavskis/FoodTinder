package com.example.foodtinder.models;


import com.example.foodtinder.models.api.Ingredient;

import java.util.ArrayList;

public class RecipeItemModel {
    private String name;
    private final String image;
    private final String url;
    private String id;
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<String> cautions;
    private final int totalTime;
    private final double calories;


    public RecipeItemModel(String name, String image, String url, ArrayList<Ingredient> ingredients, ArrayList<String> cautions, double calories, int totalTime, String id) {
        this.name = name;
        this.image = image;
        this.url = url;
        this.ingredients = ingredients;
        this.cautions = cautions;
        this.calories = calories;
        this.totalTime = totalTime;
        this.id = id;
    }


    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<String> getCautions() {
        return cautions;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public double getCalories() {
        return calories;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setId(String id) {
        this.id = id;
    }




}
