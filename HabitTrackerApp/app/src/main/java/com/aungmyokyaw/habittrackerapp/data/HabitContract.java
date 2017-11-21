package com.aungmyokyaw.habittrackerapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.net.URI;

/**
 * Created by aungmyokyaw on 21/11/17.
 */

public final class HabitContract {

    private HabitContract(){

    }

    public static final String CONTENT_AUTHORITY = "com.aungmyokyaw.habittrackerapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_HABIT = "habit";

    public static  final class HabitEntry implements BaseColumns{
        public final static Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_HABIT);
        public final static String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
                + "/"
                + CONTENT_AUTHORITY
                + "/"
                + PATH_HABIT;
        public final static String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
                + "/"
                + CONTENT_AUTHORITY
                + "/"
                + PATH_HABIT;
        public final static String TABLE_NAME = "habit";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME = "name";
        public final static String COLUMN_TIMES = "times";
    }
}
