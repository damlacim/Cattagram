package com.damlacim.cattagram.feature.about;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.damlacim.cattagram.data.local.pref.UserPreferences;

/**
 * Created by Damla Cim on 24.04.2023
 */

public class AboutViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UserPreferences userPreferences;

    public AboutViewModelFactory(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AboutViewModel(userPreferences);
    }
}