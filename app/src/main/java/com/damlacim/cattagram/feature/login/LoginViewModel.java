package com.damlacim.cattagram.feature.login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.damlacim.cattagram.data.local.pref.UserPreferences;
import com.damlacim.cattagram.data.local.sqllite.UserDatabaseHelper;
import com.damlacim.cattagram.data.local.sqllite.Users;
import com.damlacim.cattagram.util.SingleLiveEvent;

/**
 * Created by Damla Cim on 24.04.2023
 */

public class LoginViewModel extends ViewModel {
    private final UserDatabaseHelper userDatabaseHelper;
    private final UserPreferences userPreferences;

    public LoginViewModel(UserDatabaseHelper userDatabaseHelper, UserPreferences userPreferences) {
        this.userDatabaseHelper = userDatabaseHelper;
        this.userPreferences = userPreferences;
    }


    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    private SingleLiveEvent<Boolean> successEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getSuccessEvent() {
        return successEvent;
    }

    private SingleLiveEvent<Boolean> navigateToHome = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getNavigateToHomeEvent() {
        return navigateToHome;
    }


    public void login(String username, String password) {
        validate(username, password);
        if (validate(username, password)) {
            checkUserCredentials(username, password);
        } else {
            successEvent.setValue(false);
        }
    }

    public boolean validate(String username, String password) {
        if (username.isEmpty()) {
            errorMessage.setValue("Username cannot be empty");
            return false;
        }
        if (password.isEmpty()) {
            errorMessage.setValue("Password cannot be empty");
            return false;
        }
        if (password.length() < 6) {
            errorMessage.setValue("Password must be at least 6 characters");
            return false;
        }
        return true;

    }

    public void checkUserCredentials(String username, String password) {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                Users.UserEntry.COLUMN_NAME_USERNAME,
                Users.UserEntry.COLUMN_NAME_PASSWORD
        };

        String selection = Users.UserEntry.COLUMN_NAME_USERNAME + " = ? AND " +
                Users.UserEntry.COLUMN_NAME_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                Users.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean userExists = cursor.getCount() > 0;
        cursor.close();

        if (userExists) {
            successEvent.setValue(true);
            userPreferences.setUserLoggedIn(true);
        } else {
            successEvent.setValue(false);
        }
    }

    public void isUserLoggedIn() {
        if (userPreferences.isUserLoggedIn()) {
            navigateToHome.setValue(true);
        }
    }
}