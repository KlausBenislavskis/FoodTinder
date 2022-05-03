package com.example.foodtinder.ui.Home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodtinder.MainActivity;
import com.example.foodtinder.R;
import com.example.foodtinder.databinding.FragmentHomeBinding;
import com.example.foodtinder.repositories.recipe.RecipeRepository;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button button;
    Button searchButton;
    TextView errorLabel;
    EditText input;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button = root.findViewById(R.id.sign_out);
        searchButton = root.findViewById(R.id.search_button);
        input = root.findViewById(R.id.editText);
        errorLabel = root.findViewById(R.id.errorLabel);
        button.setOnClickListener(v->{
            ((MainActivity)getActivity()).signOut();
        });
        searchButton.setOnClickListener(v->{
            searchRecipe(root);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void searchRecipe(View view) {
        String result = input.getText().toString();
        if(input.getText().toString() == null || input.getText().toString().equals("")){
            errorLabel.setVisibility(View.VISIBLE);
            errorLabel.setText("Please input desired type of food");
        }
        else{
            RecipeRepository.getInstance().setQuery(input.getText().toString());
            ((MainActivity)getActivity()).navController.navigate(R.id.nav_swipe);
        }
    }
}