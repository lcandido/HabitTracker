package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.android.habittracker.data.HabitsTrackerContract.HabitsEntry;
import com.example.android.habittracker.data.HabitsTrackerDbHelper;

public class MainActivity extends AppCompatActivity {

    HabitsTrackerDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new HabitsTrackerDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitsEntry._ID,
                HabitsEntry.COLUMN_HABIT_DESCRIPTION,
                HabitsEntry.COLUMN_HABIT_TOTAL_TIMES_DONE
        };

        Cursor cursor = db.query(HabitsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.habitsTextView);

        try {

            displayView.setText("The habits table contains " + cursor.getCount() + " habits.\n\n");
            displayView.append("ID - Description - Total Times Done");

            int idColumnIndex = cursor.getColumnIndex(HabitsEntry._ID);
            int descriptionColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABIT_DESCRIPTION);
            int totalTimesDoneColumnIndex = cursor.getColumnIndex(HabitsEntry.COLUMN_HABIT_TOTAL_TIMES_DONE);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                String description = cursor.getString(descriptionColumnIndex);
                int totalTimesDone = cursor.getInt(totalTimesDoneColumnIndex);

                displayView.append("\n" + id + " - " + description + " - " + totalTimesDone);
            }

        } finally {
            db.close();
        }
    }

    public void insertHabit(View view) {

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitsEntry.COLUMN_HABIT_DESCRIPTION, "Run 5km");
        values.put(HabitsEntry.COLUMN_HABIT_TOTAL_TIMES_DONE, 15);

        try {
            long newRowId = db.insert(HabitsEntry.TABLE_NAME, null, values);
        } finally {
            db.close();
            displayDatabaseInfo();
        }

    }

}
