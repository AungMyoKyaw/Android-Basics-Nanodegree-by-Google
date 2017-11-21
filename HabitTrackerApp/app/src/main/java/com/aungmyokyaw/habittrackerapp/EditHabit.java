package com.aungmyokyaw.habittrackerapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aungmyokyaw.habittrackerapp.data.HabitContract.HabitEntry;

public class EditHabit extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{
    private EditText name,times;
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
        setContentView(R.layout.activity_edit_habit);

        name = (EditText) findViewById(R.id.name);
        times = (EditText) findViewById(R.id.times);

        //set touch listener
        name.setOnTouchListener(mTouchListener);
        times.setOnTouchListener(mTouchListener);

        //save button
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(saveHabit() != null){
                    finish();
                }
            }
        });

        currentUri = getIntent().getData();

        if(currentUri != null){
            setTitle(getString(R.string.edit_label));
            getLoaderManager().initLoader(0,null,this);
        } else {
            setTitle(getString(R.string.new_habit_label));
            invalidateOptionsMenu();
        }
    }

    private Uri saveHabit(){
        String stringName = name.getText().toString().trim();
        String no_times = times.getText().toString().trim();
        Uri newUri;

        if(TextUtils.isEmpty(stringName) || TextUtils.isEmpty(no_times)){
            Toast.makeText(
                    this,
                    getString(R.string.fill_something),
                    Toast.LENGTH_SHORT
            ).show();
            return null;
        }

        //setting content values
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME,stringName);
        values.put(HabitEntry.COLUMN_TIMES,Integer.parseInt(no_times));

        if(currentUri == null){
            newUri = getContentResolver().insert(HabitEntry.CONTENT_URI,values);
            if(newUri == null){
                Toast.makeText(
                        this,
                        getString(R.string.save_error),
                        Toast.LENGTH_SHORT
                ).show();
            } else {
//                Log.i("content uri",newUri.toString());
                Toast.makeText(
                        this,
                        getString(R.string.saved),
                        Toast.LENGTH_SHORT
                ).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(currentUri,values,null,null);
            if(rowsAffected == -1){
                Toast.makeText(
                        this,
                        getString(R.string.update_error),
                        Toast.LENGTH_SHORT
                ).show();
            } else {
                Toast.makeText(
                        this,
                        getString(R.string.updated),
                        Toast.LENGTH_SHORT
                ).show();
            }
            newUri = currentUri;
        }

        return newUri;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(currentUri == null){
            menu.findItem(R.id.delete).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_habit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                if(saveHabit() != null){
                    finish();
                }
                return true;
            case R.id.delete:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                if(!hasChanged){
                    NavUtils.navigateUpFromSameTask(EditHabit.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NavUtils.navigateUpFromSameTask(EditHabit.this);
                    }
                };
                showUnSavedChangeDialog(discardButton);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_TIMES
        };
        return new CursorLoader(
                this,
                currentUri,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//        Log.d("edit", DatabaseUtils.dumpCursorToString(cursor));
        if(cursor.moveToFirst()){
            name.setText(cursor.getString(
                    cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME)));
            times.setText(String.valueOf(
                    cursor.getString(
                            cursor.getColumnIndex(HabitEntry.COLUMN_TIMES))));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        name.setText("");
        times.setText("");
    }

    @Override
    public void onBackPressed() {
        if(!hasChanged){
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        showUnSavedChangeDialog(discardButtonClickListener);
    }

    private void showUnSavedChangeDialog(DialogInterface.OnClickListener onClickDiscard){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_change);
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

    private void showDeleteConfirmationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.del_dialog_message);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getContentResolver().delete(currentUri,null,null);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel_del, new DialogInterface.OnClickListener() {
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
}
