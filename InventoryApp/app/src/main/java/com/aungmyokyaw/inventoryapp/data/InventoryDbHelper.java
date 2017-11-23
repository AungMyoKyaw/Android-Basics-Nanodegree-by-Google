package com.aungmyokyaw.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.aungmyokyaw.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by aungmyokyaw on 22/11/17.
 */

public class InventoryDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_INVENTORY_TABLE = "CREATE TABLE "
                + InventoryEntry.TABLE_NAME + " ("
                + InventoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + InventoryEntry.COL_IMG_URL + " BLOB NOT NULL, "
                + InventoryEntry.COL_PRODUCT_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COL_PRODUCT_MODEL + " TEXT NOT NULL, "
                + InventoryEntry.COL_PRODUCT_PRICE + " TEXT NOT NULL, "
                + InventoryEntry.COL_PRODUCT_QUANTITY + " TEXT NOT NULL, "
                + InventoryEntry.COL_SUPPLIER_NAME + " TEXT NOT NULL, "
                + InventoryEntry.COL_SUPPLIER_EMAIL + " TEXT NOT NULL, "
                + InventoryEntry.COL_SUPPLIER_PHONE + " TEXT NOT NULL);";
        sqLiteDatabase.execSQL(CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
