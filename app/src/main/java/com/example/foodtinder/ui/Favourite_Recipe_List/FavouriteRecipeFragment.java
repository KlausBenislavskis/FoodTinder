package com.example.foodtinder.ui.Favourite_Recipe_List;

import static com.example.foodtinder.mappers.ApiToModel.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FavouriteRecipeListAdapter;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class FavouriteRecipeFragment extends Fragment {

    RecyclerView favouriteList;
    FavouriteRecipeListAdapter recipeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite_recipe_list, container, false);
        RecipeRepository.getInstance().searchRecipe("chicken");

        favouriteList = root.findViewById(R.id.recycle_favourite_list_view);
        favouriteList.hasFixedSize();
        favouriteList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<RecipeItemModel> list = map(RecipeRepository.getInstance().getSearchedRecipe().getValue());
        recipeAdapter = new FavouriteRecipeListAdapter(list);
        favouriteList.setAdapter(recipeAdapter);
        // detailsButton = root.findViewById(R.id.recipe_details_button);
        //linearLayout = root.findViewById(R.id.recipe_details_layout);


//        recipeAdapter.setOnClickListener(recipeItem -> {
//        linearLayout.setVisibility(View.VISIBLE);
//        });
        return root;
    }
}
