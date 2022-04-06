package com.example.foodtinder.models;

import java.util.ArrayList;
import java.util.List;

public class UserItemModel {
    private String username;
    private String password;
    private String email;
    private String image;
    private List<RecipeItemModel> favouriteRecipes;
    private List<UserItemModel> friendsList;

    public UserItemModel(String username, String password, String email, List<RecipeItemModel> favouriteRecipes, List<UserItemModel> friendsList, String image) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.favouriteRecipes = favouriteRecipes;
        this.friendsList = friendsList;
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<RecipeItemModel> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public List<UserItemModel> getFriendsList() {
        return friendsList;
    }

    public String getImage() {
        return image;
    }
}
