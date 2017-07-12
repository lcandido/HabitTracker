package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracker.data.HabitsTrackerContract.HabitsEntry;

/**
 * Created by lcandido on 11/07/17.
 */

public class HabitsTrackerDbHelper extends SQLiteOpenHelper {

    // Name of the database file
    private static final String DATABASE_NAME = "habitstracker.db";

    // Database version. If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    // Habits tracker database helper constructor
    HabitsTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /***
     * Called when the database is created for the first time.
     * @param db    The database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the habits table
        String SQL_CREATE_PRODUCTS_TABLE  = "CREATE TABLE " + HabitsEntry.TABLE_NAME + " (" +
                HabitsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitsEntry.COLUMN_HABIT_DESCRIPTION + " TEXT NOT NULL, " +
                HabitsEntry.COLUMN_HABIT_TOTAL_TIMES_DONE + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    /***
     * Called when the database needs to be upgraded.
     * @param db            The database
     * @param oldVersion    The old database version
     * @param newVersion    The new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
