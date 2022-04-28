package com.example.foodtinder.ui.Friends_List;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.userFriends.UserFriendLiveData;
import com.example.foodtinder.repositories.userFriends.UserFriendRepository;
import com.example.foodtinder.repositories.userRecipe.UserRecipeLiveData;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;

public class FriendsListViewModel  extends AndroidViewModel {
    private final UserFriendRepository userFriendRepository;
    public FriendsListViewModel(Application app) {
        super(app);
        userFriendRepository = UserFriendRepository.getInstance();
    }

    public void init() {
        userFriendRepository.init();
    }

    public void onAddFriend(String friendEmail) {
        userFriendRepository.addFriend(friendEmail);
    }
    public UserFriendLiveData getFriends() {
        return userFriendRepository.getFriends();
    }
}