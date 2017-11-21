package com.aungmyokyaw.habittrackerapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.aungmyokyaw.habittrackerapp.data.HabitContract;
import com.aungmyokyaw.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Created by aungmyokyaw on 21/11/17.
 */

public class HabitProvider extends ContentProvider{

    private static final int HABITS = 100;
    private static final int HABIT_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(HabitContract.CONTENT_AUTHORITY,HabitContract.PATH_HABIT,HABITS);
        sUriMatcher.addURI(HabitContract.CONTENT_AUTHORITY,HabitContract.PATH_HABIT + "/#",HABIT_ID);
    }
    private HabitDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new HabitDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case HABITS:
                cursor = database.query(
                        HabitEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case HABIT_ID:
                selection = HabitEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                cursor = database.query(
                        HabitEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case HABITS:
                return HabitEntry.CONTENT_LIST_TYPE;
            case HABIT_ID:
                return HabitEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown Uri " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        Uri return_uri;
        int match = sUriMatcher.match(uri);
        switch (match){
            case HABITS:
                long id = database.insert(
                        HabitEntry.TABLE_NAME,
                        null,
                        contentValues
                );
                return_uri = ContentUris.withAppendedId(uri,id);
                break;
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+uri);
        }

        if(return_uri != null){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return return_uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsDeleted;
        int match = sUriMatcher.match(uri);
        switch (match){
            case HABITS:
                rowsDeleted = database.delete(HabitEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case HABIT_ID:
                selection = HabitEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                rowsDeleted = database.delete(HabitEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if(rowsDeleted != -1){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsUpdated;
        int match = sUriMatcher.match(uri);
        switch (match){
            case HABITS:
                rowsUpdated = database.update(
                        HabitEntry.TABLE_NAME,
                        contentValues,
                        selection,
                        selectionArgs
                );
                break;
            case HABIT_ID:
                selection = HabitEntry._ID + "=?";
                selectionArgs = new String[]{
                        String.valueOf(ContentUris.parseId(uri))
                };
                rowsUpdated = database.update(
                        HabitEntry.TABLE_NAME,
                        contentValues,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
        if(rowsUpdated != -1){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsUpdated;
    }
}
