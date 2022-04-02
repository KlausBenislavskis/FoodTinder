package com.example.foodtinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodtinder.repositories.RecipeRepository;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.testH);
        RecipeRepository.getInstance().searchRecipe("chicken");
        i = 0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(RecipeRepository.getInstance().getSearchedRecipe().getValue().get(i).recipe.label);
                i++;
                if (i >= 19) {
                    i = 0;
                    RecipeRepository.getInstance().searchRecipe("chicken");

                }
            }
        });
    }
}