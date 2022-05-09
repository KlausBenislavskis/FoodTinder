package com.example.foodtinder.ui.Friends_List;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

import com.example.foodtinder.repositories.userFriends.UserFriendLiveData;
import com.example.foodtinder.repositories.userFriends.UserFriendRepository;

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
    public Bundle createFriendBundle(String email) {
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        fragment.setArguments(bundle);
        return bundle;
    }
}