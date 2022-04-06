package com.example.foodtinder.ui.Friends_List;

import static com.example.foodtinder.mappers.ApiToModel.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FriendsListAdapter;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;


public class FriendsListFragment extends Fragment {
    RecyclerView friendsList;
    FriendsListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View root = inflater.inflate(R.layout.fragment_friends_list, container, false);
      RecipeRepository.getInstance().searchRecipe("chicken");

      friendsList = root.findViewById(R.id.friendsListView);
      friendsList.hasFixedSize();
      friendsList.setLayoutManager(new LinearLayoutManager(getContext()));
      ArrayList<UserItemModel> friends = new ArrayList<>();
      adapter = new FriendsListAdapter(friends);
      friendsList.setAdapter(adapter);
      testing(friends);

      return root;
    }

    private void testing(ArrayList<UserItemModel> friends) {
        List<RecipeItemModel> recipes = map(RecipeRepository.getInstance().getSearchedRecipe().getValue());
        friends.add(new UserItemModel("pepe", "password", "email", recipes, null, String.valueOf(R.drawable.ic_arrow_back_black_24dp)));
        friends.add(new UserItemModel("pepe2", "null", "null", recipes, null, String.valueOf(R.drawable.ic_arrow_back_black_24dp)));
    }

}
