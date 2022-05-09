package com.example.foodtinder.callback;

import androidx.recyclerview.widget.DiffUtil;

import com.example.foodtinder.models.UserRecipe;

import java.util.List;

public class UserRecipeCallback extends DiffUtil.Callback {

    private final List<UserRecipe> old;
    private List<UserRecipe> newList;

    public UserRecipeCallback(List<UserRecipe> old, List<UserRecipe> newList) {
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
        return old.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition) == newList.get(newItemPosition);
    }}