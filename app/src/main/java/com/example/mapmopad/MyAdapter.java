package com.example.mapmopad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    RealmResults<Note> notesList;

    private final int VIEW_TYPE_NOTE = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public MyAdapter(Context context, RealmResults<Note> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_NOTE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }

        //삭제
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                PopupMenu menu = new PopupMenu(context, v);
//                menu.getMenu().add("Delete");
//                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if(item.getTitle().equals("Delete")) {
//                            Realm realm = Realm.getDefaultInstance();
//                            realm.beginTransaction();
//                            note.deleteFromRealm();
//                            realm.commitTransaction();
//                            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show();
//                        }
//                        return true;
//                    }
//                });
//                menu.show();
//
//                return true;
//            }
//        });
    }

    @Override
    public int getItemViewType(int position) {
        return notesList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_NOTE;
    }

    @Override
    public int getItemCount() {
        return notesList == null ? 0 : notesList.size();
    }

    private void showLoadingView(LoadingViewHolder holder, int position) {

    }

    private void populateItemRows(ItemViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.descriptionOutput.setText(note.getDescription());
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView descriptionOutput;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
        }

//        public void setItem(String item) {
//            descriptionOutput.setText(item);
//        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

}