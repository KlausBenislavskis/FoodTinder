package com.example.foodtinder.ui.Recipe_Details;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodtinder.R;


public class RecipeDetailsFragment extends Fragment {

    private RecipeDetailsViewModel viewModel;
    private TextView title;
    private ImageView image;
    private TextView ingredients;
    private TextView calories;
    private TextView cautions;
    private TextView totalTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(RecipeDetailsViewModel.class);
        viewModel.init();
        View root = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        title = root.findViewById(R.id.recipe_title);
        image = root.findViewById(R.id.recipe_small_image);
        ingredients = root.findViewById(R.id.recipe_ingredients);
        calories = root.findViewById(R.id.recipe_calories);
        cautions = root.findViewById(R.id.recipe_cautions);
        totalTime = root.findViewById(R.id.recipe_totalTime);

        return root;
    }
}
