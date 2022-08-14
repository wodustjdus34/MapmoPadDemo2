package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).findAll();
        TextView textView = findViewById(R.id.textview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int listNumber = bundle.getInt("POS");
        textView.setText(notesList.get(listNumber).getDescription());

    }
}