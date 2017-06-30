package com.example.android.notes.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KMokgethi on 2017/06/28.
 */

public class NoteDatabaseImplement implements NoteDatabase {
    private SQLiteDatabase sqLiteDatabase;


    public NoteDatabaseImplement(Context context) {
        sqLiteDatabase = NoteDbHelper.getInstance(context).getReadableDatabase();
    }

    @Override
    public boolean insertNote(Note note) {
        boolean isNoteInserted = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesContract.NoteEntry.COLUMN_ITEM_TITLE, note.getNoteTitle());
        contentValues.put(NotesContract.NoteEntry.COLUMN_ITEM_DESCRIPTION, note.getNoteDescription());

        long x = sqLiteDatabase.insert(NotesContract.NoteEntry.TABLE_NAME, null, contentValues);
        if (x != -1) {
            isNoteInserted = true;
        } else {
            isNoteInserted = false;
        }
        return isNoteInserted;
    }

    @Override
    public List<Note> getAllNote() {
        List<Note> NoteList = new ArrayList<>();
        Note note;

        Cursor resultsCursor = sqLiteDatabase.rawQuery("select * from notes order by _id desc", null);
        resultsCursor.moveToFirst();

        while (!resultsCursor.isAfterLast()) {
            note = new Note();
            note.setNoteTitle(resultsCursor.getString(resultsCursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_ITEM_TITLE)));
            note.setNoteDescription(resultsCursor.getString(resultsCursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_ITEM_DESCRIPTION)));
            note.setNoteId(resultsCursor.getInt(resultsCursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_ITEM_ID)));
            NoteList.add(note);
            resultsCursor.moveToNext();
        }
        resultsCursor.close();
        return NoteList;


    }

    @Override
    public boolean deleteNote(int itemId) {
        return sqLiteDatabase.delete(NotesContract.NoteEntry.TABLE_NAME, NotesContract.NoteEntry.COLUMN_ITEM_ID + "=" + itemId, null) > 0;
    }

    @Override
    public boolean updateNote(Note note) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesContract.NoteEntry.COLUMN_ITEM_TITLE, note.getNoteTitle());
        contentValues.put(NotesContract.NoteEntry.COLUMN_ITEM_DESCRIPTION, note.getNoteDescription());
        sqLiteDatabase.update(NotesContract.NoteEntry.TABLE_NAME, contentValues, "_id = ? ", new String[]{Integer.toString(note.getNoteId())});
        return true;
    }
}


