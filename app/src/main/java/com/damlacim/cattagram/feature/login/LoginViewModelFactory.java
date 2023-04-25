package com.damlacim.cattagram.feature.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.damlacim.cattagram.data.local.pref.UserPreferences;
import com.damlacim.cattagram.data.local.sqllite.UserDatabaseHelper;
import com.damlacim.cattagram.feature.register.RegisterViewModel;

/**
 * Created by Damla Cim on 24.04.2023
 */


public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UserDatabaseHelper databaseHelper;
    private final UserPreferences userPreferences;

    public LoginViewModelFactory(UserDatabaseHelper databaseHelper, UserPreferences userPreferences) {
        this.databaseHelper = databaseHelper;
        this.userPreferences = userPreferences;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(databaseHelper, userPreferences);
    }
}