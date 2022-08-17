package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).findAll();
        TextView textView = findViewById(R.id.textview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int listNumber = bundle.getInt("POS");
        textView.setText(notesList.get(listNumber).getDescription());

        ImageButton mainBtn = findViewById(R.id.main);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton deleteBtn = findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        notesList.get(listNumber).deleteFromRealm();
                        Toast.makeText(getApplicationContext(),"deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Search.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }
}