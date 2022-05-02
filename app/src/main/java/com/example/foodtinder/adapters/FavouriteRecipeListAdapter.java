package com.example.foodtinder.adapters;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.R;
import com.example.foodtinder.models.RecipeItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteRecipeListAdapter extends RecyclerView.Adapter<FavouriteRecipeListAdapter.ViewHolder> {
    List<RecipeItemModel> recipes;
    private OnClickListenerRecipe listener;
    int previousExpandedPosition = -1;
    int mExpandedPosition = -1;

    public void setOnClickListener(OnClickListenerRecipe listener) {
        this.listener = listener;
    }

    public FavouriteRecipeListAdapter(List<RecipeItemModel> recipeItemModelList) {
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
        final boolean isExpanded = position == mExpandedPosition;
        RecipeItemModel item = recipes.get(position);
        holder.title.setText(recipes.get(position).getName());
        String ing = "";
        for (int i=0; i< item.getIngredients().size(); i++) {
            ing += item.getIngredients().get(i).text + "\n";
        }
        holder.ingredients.setText(ing);

        String cautions = "";
        for (int i=0; i< item.getCautions().size(); i++) {
            cautions += item.getCautions().get(i);
        }
        holder.cautions.setText(cautions);
        holder.calories.setText(String.valueOf(Math.round(item.getCalories())));
        holder.totalTime.setText(String.valueOf(item.getTotalTime()));
        Picasso.get()
                .load(item.getImage())
                .fit()
                .centerCrop()
                .into(holder.image);
        holder.image.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.calories.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.totalTime.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.cautions.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.ingredients.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        if(isExpanded){
            previousExpandedPosition = position;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private  ImageView image;
        private  TextView title;
        private  TextView ingredients;
        private  TextView cautions;
        private  TextView totalTime;
        private  TextView calories;

        ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.recipe_small_image);
            title = itemView.findViewById(R.id.recipe_title);
            ingredients = itemView.findViewById(R.id.recipe_ingredients);
            cautions = itemView.findViewById(R.id.recipe_cautions);
            totalTime = itemView.findViewById(R.id.recipe_totalTime);
            calories = itemView.findViewById(R.id.recipe_calories);
            itemView.setOnClickListener(v ->{
                //listener.OnClickRecipe(recipes.get(getBindingAdapterPosition()));
                System.out.println("Clicked");
            });
        }
    }

    public interface OnClickListenerRecipe {
        void OnClickRecipe(RecipeItemModel recipe);
    }
}

