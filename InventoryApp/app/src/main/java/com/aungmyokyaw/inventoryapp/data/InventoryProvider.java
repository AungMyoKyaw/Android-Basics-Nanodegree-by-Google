package com.aungmyokyaw.inventoryapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.aungmyokyaw.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by aungmyokyaw on 22/11/17.
 */

public class InventoryProvider extends ContentProvider{
    private static final int PRODUCTS = 100;
    private static final int PRODUCTS_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY,InventoryContract.PATH_PRODUCT,PRODUCTS);
        sUriMatcher.addURI(InventoryContract.CONTENT_AUTHORITY,InventoryContract.PATH_PRODUCT+"/#",PRODUCTS_ID);
    }
    private InventoryDbHelper mDbHelper;
    @Override
    public boolean onCreate() {
        mDbHelper = new InventoryDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match){
            case PRODUCTS:
                cursor = db.query(
                        InventoryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case PRODUCTS_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] {
                        String.valueOf(ContentUris.parseId(uri))
                };
                cursor = db.query(
                        InventoryEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown Uri"+uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case PRODUCTS:
                return InventoryEntry.CONTENT_LIST_TYPE;
            case PRODUCTS_ID:
                return InventoryEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown Uri " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match){
            case PRODUCTS:
                long id = db.insert(
                        InventoryEntry.TABLE_NAME,
                        null,
                        contentValues
                );
                uri = ContentUris.withAppendedId(uri,id);
                break;
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+uri);
        }
        if(uri != null){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowDeleted;
        int match = sUriMatcher.match(uri);
        switch (match){
            case PRODUCTS:
                rowDeleted = db.delete(InventoryEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case PRODUCTS_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] {
                  String.valueOf(ContentUris.parseId(uri))
                };
                rowDeleted = db.delete(InventoryEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if(rowDeleted != -1){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowUpdated;
        int match = sUriMatcher.match(uri);
        switch (match){
            case PRODUCTS:
                rowUpdated = db.update(InventoryEntry.TABLE_NAME,contentValues,selection,selectionArgs);
                break;
            case PRODUCTS_ID:
                selection = InventoryEntry._ID + "=?";
                selectionArgs = new String[] {
                        String.valueOf(ContentUris.parseId(uri))
                };
                rowUpdated = db.update(InventoryEntry.TABLE_NAME,contentValues,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
        if(rowUpdated != -1){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowUpdated;
    }
}
