package com.damlacim.cattagram.feature.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.damlacim.cattagram.data.local.pref.UserPreferences;
import com.damlacim.cattagram.data.local.sqllite.UserDatabaseHelper;

/**
 * Created by Damla Cim on 24.04.2023
 */

public class RegisterViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UserDatabaseHelper databaseHelper;
    private final UserPreferences userPreferences;

    public RegisterViewModelFactory(UserDatabaseHelper databaseHelper, UserPreferences userPreferences) {
        this.databaseHelper = databaseHelper;
        this.userPreferences = userPreferences;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RegisterViewModel(databaseHelper, userPreferences);
    }
}