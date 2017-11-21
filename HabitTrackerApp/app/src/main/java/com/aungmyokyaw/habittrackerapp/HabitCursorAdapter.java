package com.aungmyokyaw.habittrackerapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.aungmyokyaw.habittrackerapp.data.HabitContract.HabitEntry;

import org.w3c.dom.Text;

/**
 * Created by aungmyokyaw on 21/11/17.
 */

public class HabitCursorAdapter extends CursorAdapter{

    public HabitCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_habit,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView times = (TextView) view.findViewById(R.id.display_times);
        TextView habit_name = (TextView) view.findViewById(R.id.display_habit);

        int time_col_index = cursor.getColumnIndex(HabitEntry.COLUMN_TIMES);
        int name_col_index = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);

        times.setText(String.valueOf(cursor.getInt(time_col_index)));
        habit_name.setText(cursor.getString(name_col_index));
    }
}
