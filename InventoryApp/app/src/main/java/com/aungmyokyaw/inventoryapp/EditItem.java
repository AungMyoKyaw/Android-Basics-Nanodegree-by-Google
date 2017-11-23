package com.aungmyokyaw.inventoryapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aungmyokyaw.inventoryapp.data.InventoryContract.InventoryEntry;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EditItem extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{
    private int PICK_IMAGE = 1;
    private ImageView product_image;
    private byte[] image_url;
    private TextView product_name,
            product_model,product_price,
            product_quantity,supplier_name,
            supplier_email,supplier_phone;
    private Uri currentUri;
    private boolean hasChanged = false;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            hasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        product_name = findViewById(R.id.product_name);
        product_model = findViewById(R.id.product_model);
        product_price = findViewById(R.id.product_price);
        product_quantity = findViewById(R.id.product_quantity);
        supplier_name = findViewById(R.id.supplier_name);
        supplier_email = findViewById(R.id.supplier_email);
        supplier_phone = findViewById(R.id.supplier_phone);

        //set touch listener
        product_name.setOnTouchListener(mTouchListener);
        product_model.setOnTouchListener(mTouchListener);
        product_price.setOnTouchListener(mTouchListener);
        product_quantity.setOnTouchListener(mTouchListener);
        product_name.setOnTouchListener(mTouchListener);
        supplier_email.setOnTouchListener(mTouchListener);
        supplier_phone.setOnTouchListener(mTouchListener);

        //uploadImage
        uploadImage();

        //onclick save
        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        //get current uri
        currentUri = getIntent().getData();

        if(currentUri == null){
            setTitle(R.string.new_product);
            invalidateOptionsMenu();
        } else {
            setTitle(R.string.edit_product);
            //init loader
            getLoaderManager().initLoader(0,null,this);
        }
    }

    private void uploadImage(){
        product_image = findViewById(R.id.product_image);
        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(intent,getString(R.string.choose_image)),
                        PICK_IMAGE);
            }
        });
    }

    private void save(){
        String pName = product_name.getText().toString().trim();
        String pModel = product_model.getText().toString().trim();
        String pPrice = product_price.getText().toString().trim();
        String pQuantity = product_quantity.getText().toString().trim();
        String sName = supplier_name.getText().toString().trim();
        String sEmail = supplier_email.getText().toString().trim();
        String sPhone = supplier_phone.getText().toString().trim();
        Uri newItemUri;

        if(TextUtils.isEmpty(pName) || TextUtils.isEmpty(pModel) ||
                TextUtils.isEmpty(pPrice) || TextUtils.isEmpty(pQuantity) ||
                TextUtils.isEmpty(sName) || TextUtils.isEmpty(sEmail) ||
                TextUtils.isEmpty(sPhone) || image_url.length == 0){
            Toast.makeText(
                    this,
                    getString(R.string.required),
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        //setting content value
        ContentValues values = new ContentValues();
        values.put(InventoryEntry.COL_IMG_URL,image_url);
        values.put(InventoryEntry.COL_PRODUCT_NAME,pName);
        values.put(InventoryEntry.COL_PRODUCT_MODEL,pModel);
        values.put(InventoryEntry.COL_PRODUCT_PRICE,pPrice);
        values.put(InventoryEntry.COL_PRODUCT_QUANTITY,pQuantity);
        values.put(InventoryEntry.COL_SUPPLIER_NAME,sName);
        values.put(InventoryEntry.COL_SUPPLIER_EMAIL,sEmail);
        values.put(InventoryEntry.COL_SUPPLIER_PHONE,sPhone);

        if(currentUri == null){
            newItemUri = getContentResolver().insert(InventoryEntry.CONTENT_URI,values);
            if(newItemUri == null){
                Toast.makeText(
                        this,
                        getString(R.string.save_err),
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        this,
                        getString(R.string.saved),
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            }
        } else {
            int rowupdated = getContentResolver().update(currentUri,values,null,null);
            if(rowupdated != -1){
                Toast.makeText(
                        this,
                        getString(R.string.updated),
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            } else {
                Toast.makeText(
                        this,
                        getString(R.string.update_err),
                        Toast.LENGTH_SHORT
                ).show();
            }
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(currentUri == null){
            menu.findItem(R.id.delete_product).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_product:
                delete();
                break;
            case android.R.id.home:
                if(!hasChanged){
                    NavUtils.navigateUpFromSameTask(EditItem.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NavUtils.navigateUpFromSameTask(EditItem.this);
                    }
                };
                unsavedChange(discardButton);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!hasChanged){
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButton = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        };
        unsavedChange(discardButton);
    }

    private void delete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.del_dialog_msg);
        builder.setPositiveButton(R.string.delete_product, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getContentResolver().delete(currentUri,null,null);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dialogInterface != null){
                    dialogInterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void unsavedChange(DialogInterface.OnClickListener onClickDiscard){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.discard_dialog_msg);
        builder.setPositiveButton(R.string.discard,onClickDiscard);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(dialogInterface != null){
                    dialogInterface.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE &&
                resultCode == RESULT_OK &&
                data != null &&
                data.getData() !=null){
            Uri uri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                product_image.setImageBitmap(bitmap);
                product_image.setPadding(1,1,1,1);
                product_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                hasChanged = true; //make changed

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                image_url = bos.toByteArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                InventoryEntry._ID,
                InventoryEntry.COL_IMG_URL,
                InventoryEntry.COL_PRODUCT_NAME,
                InventoryEntry.COL_PRODUCT_MODEL,
                InventoryEntry.COL_PRODUCT_PRICE,
                InventoryEntry.COL_PRODUCT_QUANTITY,
                InventoryEntry.COL_SUPPLIER_NAME,
                InventoryEntry.COL_SUPPLIER_EMAIL,
                InventoryEntry.COL_SUPPLIER_PHONE
        };
        return new CursorLoader(
                this,
                currentUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if(cursor.moveToFirst()){
            product_name.setText(cursor.getString(
                    cursor.getColumnIndex(InventoryEntry.COL_PRODUCT_NAME)
            ));
            product_model.setText(cursor.getString(
                    cursor.getColumnIndex(InventoryEntry.COL_PRODUCT_MODEL)
            ));
            product_price.setText(cursor.getString(
                    cursor.getColumnIndex(InventoryEntry.COL_PRODUCT_PRICE)
            ));
            product_quantity.setText(cursor.getString(
                    cursor.getColumnIndex(InventoryEntry.COL_PRODUCT_QUANTITY)
            ));
            supplier_name.setText(cursor.getString(
                    cursor.getColumnIndex(InventoryEntry.COL_SUPPLIER_NAME)
            ));
            supplier_email.setText(cursor.getString(
                    cursor.getColumnIndex(InventoryEntry.COL_SUPPLIER_EMAIL)
            ));
            supplier_phone.setText(cursor.getString(
                    cursor.getColumnIndex(InventoryEntry.COL_SUPPLIER_PHONE)
            ));
            image_url = cursor.getBlob(cursor.getColumnIndex(InventoryEntry.COL_IMG_URL));
            Bitmap bm = BitmapFactory.decodeByteArray(image_url,0,image_url.length);
            product_image.setImageBitmap(bm);
            product_image.setPadding(1,1,1,1);
            product_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        product_name.setText("");
        product_model.setText("");
        product_price.setText("");
        product_quantity.setText("");
        supplier_name.setText("");
        supplier_email.setText("");
        supplier_phone.setText("");
        String uri = "@drawable/ic_add_a_photo_white_24dp";
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        product_image.setImageDrawable(res);
    }
}