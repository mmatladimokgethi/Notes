package com.example.android.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.notes.DataBase.Note;
import com.example.android.notes.DataBase.NoteDatabase;
import com.example.android.notes.DataBase.NoteDatabaseImplement;

public class AddNoteActivity extends AppCompatActivity {

    private NoteDatabase noteDatabase;
    private EditText titleEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__note);
        noteDatabase = new NoteDatabaseImplement(getApplicationContext());

        titleEditText = (EditText) findViewById(R.id.note_title_edittext);
        descriptionEditText = (EditText) findViewById(R.id.note_description_edittext);

        Button addnote = (Button) findViewById(R.id.add_note_button);

        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
                Note note = new Note();
                note.setNoteTitle(titleEditText.getText().toString());
                note.setNoteDescription(descriptionEditText.getText().toString());
                createNote(note);
            }
        });
    }
    void openMainActivity() {
        Intent addNoteIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(addNoteIntent);
    }

    private void createNote(Note note) {
      if(  noteDatabase.insertNote(note) == true){
          Toast.makeText(getApplicationContext(),"Note added successfully",Toast.LENGTH_SHORT ).show();
      }else {
          Toast.makeText(getApplicationContext(),"Failed to a note",Toast.LENGTH_SHORT ).show();
      }
    }
}


