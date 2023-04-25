package com.damlacim.cattagram.feature.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.damlacim.cattagram.R;
import com.damlacim.cattagram.data.local.pref.UserPreferences;
import com.damlacim.cattagram.data.local.sqllite.UserDatabaseHelper;
import com.damlacim.cattagram.databinding.FragmentRegisterBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;
    private NavController navController;

    private UserPreferences userPreferences;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserDatabaseHelper databaseHelper = new UserDatabaseHelper(getContext());
        userPreferences = new UserPreferences(getContext());
        viewModel = new RegisterViewModelFactory(databaseHelper, userPreferences).create(RegisterViewModel.class);
        navController = Navigation.findNavController(view);
        initViews();
    }

    private void initViews() {
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), s -> {
            Snackbar.make(binding.getRoot(), s, Snackbar.LENGTH_SHORT).show();
        });

        binding.btnLogin.setOnClickListener(v -> {
            viewModel.register(Objects.requireNonNull(binding.etUsername.getText()).toString(), binding.etPassword.getText().toString());
        });

        viewModel.getSuccessEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                navController.navigate(R.id.action_RegisterFragment_to_main_nav_graph);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
