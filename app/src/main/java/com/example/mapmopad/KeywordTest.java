package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class KeywordTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_test);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Keyword> keywordList = realm.where(Keyword.class).findAll(); //클래스에서 가져옴
        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyKeywordAdapter myKeywordAdapter = new MyKeywordAdapter(getApplicationContext(),keywordList);
        recyclerView.setAdapter(myKeywordAdapter);

        keywordList.addChangeListener(new RealmChangeListener<RealmResults<Keyword>>() {
            @Override
            public void onChange(RealmResults<Keyword> keywords) {
                myKeywordAdapter.notifyDataSetChanged(); //추가되면 달라짐
            }
        });
    }
}