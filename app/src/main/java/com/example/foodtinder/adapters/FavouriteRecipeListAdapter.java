package com.example.foodtinder.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.R;
import com.example.foodtinder.callback.RecipeClickCallBack;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.ui.Recipe_Details.RecipeDetailsFragment;

import java.util.List;

public class FavouriteRecipeListAdapter extends RecyclerView.Adapter<FavouriteRecipeListAdapter.ViewHolder> {
    private List<UserRecipe> recipes;
    private RecipeClickCallBack callBack;
    private Context context;

    public void setOnClickListener(RecipeClickCallBack listener) {
        this.callBack = listener;
    }

    public FavouriteRecipeListAdapter(Context context, List<UserRecipe> recipeItemModelList) {
        this.context = context;
        this.recipes = recipeItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.favourite_recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        UserRecipe item = recipes.get(position);
        holder.title.setText(recipes.get(position).getName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onClick(recipes.get(holder.getBindingAdapterPosition()));
            }
        });
    }
    public void set(List<UserRecipe> userRecipe)
    {
        this.recipes = userRecipe;
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ConstraintLayout layout;

        ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.recipe_title);
            layout = itemView.findViewById(R.id.favourite_recipe_item);
            itemView.setOnClickListener(v ->{
                //listener.OnClickRecipe(recipes.get(getBindingAdapterPosition()));
                System.out.println("Clicked");
            });
        }
    }

}

