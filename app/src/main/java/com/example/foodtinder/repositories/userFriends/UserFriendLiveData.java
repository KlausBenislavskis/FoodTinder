package com.example.foodtinder.repositories.userFriends;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserFriendLiveData extends LiveData<ArrayList<String>> {
    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            ArrayList<String> friends = (ArrayList<String>) snapshot.getValue();
            setValue(friends);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
    DatabaseReference databaseReference;

    public UserFriendLiveData(DatabaseReference reference) {
        databaseReference = reference;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}
