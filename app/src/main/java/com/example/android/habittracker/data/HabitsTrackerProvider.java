package com.example.android.habittracker.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.example.android.habittracker.data.HabitsTrackerContract.HabitsEntry;


public class HabitsTrackerProvider extends ContentProvider {

    /** Database helper object */
    private HabitsTrackerDbHelper mDbHelper;

    /** URI matcher code for the content URI for the habits table */
    private static final int HABITS = 100;

    /** URI matcher code for the content URI for a single habit in the habits table */
    private static final int HABIT_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static initializer. This is run the first time anything is called from this class.
    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize.
        sUriMatcher.addURI(
                HabitsTrackerContract.AUTHORITY,
                HabitsTrackerContract.PATH_HABITS,
                HABITS);

        sUriMatcher.addURI(
                HabitsTrackerContract.AUTHORITY,
                HabitsTrackerContract.PATH_HABITS + "/#",
                HABIT_ID);
    }

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        // Create and initialize a HabitsTrackerDbHelper object to gain access to the habits
        // tracker database.
        mDbHelper = new HabitsTrackerDbHelper(getContext());
        return true;
    }


    /**
     * Perform the query for the given URI.
     */
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Gets the data repository in read mode
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {

            // URI matches with the habits list's URI
            case HABITS:

                cursor = database.query(
                        HabitsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            // URI matches with the single habit's URI
            case HABIT_ID:

                selection = HabitsEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};

                cursor = database.query(
                        HabitsEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown Uri: "+ uri);
        }

        // Notify all listeners that the data has changed for the habit content URI
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case HABITS:
                return HabitsEntry.CONTENT_LIST_TYPE;
            case HABIT_ID:
                return HabitsEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
