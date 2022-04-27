package com.example.foodtinder.models;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserItemModel {
    private String username;
    private String password;
    private String email;
    private String image;
    private List<RecipeItemModel> favouriteRecipes;
    private List<UserItemModel> friendsList;

    public UserItemModel(String username, String email, List<RecipeItemModel> favouriteRecipes, List<UserItemModel> friendsList, String image) {
        this.username = username;
        this.email = email;
        this.favouriteRecipes = favouriteRecipes;
        this.friendsList = friendsList;
        this.image = image;
    }

    public UserItemModel()
    {

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


    public void setFavouriteRecipes(List<RecipeItemModel> favouriteRecipes) {
        if(favouriteRecipes != null) {
            this.favouriteRecipes = favouriteRecipes;
        }else{
            this.favouriteRecipes= new ArrayList<RecipeItemModel>();
        }
    }

    public void setFriendsList(List<UserItemModel> friendsList) {
        if(friendsList != null) {
            this.friendsList = friendsList;
        }else{
            this.friendsList = new ArrayList<UserItemModel>();
        }

    }
}
