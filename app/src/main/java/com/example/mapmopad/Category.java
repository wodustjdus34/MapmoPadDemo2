package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button addmemobtn = (Button) findViewById(R.id.addmemo2);
        addmemobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
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

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Keyword> keywords = realm.where(Keyword.class).sort("num", Sort.DESCENDING).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter2 myAdapter2 = new MyAdapter2(keywords);
        recyclerView.setAdapter(myAdapter2);

        myAdapter2.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(),"text",Toast.LENGTH_SHORT).show();
            }
        });


        keywords.addChangeListener(new RealmChangeListener<RealmResults<Keyword>>() {
            @Override
            public void onChange(RealmResults<Keyword> keywords) {
                myAdapter2.notifyDataSetChanged();
            }
        });
    }
}