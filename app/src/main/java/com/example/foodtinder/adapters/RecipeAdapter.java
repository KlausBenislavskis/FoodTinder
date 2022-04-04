package com.example.foodtinder.adapters;

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

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<RecipeItemModel> recipeItemModelList;

    public RecipeAdapter(List<RecipeItemModel> recipeItemModelList) {
        this.recipeItemModelList = recipeItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(recipeItemModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeItemModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, url;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_name);
            //url = itemView.findViewById(R.id.item);
        }

        public void setData(RecipeItemModel recipeItemModel) {
            Picasso.get()
                    .load(recipeItemModel.getImage())
                    .fit()
                    .centerCrop()
                    .into(image);
            name.setText(recipeItemModel.getName());
//            url.setText(recipeItemModel.getImage());
        }

    }

    public List<RecipeItemModel> getItems() {
        return recipeItemModelList;
    }

    public void setItems(List<RecipeItemModel> items) {
        this.recipeItemModelList = items;
    }
}
