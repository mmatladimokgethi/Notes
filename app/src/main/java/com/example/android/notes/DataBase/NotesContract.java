package com.example.android.notes.DataBase;

import android.provider.BaseColumns;


public class NotesContract {

    public static final class NoteEntry implements BaseColumns {

        public static final String TABLE_NAME = "notes";

        public static final String COLUMN_ITEM_TITLE = "title";
        public static final String COLUMN_ITEM_DESCRIPTION = "description";
        public static final String COLUMN_ITEM_ID = "_id";
    }
}
