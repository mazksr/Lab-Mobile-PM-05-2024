package com.azka.praktikum8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private ArrayList<Notes> notes;
    private NotesViewInterface notesViewInterface;

    public NotesAdapter(NotesViewInterface notesViewInterface) {
        this.notesViewInterface = notesViewInterface;
    }

    public void setNotes(ArrayList<Notes> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Notes note = notes.get(position);

        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.timeDescription.setText(note.getTimeDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, timeDescription;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.desc);
            timeDescription = itemView.findViewById(R.id.time_desc);
            image = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(v -> {
                if (notesViewInterface != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        notesViewInterface.onItemClickToEdit(pos);
                    }
                }
            });
        }
    }
}
