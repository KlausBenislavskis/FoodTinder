package com.example.foodtinder.ui.Friends_List;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.MainActivity;
import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FriendsListAdapter;
import com.example.foodtinder.models.UserItemModel;

import java.util.ArrayList;

public class FriendsListFragment extends Fragment {
    RecyclerView friendsList;
    FriendsListAdapter adapter;
    Button addFriendButton;
    EditText email;
    private FriendsListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_friends_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FriendsListViewModel.class);
        viewModel.init();

        friendsList = view.findViewById(R.id.friendsListView);
        friendsList.hasFixedSize();
        friendsList.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> friends = new ArrayList<>();
        adapter = new FriendsListAdapter(friends);
        friendsList.setAdapter(adapter);
        viewModel.getFriends().observe(getViewLifecycleOwner(), userItemModels -> {
            adapter.set(userItemModels);
        });
        addFriendButton = view.findViewById(R.id.addFriendButton);
        email = view.findViewById(R.id.friendsEmailAddressInputField);
        addFriendButton.setOnClickListener(v->{
        if(!adapter.contains(email.getText().toString())) {
            viewModel.onAddFriend(email.getText().toString());
        }
        else{
            Toast.makeText(getContext(), "User already in friend list", Toast.LENGTH_SHORT).show();
        }});
        adapter.setOnItemClickListener(email -> {

        });
    }
}
