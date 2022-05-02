package com.example.foodtinder.repositories.userRecipe;

import androidx.annotation.NonNull;

import com.example.foodtinder.models.UserRecipe;
import com.example.foodtinder.repositories.userCurrent.CurrentUserRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserRecipeRepository {
    private static UserRecipeRepository instance;
    private DatabaseReference reference;
    private UserRecipeLiveData recipes;
    private String id;

    private UserRecipeRepository() {
    }

    public static synchronized UserRecipeRepository getInstance() {
        if (instance == null) {
            instance = new UserRecipeRepository();
        }
        return instance;
    }


    public void init(String email) {
        if(!email.equals("")) {
            CurrentUserRepository currentUserRepository = CurrentUserRepository.getInstance();

            reference = getUserRecipeDbReference(FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("users"),currentUserRepository.getSafeCurrentUserEmail(email));
        }else
            reference = getUserRecipeDbReference(FirebaseDatabase.getInstance("https://foodtinder-b3f74-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("users"),getCurrentUserSafeEmail());

        recipes = new UserRecipeLiveData(reference);
    }
    public void saveRecipe(UserRecipe recipe) {
        ArrayList<UserRecipe> recipesList = recipes.getValue();
        if(recipesList == null) {
            recipesList = new ArrayList<>();
        }
        recipesList.add(recipe);
        reference.setValue(recipesList);
    }

    public UserRecipeLiveData getRecipes() {
        return recipes;
    }

    @NonNull
    private DatabaseReference getUserRecipeDbReference(DatabaseReference myRef, String email) {
        return myRef
                .child(email)
                .child("favouriteRecipes");
    }

    private String getCurrentUserSafeEmail() {
        CurrentUserRepository currentUserRepository = CurrentUserRepository.getInstance();
        return currentUserRepository.getSafeCurrentUserEmail(currentUserRepository.getCurrentUser().getValue().getEmail());
    }

    public void saveId(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }



}
