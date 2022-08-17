package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class KeywordList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_list);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Keyword> keywordList = realm.where(Keyword.class).sort("num", Sort.DESCENDING).findAll();
        //RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime", Sort.DESCENDING).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyKeywordAdapter myKeywordAdapter = new MyKeywordAdapter(getApplicationContext(),keywordList);
        recyclerView.setAdapter(myKeywordAdapter);

        keywordList.addChangeListener(new RealmChangeListener<RealmResults<Keyword>>() {
            @Override
            public void onChange(RealmResults<Keyword> keywords) {
                myKeywordAdapter.notifyDataSetChanged();
            }
        });

        Button mainBtn = findViewById(R.id.main);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}