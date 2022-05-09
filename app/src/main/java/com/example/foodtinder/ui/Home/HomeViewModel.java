package com.example.foodtinder.ui.Home;

import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;

public class HomeViewModel extends AndroidViewModel {
    public HomeViewModel(Application app) {
        super(app);
    }
    public Bundle searchRecipe(String query) {
        Fragment fragment = new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("query", query);
        fragment.setArguments(bundle);
        return bundle;
    }
}
