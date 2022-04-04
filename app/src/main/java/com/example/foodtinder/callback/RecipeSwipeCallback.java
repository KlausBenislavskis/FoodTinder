package com.example.foodtinder.callback;

import androidx.recyclerview.widget.DiffUtil;

import com.example.foodtinder.models.RecipeItemModel;

import java.util.List;

public class RecipeSwipeCallback extends DiffUtil.Callback {

    private List<RecipeItemModel> old, newList;

    public RecipeSwipeCallback(List<RecipeItemModel> old, List<RecipeItemModel> newList) {
        this.old = old;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return old.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition).getImage() == newList.get(newItemPosition).getImage();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition) == newList.get(newItemPosition);
    }
}
