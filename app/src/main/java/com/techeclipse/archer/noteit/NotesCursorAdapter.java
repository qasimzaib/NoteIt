package com.techeclipse.archer.noteit;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NotesCursorAdapter extends CursorAdapter {

    public NotesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.note_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String noteText = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NOTES_TEXT));
        String noteDate = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NOTES_CREATED));

        int pos = noteText.indexOf(10);
        if (pos != -1) {
            noteText = noteText.substring(0, pos) + "...";
        }

        TextView nt = (TextView) view.findViewById(R.id.tvNote);
        nt.setText(noteText);

        TextView dt = (TextView) view.findViewById(R.id.tvDate);
        dt.setText(noteDate);
    }
}