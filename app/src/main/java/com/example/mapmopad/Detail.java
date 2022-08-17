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

import java.security.Key;
import java.util.Vector;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime", Sort.DESCENDING).findAll();
        TextView textView = findViewById(R.id.textview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int listNumber = bundle.getInt("POS");
        String description = notesList.get(listNumber).getDescription();
        textView.setText(description);
        RealmResults<Keyword> words = realm.where(Keyword.class).findAll();
        Toast.makeText(getApplicationContext(), words.toString(), Toast.LENGTH_LONG).show();


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
                        Vector<String> s = WriteActivity.FindKeyword2(WriteActivity.FindKeyword1(description));
                        for(int i = 0; i<s.size(); i++){
                            RealmResults<Keyword> keywords = realm.where(Keyword.class).equalTo("category", s.get(i)).findAll();
                            if(keywords.get(0).getNum() == 1) keywords.get(0).deleteFromRealm();
                            else keywords.get(0).setNum(keywords.get(0).getNum()-1);
                        }
                        notesList.get(listNumber).deleteFromRealm();
                        Intent intent = new Intent(getApplicationContext(), Search.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }
}