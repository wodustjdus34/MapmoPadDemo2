package com.example.mapmopad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    RealmResults<Note> notesList;



    public MyAdapter(RealmResults<Note> notesList) {
        this.notesList = notesList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView descriptionOutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_view, parent, false);
        MyAdapter.MyViewHolder vh = new MyAdapter.MyViewHolder(view);
        return vh;

        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.descriptionOutput.setText(note.getDescription());

        };


    @Override
    public int getItemCount() {
        return notesList.size();
    }

}
