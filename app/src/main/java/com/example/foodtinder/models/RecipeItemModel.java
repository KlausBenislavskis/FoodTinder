package com.example.foodtinder.models;

public class RecipeItemModel {
    private String name, image, url;

    public RecipeItemModel(String name, String image, String url) {
        this.name = name;
        this.image = image;
        this.url = url;
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
}
