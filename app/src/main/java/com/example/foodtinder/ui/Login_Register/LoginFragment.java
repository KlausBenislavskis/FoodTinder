package com.example.foodtinder.ui.Login_Register;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.foodtinder.MainActivity;
import com.example.foodtinder.R;
import com.example.foodtinder.databinding.FragmentLoginBinding;
public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private Button button;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button = root.findViewById(R.id.button_login);
        button.setOnClickListener(v-> ((MainActivity)this.getActivity()).navController.navigate(R.id.nav_home));
        return root;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}