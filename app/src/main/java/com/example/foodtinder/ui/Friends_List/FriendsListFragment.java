package com.example.foodtinder.ui.Friends_List;

import static com.example.foodtinder.mappers.ApiToModel.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodtinder.MainActivity;
import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FriendsListAdapter;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.repositories.RecipeRepository;
import com.example.foodtinder.ui.Swipe.SwipeViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class FriendsListFragment extends Fragment {
    RecyclerView friendsList;
    FriendsListAdapter adapter;
    Button addFriendButton;
    EditText email;
    private FriendsListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      viewModel = new ViewModelProvider(this).get(FriendsListViewModel.class);
      viewModel.init();
      View root = inflater.inflate(R.layout.fragment_friends_list, container, false);

      friendsList = root.findViewById(R.id.friendsListView);
      friendsList.hasFixedSize();
      friendsList.setLayoutManager(new LinearLayoutManager(getContext()));
      ArrayList<UserItemModel> friends = new ArrayList<>();
      adapter = new FriendsListAdapter(friends);
      friendsList.setAdapter(adapter);
      viewModel.getFriends().observe(getViewLifecycleOwner(), userItemModels -> {
            //Have to do so liveData listeners are registered
        });
      addFriendButton = root.findViewById(R.id.addFriendButton);
      email = root.findViewById(R.id.friendsEmailAddressInputField);
      addFriendButton.setOnClickListener(v->{
          viewModel.onAddFriend(email.getText().toString());
      });
      return root;
    }


}
