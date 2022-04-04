package com.example.foodtinder.ui.Favourite_Recipe_List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.MainActivity;
import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FavouriteRecipeListAdapter;
import com.example.foodtinder.adapters.RecipeAdapter;
import com.example.foodtinder.databinding.FragmentFavouriteRecipeListBinding;
import com.example.foodtinder.databinding.FragmentLoginBinding;
import com.example.foodtinder.databinding.FragmentRecipeItemBinding;
import com.example.foodtinder.models.RecipeItemModel;

import java.util.ArrayList;

public class FavouriteRecipeFragment extends Fragment {

     RecyclerView favouriteList;
     FavouriteRecipeListAdapter recipeAdapter;
     FragmentFavouriteRecipeListBinding binding;
     LinearLayout linearLayout;
     Button detailsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavouriteRecipeListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        favouriteList = root.findViewById(R.id.recycle_favourite_list_view);
        favouriteList.hasFixedSize();
        favouriteList.setLayoutManager(new LinearLayoutManager(getActivity()));
        detailsButton = root.findViewById(R.id.recipe_details_button);
        linearLayout = root.findViewById(R.id.recipe_details_layout);

        ArrayList<RecipeItemModel> list = new ArrayList<>();
        list.add(new RecipeItemModel("Recipe name 1", "image 1", "def"));
        list.add(new RecipeItemModel("Recipe name 2", "image 2", "def"));
        list.add(new RecipeItemModel("Recipe name 3", "image 3", "def"));
        list.add(new RecipeItemModel("Recipe name 4", "image 4", "def"));


        recipeAdapter = new FavouriteRecipeListAdapter(list);
        favouriteList.setAdapter(recipeAdapter);
        recipeAdapter.setOnClickListener(recipeItem -> {
        linearLayout.setVisibility(View.VISIBLE);
        });
        return root;
    }
}
