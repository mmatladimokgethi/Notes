package com.example.android.notes.DataBase;

import java.util.List;

/**
 * Created by KMokgethi on 2017/06/28.
 */

public interface NoteDatabase {
    boolean insertNote(Note note);

    List<Note> getAllNote();

    boolean deleteNote(int itemId);

    boolean updateNote(Note note);
}
