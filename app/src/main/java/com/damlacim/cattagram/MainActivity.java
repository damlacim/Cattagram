package com.damlacim.cattagram;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.damlacim.cattagram.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            setVisibleBottomNavigation(destination.getId() == R.id.dashboardFragment || R.id.aboutFragment == destination.getId());
            if (destination.getId() == R.id.dashboardFragment || R.id.aboutFragment == destination.getId()) {
                if (navController.getGraph().getId() != R.id.main_nav_graph) {
                    navController.setGraph(R.navigation.main_nav_graph);
                }
            } else {
                if (navController.getGraph().getId() != R.id.nav_graph) {
                    navController.setGraph(R.navigation.nav_graph);
                }
            }
        });
    }

    private void setVisibleBottomNavigation(boolean isVisible) {
        if (isVisible) {
            binding.bottomNavigationView.setVisibility(View.VISIBLE);

        } else {
            binding.bottomNavigationView.setVisibility(View.GONE);
        }
    }
}

