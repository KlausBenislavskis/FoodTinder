package com.example.foodtinder.repositories.userFriends;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.repositories.userCurrent.CurrentUserRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

            DatabaseReference friendsReference =
            getUserFriendsDbReference(FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("users"),CurrentUserRepository.getInstance().getSafeCurrentUserEmail(friend));
            Query query = FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users").orderByKey().equalTo(CurrentUserRepository.getInstance().getSafeCurrentUserEmail(friend));
            ArrayList<String> finalFriendsList = friendsList;
            query.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getChildrenCount()>0) {
                        finalFriendsList.add(friend);
                        reference.setValue(finalFriendsList);
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
                    }else{
                        System.out.println("not found");
                    }

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

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
