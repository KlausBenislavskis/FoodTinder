package com.example.foodtinder.models;

import android.view.View;

import com.example.foodtinder.models.api.Ingredient;

import java.util.ArrayList;

public class RecipeItemModel {
    private String name, image, url;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<String> cautions;
    private int totalTime;
    private double calories;

    public RecipeItemModel(String name, String image, String url, ArrayList<Ingredient> ingredients, ArrayList<String> cautions, double calories, int totalTime) {
        this.name = name;
        this.image = image;
        this.url = url;
        this.ingredients = ingredients;
        this.cautions = cautions;
        this.calories = calories;
        this.totalTime = totalTime;
    }
    public RecipeItemModel(String name) {
        this.name = name;
    }
    public RecipeItemModel() {
    }

        public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
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
}
