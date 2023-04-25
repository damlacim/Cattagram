package com.damlacim.cattagram.data.local.sqllite;

import android.provider.BaseColumns;

/**
 * Created by Damla Cim on 24.04.2023
 */

public final class Users {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Users() {}

    /* Inner class that defines the table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }


}

