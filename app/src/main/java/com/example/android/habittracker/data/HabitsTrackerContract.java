package com.example.android.habittracker.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class HabitsTrackerContract {

    // The authority for the habit tracker provider
    static final String AUTHORITY = "com.example.android.habittracker";

    // A content:// style uri to the authority for the inventory provider
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // The path which contains habits data
    static final String PATH_HABITS = "habits";

    public static final class HabitsEntry implements BaseColumns {

        // A content:// style uri to the habits for the habits tracker provider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HABITS);

        // A content type for a list of habits
        static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_HABITS;

        // A content type for a single habit
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_HABITS;

        // Products table name
        static final String TABLE_NAME = "habits";

        // Product's attributes
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_DESCRIPTION = "description";
        public static final String COLUMN_HABIT_TOTAL_TIMES_DONE = "totalTimesDone";

    }
}
