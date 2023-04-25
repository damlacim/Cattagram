package com.damlacim.cattagram.feature.about;

import androidx.lifecycle.ViewModel;

import com.damlacim.cattagram.data.local.pref.UserPreferences;
import com.damlacim.cattagram.util.SingleLiveEvent;

public class AboutViewModel extends ViewModel {
    private final UserPreferences userPreferences;

    private SingleLiveEvent<Boolean> navigateToLogin = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getNavigateToLoginEvent() {
        return navigateToLogin;
    }

    public AboutViewModel(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void logout() {
        userPreferences.setUserLoggedIn(false);
        navigateToLogin.setValue(true);
    }
}
