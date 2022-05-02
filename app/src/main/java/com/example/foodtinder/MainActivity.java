package com.example.foodtinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.recipe.RecipeRepository;
import com.example.foodtinder.repositories.userCurrent.CurrentUserRepository;
import com.example.foodtinder.repositories.userFriends.UserFriendRepository;
import com.example.foodtinder.repositories.userRecipe.UserRecipeRepository;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

     public NavController navController;
    AppBarConfiguration appBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationDrawer;
    Toolbar toolbar;
    Button logout_button;
    CurrentUserRepository currentUserRepository;
    UserItemModel userItemModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupNavigation();
        currentUserRepository = CurrentUserRepository.getInstance();
        checkIfSignedIn();


    }
    private void checkIfSignedIn() {
        currentUserRepository.getCurrentUser().observe(this, user -> {
            if (user != null) {
                String message = "Welcome " + user.getDisplayName();
            } else
                startLoginActivity();
        });
    }

    private void startLoginActivity() {

        startActivity(new Intent(this, LoginActivity.class));
        finish();

    }


    public void addFriend(String email)
    {
        UserFriendRepository.getInstance().addFriend(email);
    }
    public void addRecipe(RecipeItemModel itemModel)
    {
        UserRecipeRepository.getInstance().saveRecipe(new UserRecipe(itemModel.getName(), itemModel.getId()));
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        toolbar = findViewById(R.id.toolbar);

    }
    public void signOut()
    {
        CurrentUserRepository.getInstance().signOut(getApplication());

    }
    public void hideActionBar()
    {
        initViews();
        toolbar.setVisibility(View.INVISIBLE);
    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setSupportActionBar(toolbar);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);

    }



    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}