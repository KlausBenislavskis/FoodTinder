package com.example.foodtinder.ui.Favourite_Recipe_List;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
import com.example.foodtinder.repositories.recipe.RecipeRepository;

import java.util.ArrayList;

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
        //RecipeRepository.getInstance().searchRecipe("chicken");
        recipeList = view.findViewById(R.id.favouriteRecipeListView);

        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        //Temporary
        recipeAdapter = new FavouriteRecipeListAdapter(getContext(), new ArrayList<>());
        viewModel.getRecipes().observe(getViewLifecycleOwner(), userRecipes -> {
            recipeAdapter.setRecipes(userRecipes);
        });

        recipeAdapter.setOnClickListener(userRecipe -> {
            //viewModel.saveId(userRecipe.getId());
            Fragment fragment = new Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("id", userRecipe.getId());
            fragment.setArguments(bundle);
            ((MainActivity)getActivity()).navController.navigate(R.id.nav_recipe_details, bundle);
        });
        recipeList.setAdapter(recipeAdapter);

    }
}
