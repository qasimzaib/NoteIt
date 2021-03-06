package com.techeclipse.archer.noteit;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class NotesProvider extends ContentProvider {
    private static final String AUTHORITY = "com.techeclipse.archer.noteit.notesprovider";
    private static final String BASE_PATH = "notes";
    private static final int NOTES = 1;
    private static final int NOTES_ID = 2;
    private static final UriMatcher UM = new UriMatcher(UriMatcher.NO_MATCH);

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH );
    public static final String CONTENT_ITEM_TYPE = "Note";

    static {
        UM.addURI(AUTHORITY, BASE_PATH, NOTES);
        UM.addURI(AUTHORITY, BASE_PATH + "/#", NOTES_ID);
    }

    private SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        DBOpenHelper helper = new DBOpenHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (UM.match(uri) == NOTES_ID) {
            selection = DBOpenHelper.NOTES_ID + "=" + uri.getLastPathSegment();
        }

        return database.query(DBOpenHelper.TABLE_NOTES, DBOpenHelper.ALL_COLUMNS,
                selection, null, null, null,
                DBOpenHelper.NOTES_CREATED + " DESC");
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert (DBOpenHelper.TABLE_NOTES, null, values);
        return Uri.parse (BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete (DBOpenHelper.TABLE_NOTES, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update (DBOpenHelper.TABLE_NOTES, values, selection, selectionArgs);
    }
}