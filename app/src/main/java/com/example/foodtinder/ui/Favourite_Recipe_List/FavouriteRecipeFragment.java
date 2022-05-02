package com.example.foodtinder.ui.Favourite_Recipe_List;

import static com.example.foodtinder.mappers.ApiToModel.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FavouriteRecipeListAdapter;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.repositories.recipe.RecipeRepository;

import java.util.List;

public class FavouriteRecipeFragment extends Fragment {

    RecyclerView recipeList;
    FavouriteRecipeListAdapter recipeAdapter;
    private FavouriteRecipeViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(FavouriteRecipeViewModel.class);
        viewModel.init();
        View root = inflater.inflate(R.layout.fragment_favourite_recipe_list, container, false);
        RecipeRepository.getInstance().searchRecipe("chicken");

        recipeList = root.findViewById(R.id.favouriteRecipeListView);
        recipeList.hasFixedSize();
        recipeList.setLayoutManager(new LinearLayoutManager(getContext()));
        List<RecipeItemModel> list = map(RecipeRepository.getInstance().getSearchedRecipe().getValue());
        recipeAdapter = new FavouriteRecipeListAdapter(list);
        recipeList.setAdapter(recipeAdapter);
        viewModel.getRecipes().observe(getViewLifecycleOwner(), userRecipes -> {
            //Have to do so liveData listeners are registered
        });

        return root;
    }
}
