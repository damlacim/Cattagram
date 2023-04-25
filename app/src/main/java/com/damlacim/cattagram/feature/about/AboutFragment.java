package com.damlacim.cattagram.feature.about;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damlacim.cattagram.R;
import com.damlacim.cattagram.data.local.pref.UserPreferences;
import com.damlacim.cattagram.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    private AboutViewModel viewModel;
    private UserPreferences userPreferences;

    private FragmentAboutBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        userPreferences = new UserPreferences(getContext());
        viewModel = new AboutViewModelFactory(userPreferences).create(AboutViewModel.class);
        initViews();
    }

    private void initViews() {
        binding.cardLogout.setOnClickListener(v -> {
            viewModel.logout();
        });

        viewModel.getNavigateToLoginEvent().observe(getViewLifecycleOwner(), aBoolean -> {
            navController.setGraph(R.navigation.nav_graph);
            int startDestination = navController.getGraph().getStartDestination();
            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(startDestination, true)
                    .build();
            navController.navigate(startDestination, null, navOptions);
        });

    }
}