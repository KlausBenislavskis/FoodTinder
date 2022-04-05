package com.example.foodtinder.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.foodtinder.R;
import com.example.foodtinder.models.RecipeItemModel;
import com.ramotion.foldingcell.FoldingCell;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "unused"})
public class FavouriteRecipeListAdapter extends ArrayAdapter<RecipeItemModel> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private View.OnClickListener listener;

    public FavouriteRecipeListAdapter(Context context, List<RecipeItemModel> recipeItemModelList) {
        super(context, 0, recipeItemModelList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get item for selected view
        RecipeItemModel item = getItem(position);
        // if cell is exists - reuse it, if not - create the new one from resource
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.fragment_recipe_folding_item, parent, false);
            // binding view parts to view holder
            viewHolder.nameUnfolded = cell.findViewById(R.id.recipe_title);
            viewHolder.nameFolded = cell.findViewById(R.id.recipe_title2);
            viewHolder.image = cell.findViewById(R.id.recipe_small_image);
            viewHolder.ingredients = cell.findViewById(R.id.recipe_ingredients);
            viewHolder.cautions = cell.findViewById(R.id.recipe_cautions);
            viewHolder.calories = cell.findViewById(R.id.recipe_calories);
            viewHolder.totalTime = cell.findViewById(R.id.recipe_totalTime);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }

        if (null == item)
            return cell;

        // bind data from selected element to view through view holder
        viewHolder.nameUnfolded.setText(item.getName());
        viewHolder.nameFolded.setText(item.getName());
        Picasso.get()
                .load(item.getImage())
                .fit()
                .centerCrop()
                .into(viewHolder.image);
        String ing = "";
        for (int i=0; i< item.getIngredients().size(); i++) {
            ing += item.getIngredients().get(i).text + "\n";
        }
        viewHolder.ingredients.setText(ing);

        String cautions = "";
        for (int i=0; i< item.getCautions().size(); i++) {
            cautions += item.getCautions().get(i);
        }
        viewHolder.cautions.setText(cautions);
        viewHolder.calories.setText(String.valueOf(Math.round(item.getCalories())));
        viewHolder.totalTime.setText(String.valueOf(item.getTotalTime()));
        return cell;
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
        unfoldedIndexes.clear();
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position)){
            registerFold(position);
        }
        else{
            registerUnfold(position);
        }
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return listener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.listener = defaultRequestBtnClickListener;
    }

    class ViewHolder  {
        private  ImageView image;
        private  TextView nameUnfolded;
        private  TextView nameFolded;
        private  TextView ingredients;
        private  TextView cautions;
        private  TextView totalTime;
        private  TextView calories;
    }
}
