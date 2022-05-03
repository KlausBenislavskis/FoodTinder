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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodtinder.R;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.api.Ingredient;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


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

        MutableLiveData<RecipeItemModel> recipeItemModelLive = viewModel.getRecipeDetails();
        recipeItemModelLive.observe(getViewLifecycleOwner(), recipeItemModel -> {
            title.setText(recipeItemModel.getName());
            Picasso.get()
                    .load(recipeItemModel.getImage())
                    .fit()
                    .centerCrop()
                    .into(image);
            String ing = "";
            DecimalFormat df = new DecimalFormat("0.00");
            for(Ingredient item: recipeItemModel.getIngredients()){
                if(item.measure == null || item.quantity == 0){
                    ing += item.food + "\n";
                }
                else{
                    ing += df.format(item.quantity) + " " + item.measure + " " + item.food + "\n";
                }
            }
            ingredients.setText(ing);
            calories.setText(String.valueOf(df.format(recipeItemModel.getCalories())));
            StringBuilder str = new StringBuilder(String.valueOf(recipeItemModel.getCautions()));
            str.deleteCharAt(0);
            str.deleteCharAt(str.length() -1);
            cautions.setText(str);
            totalTime.setText(String.valueOf(recipeItemModel.getTotalTime()));
        });

        return root;
    }
}
