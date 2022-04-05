package com.example.foodtinder.ui.Favourite_Recipe_List;

import static com.example.foodtinder.mappers.ApiToModel.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodtinder.R;
import com.example.foodtinder.adapters.FavouriteRecipeListAdapter;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.repositories.RecipeRepository;
import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

public class FavouriteRecipeFragment extends Fragment {

    ListView listView;
    FavouriteRecipeListAdapter recipeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favourite_recipe_list, container, false);
        RecipeRepository.getInstance().searchRecipe("chicken");

        listView = root.findViewById(R.id.mainListView);

        List<RecipeItemModel> list = map(RecipeRepository.getInstance().getSearchedRecipe().getValue());
        recipeAdapter = new FavouriteRecipeListAdapter(getContext(), list);
        listView.setAdapter(recipeAdapter);
        recipeAdapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set on click event listener to list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                recipeAdapter.registerToggle(pos);
            }
        });
        return root;
    }
}
