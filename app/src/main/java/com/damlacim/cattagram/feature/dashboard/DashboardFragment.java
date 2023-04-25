package com.damlacim.cattagram.feature.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.damlacim.cattagram.data.model.Cat;
import com.damlacim.cattagram.databinding.FragmentDashboardBinding;
import com.damlacim.cattagram.feature.dashboard.adapter.CatAdapter;
import com.damlacim.cattagram.feature.dashboard.adapter.ItemDecoration;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setupAdapter() {
        CatAdapter catAdapter = new CatAdapter(requireContext(), new Cat().getMockImages());
        binding.rvCatList.setAdapter(catAdapter);
        binding.rvCatList.addItemDecoration(new ItemDecoration(requireContext()));
    }
}