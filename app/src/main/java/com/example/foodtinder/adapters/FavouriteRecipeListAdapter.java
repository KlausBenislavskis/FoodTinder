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

import java.util.List;

public class FavouriteRecipeListAdapter extends RecyclerView.Adapter<FavouriteRecipeListAdapter.ViewHolder>{

    private List<RecipeItemModel> recipeItemModelList;
    private OnClickListener listener;

    public FavouriteRecipeListAdapter(List<RecipeItemModel> recipeItemModelList) {
        this.recipeItemModelList = recipeItemModelList;
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_favourite_recipe_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(recipeItemModelList.get(position).getName());
        holder.image.setImageResource(Integer.parseInt((recipeItemModelList.get(position).getImage())));
    }

    @Override
    public int getItemCount() {
        return recipeItemModelList.size();
    }

    public interface OnClickListener{
        void OnClick(RecipeItemModel recipeItem);
    }

    public List<RecipeItemModel> getItems() {
        return recipeItemModelList;
    }

    public void setItems(List<RecipeItemModel> items) {
        this.recipeItemModelList = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recipe_image);
            name = itemView.findViewById(R.id.recipe_name);
            itemView.setOnClickListener(v ->{
                listener.OnClick(recipeItemModelList.get(getBindingAdapterPosition()));
            });
        }
    }
}
