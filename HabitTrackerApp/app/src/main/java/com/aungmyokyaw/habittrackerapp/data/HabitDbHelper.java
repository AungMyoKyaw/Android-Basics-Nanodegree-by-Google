package com.aungmyokyaw.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.aungmyokyaw.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Created by aungmyokyaw on 21/11/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "habit.db";
    private static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_HABIT_TABLE = "CREATE TABLE "
                + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_TIMES + " INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
