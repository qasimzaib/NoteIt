package com.techeclipse.archer.noteit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NoteIt.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NOTES = "notes";
    public static final String NOTES_ID = "_id";
    public static final String NOTES_TEXT = "noteText";
    public static final String NOTES_CREATED = "noteCreated";

    public static final String[] ALL_COLUMNS = {NOTES_ID, NOTES_TEXT, NOTES_CREATED};

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NOTES + " (" +
                    NOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTES_TEXT + " TEXT, " +
                    NOTES_CREATED + " TEXT default CURRENT_TIMESTAMP" +
                    ")";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS" + TABLE_NOTES);
        onCreate(db);
    }
}