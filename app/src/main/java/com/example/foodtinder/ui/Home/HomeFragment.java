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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {

    private TextView errorLabel;
    private EditText input;
    private HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = root.findViewById(R.id.sign_out);
        Button searchButton = root.findViewById(R.id.search_button);
        input = root.findViewById(R.id.editText);
        errorLabel = root.findViewById(R.id.errorLabel);
        TextView emailProfileTextView = root.findViewById(R.id.emailProfileTextView);
        ImageView profileImageView = root.findViewById(R.id.profileImageView);
        TextView nameProfileTextView = root.findViewById(R.id.nameProfileTextView);
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
            searchRecipe();
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void searchRecipe() {
        String input = this.input.getText().toString();
        if(input.equals("")){
            errorLabel.setVisibility(View.VISIBLE);
            errorLabel.setText("Please input desired type of food");
        }
        else{
            Bundle bundle = homeViewModel.searchRecipe(input);
            ((MainActivity)getActivity()).navController.navigate(R.id.nav_swipe, bundle);
        }
    }
}