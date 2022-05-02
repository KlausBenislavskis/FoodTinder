package com.example.foodtinder.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.R;
import com.example.foodtinder.callback.RecipeClickCallBack;
import com.example.foodtinder.callback.UserRecipeCallback;
import com.example.foodtinder.models.UserRecipe;

import java.util.ArrayList;
import java.util.List;

public class FavouriteRecipeListAdapter extends RecyclerView.Adapter<FavouriteRecipeListAdapter.ViewHolder> {
    private ArrayList<UserRecipe> recipes;
    private RecipeClickCallBack callBack;
    private Context context;

    public void setOnClickListener(RecipeClickCallBack listener) {
        this.callBack = listener;
    }

    public FavouriteRecipeListAdapter(Context context, ArrayList<UserRecipe> recipeItemModelList) {
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
        UserRecipe r = recipes.get(position);
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

    public void setRecipes(ArrayList<UserRecipe> userRecipes) {
            List<UserRecipe> old = recipes;
            List<UserRecipe> newList = new ArrayList<>(userRecipes);
            UserRecipeCallback callback = new UserRecipeCallback(old, newList);
            DiffUtil.DiffResult results = DiffUtil.calculateDiff(callback);
            this.recipes = userRecipes;
            results.dispatchUpdatesTo(this);
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

