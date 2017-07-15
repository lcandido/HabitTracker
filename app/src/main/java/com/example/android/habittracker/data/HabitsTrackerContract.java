package com.example.android.habittracker.data;

import android.provider.BaseColumns;

public class HabitsTrackerContract {

    public static final class HabitsEntry implements BaseColumns {

        // Habits table name
        public static final String TABLE_NAME = "habits";

        // Habits' attributes
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_DESCRIPTION = "description";
        public static final String COLUMN_HABIT_TOTAL_TIMES_DONE = "totalTimesDone";

    }
}
