package com.example.android.notes;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.notes.DataBase.Note;
import com.example.android.notes.DataBase.NoteDatabase;

import java.util.List;

/**
 * Created by KMokgethi on 2017/06/29.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {


    private List<Note> notes;
    private Context context;
    private NoteDatabase noteDatabase;

    public NoteAdapter(List<Note> notes,Context context,NoteDatabase noteDatabase)
    {
        this.notes = notes;
        this.context = context;
        this.noteDatabase = noteDatabase;
    }

    @Override
    public NoteAdapter.NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new NoteHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.NoteHolder holder, final int position) {
        holder.title.setText(notes.get(position).getNoteTitle());
        holder.description.setText(notes.get(position).getNoteDescription());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"Note Deleted Successfully",Toast.LENGTH_SHORT).show();
                notes.remove(notes);
                deleteNote(notes.get(position).getNoteId());
                notifyItemRemoved(position);
                return true;
            }
        });
    }

    private void deleteNote(int noteId) {
        noteDatabase.deleteNote(noteId);
        reloadData();
    }

    private void reloadData() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;
        private TextView description;
        private CardView cardView;

        public NoteHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.note_title_edittext);
            this.description = (TextView) itemView.findViewById(R.id.note_description_edittext);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
