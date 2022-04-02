package com.example.foodtinder.models.api;

import java.util.ArrayList;

public class Recipe {
    public String uri;
    public String label;
    public String image;
    public Images images;
    public String source;
    public String url;
    public String shareAs;
    public int yield;
    public ArrayList<String> dietLabels;
    public ArrayList<String> healthLabels;
    public ArrayList<String> cautions;
    public ArrayList<String> ingredientLines;
    public ArrayList<Ingredient> ingredients;
    public double calories;
    public double totalWeight;
    public int totalTime;
    public ArrayList<String> cuisineType;
    public ArrayList<String> mealType;
    public ArrayList<String> dishType;
    public ArrayList<Digest> digest;
}
