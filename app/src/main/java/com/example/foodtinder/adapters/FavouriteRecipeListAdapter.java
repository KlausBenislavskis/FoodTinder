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
            viewHolder.name = cell.findViewById(R.id.recipe_title);
            viewHolder.name2 = cell.findViewById(R.id.recipe_title2);
            viewHolder.image = cell.findViewById(R.id.recipe_small_image);
            viewHolder.detailsButton = cell.findViewById(R.id.details_button);
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
        viewHolder.name.setText(item.getName());
        viewHolder.name2.setText(item.getName());
        Picasso.get()
                .load(item.getImage())
                .fit()
                .centerCrop()
                .into(viewHolder.image);

        // set custom btn handler for list item from that item
        if (item.getRequestBtnClickListener() != null) {
            viewHolder.detailsButton.setOnClickListener(item.getRequestBtnClickListener());
        } else {
            // (optionally) add "default" handler if no handler found in item
            viewHolder.detailsButton.setOnClickListener(listener);
        }

        return cell;
    }

    public interface OnClickListener{
        void OnClick(RecipeItemModel recipeItem);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public View.OnClickListener getDefaultRequestBtnClickListener() {
        return listener;
    }

    public void setDefaultRequestBtnClickListener(View.OnClickListener defaultRequestBtnClickListener) {
        this.listener = defaultRequestBtnClickListener;
    }

    class ViewHolder  {
        private  ImageView image;
        private  TextView name;
        private  TextView name2;
        private  TextView detailsButton;

    }
}
