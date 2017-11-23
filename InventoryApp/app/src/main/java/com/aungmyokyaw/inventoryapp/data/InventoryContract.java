package com.aungmyokyaw.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by aungmyokyaw on 22/11/17.
 */

public final class InventoryContract {
    public InventoryContract() {

    }

    public static final String CONTENT_AUTHORITY = "com.aungmyokyaw.inventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCT = "product";

    public static final class InventoryEntry implements BaseColumns{
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PRODUCT);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/"
                + CONTENT_AUTHORITY + "/"
                + PATH_PRODUCT;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/"
                + CONTENT_AUTHORITY + "/"
                + PATH_PRODUCT;
        public static final String TABLE_NAME = "products";
        public static final String _ID = BaseColumns._ID;
        public static final String COL_IMG_URL = "img";
        public static final String COL_PRODUCT_NAME = "product_name";
        public static final String COL_PRODUCT_MODEL = "product_model";
        public static final String COL_PRODUCT_PRICE = "product_price";
        public static final String COL_PRODUCT_QUANTITY = "product_quantity";
        public static final String COL_SUPPLIER_NAME = "supplier_name";
        public static final String COL_SUPPLIER_EMAIL = "supplier_email";
        public static final String COL_SUPPLIER_PHONE = "supplier_phone";
    }
}
