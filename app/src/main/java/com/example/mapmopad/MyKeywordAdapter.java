package com.example.mapmopad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.RealmResults;

public class MyKeywordAdapter extends  RecyclerView.Adapter<MyKeywordAdapter.MyKeywordViewHolder>{

    Context context;
    RealmResults<Keyword> keywordList;

    public MyKeywordAdapter(Context context, RealmResults<Keyword> keywordList) {
        this.context = context;
        this.keywordList = keywordList;
    }

    @NonNull
    @Override
    public MyKeywordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyKeywordViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_keyword,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyKeywordViewHolder holder, int position) {
        Keyword keyword = keywordList.get(position);
        holder.keywordOutput.setText(keyword.getCategory());
    }

    @Override
    public int getItemCount() {
        return keywordList.size();
    }

    public class MyKeywordViewHolder extends RecyclerView.ViewHolder{
        TextView keywordOutput;

        public MyKeywordViewHolder(@NonNull View itemView) {
            super(itemView);
            keywordOutput = itemView.findViewById(R.id.keywordoutput);
        }
    }
}
