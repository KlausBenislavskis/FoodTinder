package com.example.foodtinder.ui.Home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodtinder.MainActivity;
import com.example.foodtinder.R;
import com.example.foodtinder.repositories.recipe.RecipeRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {

    private Button button;
    private Button searchButton;
    private TextView errorLabel;
    private TextView emailProfileTextView;
    private TextView nameProfileTextView;
    private ImageView profileImageView;
    private EditText input;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        button = root.findViewById(R.id.sign_out);
        searchButton = root.findViewById(R.id.search_button);
        input = root.findViewById(R.id.editText);
        errorLabel = root.findViewById(R.id.errorLabel);
        emailProfileTextView = root.findViewById(R.id.emailProfileTextView);
        profileImageView = root.findViewById(R.id.profileImageView);
        nameProfileTextView = root.findViewById(R.id.nameProfileTextView);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            nameProfileTextView.setText(currentUser.getDisplayName());
            emailProfileTextView.setText(currentUser.getEmail());
            Picasso.get().load(currentUser.getPhotoUrl()).into(profileImageView);
        }
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
    }

    public void searchRecipe(View view) {
        System.out.println(input.getText().toString());
        String result = input.getText().toString();
        if(input.getText().toString() == null || input.getText().toString().equals("")){
            errorLabel.setVisibility(View.VISIBLE);
            errorLabel.setText("Please input desired type of food");
        }
        else{
            Fragment fragment = new Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("query", input.getText().toString());
            fragment.setArguments(bundle);
            ((MainActivity)getActivity()).navController.navigate(R.id.nav_swipe, bundle);
        }
    }
}