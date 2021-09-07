package com.example.classapp.Database;

import android.provider.BaseColumns;

public class UserMaster {

    public UserMaster(){

    }

    public static class User implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
