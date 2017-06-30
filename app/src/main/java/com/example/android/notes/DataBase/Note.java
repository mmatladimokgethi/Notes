package com.example.android.notes.DataBase;

public class Note {
    private String noteTitle;
    private String noteDescription;
    private int noteId;

    public Note(){

    }
    public void setNoteTitle(String itemTitle) {
        this.noteTitle = itemTitle;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public int getNoteId() {
        return noteId;
    }
}
