package com.example.android.notes;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.notes.DataBase.Note;
import com.example.android.notes.DataBase.NoteDatabase;
import com.example.android.notes.DataBase.NoteDatabaseImplement;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment{
    private NoteAdapter noteAdapter;
    private android.support.v7.widget.RecyclerView RecyclerView;
    private NoteDatabase noteDatabase;
    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        noteDatabase = new NoteDatabaseImplement(getActivity().getApplicationContext());
        noteAdapter = new NoteAdapter(loadNotes(),getContext(),noteDatabase);
        RecyclerView = (android.support.v7.widget.RecyclerView) rootView.findViewById(R.id.notes_recycler_view);
        RecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLinearLayoutManager
                = new LinearLayoutManager(getActivity().getApplicationContext());
        RecyclerView.setLayoutManager(mLinearLayoutManager);
        RecyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.setAdapter(noteAdapter);

        FloatingActionButton addNewNoteFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_new_note_fab);
        addNewNoteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNoteActivity();
            }
        });
        return rootView;
    }



    void openAddNoteActivity() {
        Intent addNoteIntent = new Intent(getActivity().getApplicationContext(), AddNoteActivity.class);
        startActivity(addNoteIntent);
    }

    List<Note> loadNotes(){
        return noteDatabase.getAllNote();
    }

}
