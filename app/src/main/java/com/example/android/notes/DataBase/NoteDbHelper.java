package com.example.android.notes.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.android.notes.DataBase.NotesContract.NoteEntry;
/**
 * Created by KMokgethi on 2017/06/28.
 */


public class NoteDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "notes.db";
    private static NoteDbHelper noteDbInstance;
    private static final int DATABASE_VERSION = 2;

    public static synchronized NoteDbHelper getInstance(Context context) {

        if (noteDbInstance == null) {
            noteDbInstance = new NoteDbHelper(context.getApplicationContext());
        }
        return noteDbInstance;
    }


    private NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_NOTE_TABLE = "CREATE TABLE " + NoteEntry.TABLE_NAME + " (" +
                NoteEntry._ID + " INTEGER PRIMARY KEY," +
                NoteEntry.COLUMN_ITEM_TITLE + " TEXT NOT NULL, " +
                NoteEntry.COLUMN_ITEM_DESCRIPTION + " TEXT NOT NULL " +
                " );";
        db.execSQL(SQL_CREATE_NOTE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NoteEntry.TABLE_NAME);
        onCreate(db);
    }
}
