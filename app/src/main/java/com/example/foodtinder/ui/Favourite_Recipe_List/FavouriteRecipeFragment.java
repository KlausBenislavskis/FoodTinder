package com.example.foodtinder.ui.Favourite_Recipe_List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
        return inflater.inflate(R.layout.fragment_favourite_recipe_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(FavouriteRecipeViewModel.class);
        viewModel.init(getArguments() == null ? "": getArguments().getString("email"));
        RecipeRepository.getInstance().searchRecipe("chicken");

        recipeList = view.findViewById(R.id.favouriteRecipeListView);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        //Temporary
        recipeAdapter = new FavouriteRecipeListAdapter(getContext(), new ArrayList<>());
//
//        if(getArguments() == null) {
//            viewModel.getRecipes().observe(getViewLifecycleOwner(), userRecipes -> {
//                recipeAdapter.setRecipes(userRecipes);
//            });
//        }else {
//            viewModel.getRecipes().observe(getViewLifecycleOwner(), userRecipes -> {
//                System.out.println(getArguments().getString("email"));
//            });
//        }
                    viewModel.getRecipes().observe(getViewLifecycleOwner(), userRecipes -> {
                recipeAdapter.setRecipes(userRecipes);
            });
        recipeAdapter.setOnClickListener(userRecipe -> {
            viewModel.saveId(userRecipe.getId());
            //Move to Detailed view
            ((MainActivity)getActivity()).navController.navigate(R.id.nav_recipe_details);
        });
        recipeList.setAdapter(recipeAdapter);

    }
    public FavouriteRecipeFragment()
    {

    }
}
