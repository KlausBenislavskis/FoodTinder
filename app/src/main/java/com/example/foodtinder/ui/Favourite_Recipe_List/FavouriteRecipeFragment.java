package com.example.foodtinder.ui.Favourite_Recipe_List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.MainActivity;
import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FavouriteRecipeListAdapter;
import com.example.foodtinder.adapters.FriendsListAdapter;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.recipe.RecipeRepository;
import com.example.foodtinder.ui.Friends_List.FriendsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteRecipeFragment extends Fragment {

    private RecyclerView recipeList;
    private FavouriteRecipeListAdapter recipeAdapter;
    private FavouriteRecipeViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println(savedInstanceState);

        return inflater.inflate(R.layout.fragment_favourite_recipe_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FavouriteRecipeViewModel.class);
        viewModel.init();
        RecipeRepository.getInstance().searchRecipe("chicken");

        recipeList = view.findViewById(R.id.favouriteRecipeListView);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        //Temporary
        List<UserRecipe> list = new ArrayList<>();

        recipeAdapter = new FavouriteRecipeListAdapter(getContext(), list);
        recipeList.setAdapter(recipeAdapter);

        viewModel.getRecipes().observe(getViewLifecycleOwner(), userRecipes -> {
            recipeAdapter.set(userRecipes);
        });
        if(getArguments() != null) {
            recipeAdapter.setOnClickListener(userrecepie -> {

            });
        }else {
            recipeAdapter.setOnClickListener(userrecepie -> {
                System.out.println(getArguments().getString("email"));
            });
        }

    }


}
