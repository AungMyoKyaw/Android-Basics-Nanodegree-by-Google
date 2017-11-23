package com.aungmyokyaw.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aungmyokyaw.inventoryapp.data.InventoryContract.InventoryEntry;

/**
 * Created by aungmyokyaw on 22/11/17.
 */

public class InventoryCursorAdapter extends CursorAdapter{
    private Context context;

    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_product,viewGroup,false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        ImageView image = view.findViewById(R.id.image_display);
        TextView product_name = view.findViewById(R.id.product_name_display);
        TextView product_price = view.findViewById(R.id.product_price_display);
        TextView product_quantity = view.findViewById(R.id.product_quantity_display);

        //column index
        final int id_idx = cursor.getColumnIndex(InventoryEntry._ID);
        final int img_col_idx = cursor.getColumnIndex(InventoryEntry.COL_IMG_URL);
        final int name_col_idx = cursor.getColumnIndex(InventoryEntry.COL_PRODUCT_NAME);
        final int price_col_idx = cursor.getColumnIndex(InventoryEntry.COL_PRODUCT_PRICE);
        final int quantity_col_idx = cursor.getColumnIndex(InventoryEntry.COL_PRODUCT_QUANTITY);

        //id product
        final int id_product = Integer.parseInt(cursor.getString(id_idx));
        final int quantity_product = Integer.parseInt(cursor.getString(quantity_col_idx));

        //setting textview
        product_name.setText(cursor.getString(name_col_idx));
        product_price.setText(cursor.getString(price_col_idx));
        product_quantity.setText(cursor.getString(quantity_col_idx));

        //setting image view
        byte[] image_blob = cursor.getBlob(img_col_idx);
        Bitmap bm = BitmapFactory.decodeByteArray(image_blob,0,image_blob.length);
        image.setImageBitmap(bm);

        //setting checkout
        ImageButton checkout = (ImageButton) view.findViewById(R.id.product_checkout_display);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri currentUri = ContentUris.withAppendedId(InventoryEntry.CONTENT_URI,id_product);
                ContentValues values = new ContentValues();
                String quantity = String.valueOf(quantity_product - 1);
                if(Integer.parseInt(quantity)>=0){
                    values.put(InventoryEntry.COL_PRODUCT_QUANTITY,quantity);
                    context.getContentResolver().update(currentUri,values,null,null);
                }
            }
        });
    }
}
