package com.damlacim.cattagram.feature.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.damlacim.cattagram.R;
import com.damlacim.cattagram.data.local.pref.UserPreferences;
import com.damlacim.cattagram.data.local.sqllite.UserDatabaseHelper;
import com.damlacim.cattagram.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private NavController navController;
    private LoginViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        UserDatabaseHelper databaseHelper = new UserDatabaseHelper(getContext());
        UserPreferences userPreferences = new UserPreferences(getContext());
        viewModel = new LoginViewModelFactory(databaseHelper, userPreferences).create(LoginViewModel.class);
        viewModel.isUserLoggedIn();
        initViews();

    }

    private void initViews() {
        binding.tvRegister.setOnClickListener(v -> {
            navController.navigate(R.id.action_FirstFragment_to_RegisterFragment);
        });

        binding.btnLogin.setOnClickListener(v -> {
            viewModel.login(binding.etUsername.getText().toString(), binding.etPassword.getText().toString());
        });

        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), s -> {
            Snackbar.make(binding.getRoot(), s, Snackbar.LENGTH_SHORT).show();
        });

        viewModel.getSuccessEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                navController.navigate(R.id.action_LoginFragment_to_main_nav_graph);
            }
        });

        viewModel.getNavigateToHomeEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                navController.navigate(R.id.action_LoginFragment_to_main_nav_graph);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}