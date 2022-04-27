package com.example.foodtinder.models;

import java.util.ArrayList;
import java.util.List;

public class UserItemModel {
    private String username;
    private String email;
    private String image;
    private List<UserRecipe> favouriteRecipes;
    private List<String> friendsList;

    public UserItemModel(String username, String email, List<UserRecipe> favouriteRecipes, List<String> friendsList, String image) {
        this.username = username;
        this.email = email;
        if(favouriteRecipes != null) {
            this.favouriteRecipes = favouriteRecipes;
        }
        else this.favouriteRecipes = new ArrayList<>();
        if(friendsList != null){
            this.friendsList = friendsList;}
        else this.friendsList = new ArrayList<>();
        this.image = image;
    }

    public UserItemModel() {
        this.favouriteRecipes = new ArrayList<>();
        this.friendsList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }


    public List<UserRecipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }


    public String getImage() {
        return image;
    }


    public void setFavouriteRecipes(List<UserRecipe> favouriteRecipes) {
        if (favouriteRecipes != null) {
            this.favouriteRecipes = favouriteRecipes;
        } else {
            this.favouriteRecipes = new ArrayList<>();
        }
    }

    public List<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<String> friendsList) {
        this.friendsList = friendsList;
    }
}
