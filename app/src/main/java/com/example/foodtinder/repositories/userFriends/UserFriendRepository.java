package com.example.foodtinder.repositories.userFriends;

import androidx.annotation.NonNull;

import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.repositories.userCurrent.CurrentUserRepository;
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
        reference = getUserFriendsDbReference(FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users"),getCurrentUserSafeEmail());
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
            DatabaseReference friendsReference =
            getUserFriendsDbReference(FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("users"),CurrentUserRepository.getInstance().getSafeCurrentUserEmail(friend));
            friendsReference.get().addOnCompleteListener(v->{
                if(v.isSuccessful()) {
                    ArrayList<String> friends = (ArrayList<String>)v.getResult().getValue();
                    if(friends == null) {
                        friends = new ArrayList<>();
                    }
                    friends.add(CurrentUserRepository.getInstance().getCurrentUser().getValue().getEmail());
                    friendsReference.setValue(friends);
                }
            });
        }
    }

    public UserFriendLiveData getFriends() {
        return friends;
    }
    @NonNull
    private DatabaseReference getUserFriendsDbReference(DatabaseReference myRef,String userEmail) {
        return myRef
                .child(userEmail)
                .child("friendsList");
    }
    private String getCurrentUserSafeEmail() {
        CurrentUserRepository currentUserRepository = CurrentUserRepository.getInstance();
        return currentUserRepository.getSafeCurrentUserEmail(currentUserRepository.getCurrentUser().getValue().getEmail());
    }

}
