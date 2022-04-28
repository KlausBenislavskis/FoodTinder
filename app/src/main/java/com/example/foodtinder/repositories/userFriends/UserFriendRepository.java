package com.example.foodtinder.repositories.userFriends;

import androidx.annotation.NonNull;

import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.UserRepository;
import com.example.foodtinder.repositories.userRecipe.UserRecipeLiveData;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserFriendRepository {
    private static UserFriendRepository instance;
    private DatabaseReference reference;
    private UserFriendLiveData friends;
    private UserFriendRepository() {
    }

    public static synchronized UserFriendRepository getInstance() {
        if (instance == null) {
            instance = new UserFriendRepository();
        }
        return instance;
    }
    public void init() {
        reference = getUserFriendsDbReference(FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users"));
        friends = new UserFriendLiveData(reference);
    }
    public void addFriend(String friend) {
        ArrayList<String> friendsList = friends.getValue();
        if(friendsList == null) {
            friendsList = new ArrayList<>();
        }
        if(!friendsList.contains(friend)) {
            friendsList.add(friend);
            reference.setValue(friendsList);
        }
    }

    public UserFriendLiveData getFriends() {
        return friends;
    }
    @NonNull
    private DatabaseReference getUserFriendsDbReference(DatabaseReference myRef) {
        return myRef
                .child(getCurrentUserSafeEmail())
                .child("friendsList");
    }
    private String getCurrentUserSafeEmail() {
        UserRepository userRepository = UserRepository.getInstance();
        return userRepository.getSafeCurrentUserEmail(userRepository.getCurrentUser().getValue().getEmail());
    }
}
