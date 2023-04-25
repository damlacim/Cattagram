package com.damlacim.cattagram.feature.register;

import android.content.ContentValues;
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

public class RegisterViewModel extends ViewModel {
    private final UserDatabaseHelper userDatabaseHelper;
    private final UserPreferences userPreferences;

    public RegisterViewModel(UserDatabaseHelper userDatabaseHelper, UserPreferences userPreferences) {
        this.userDatabaseHelper = userDatabaseHelper;
        this.userPreferences = userPreferences;
    }

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    private final SingleLiveEvent<Boolean> successEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getSuccessEvent() {
        return successEvent;
    }

    public void register(String username, String password) {
        validate(username, password);
        if (validate(username, password)) {
            if (checkIfUsernameExists(username)) {
                errorMessage.setValue("Username already exists");
                successEvent.setValue(false);
            } else {
                createUser(username, password);
            }

        } else {
            successEvent.setValue(false);
        }
    }

    private void createUser(String username, String password) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Users.UserEntry.COLUMN_NAME_USERNAME, username);
        values.put(Users.UserEntry.COLUMN_NAME_PASSWORD, password);

        db.insert(Users.UserEntry.TABLE_NAME, null, values);
        errorMessage.setValue("User created successfully");
        userPreferences.setUserLoggedIn(true);
        successEvent.setValue(true);
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

    public boolean checkIfUsernameExists(String username) {
        SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                Users.UserEntry.COLUMN_NAME_USERNAME
        };

        String selection = Users.UserEntry.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(
                Users.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean usernameExists = cursor.getCount() > 0;
        cursor.close();

        return usernameExists;
    }
}