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

public class DetailKeywordNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_keyword_notes);
        TextView textView = findViewById(R.id.textview);


        Realm realm = Realm.getDefaultInstance();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("KEYWORD");
        int listNumber = bundle.getInt("POS");

        RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime", Sort.DESCENDING).contains("description", s).findAll();
        RealmResults<Keyword> words = realm.where(Keyword.class).findAll();
        String description = notesList.get(listNumber).getDescription();
        textView.setText(description);

        Toast.makeText(getApplicationContext(),words.toString(),Toast.LENGTH_LONG).show();

        ImageButton mainBtn = findViewById(R.id.main5);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        ImageButton deleteBtn = findViewById(R.id.delete2);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Vector<String> v = WriteActivity.FindKeyword2(WriteActivity.FindKeyword1(description));
                        for(int i = 0; i<v.size(); i++){
                            RealmResults<Keyword> keywords = realm.where(Keyword.class).equalTo("category", v.get(i)).findAll();
                            if(keywords.get(0).getNum() == 1) keywords.get(0).deleteFromRealm();
                            else keywords.get(0).setNum(keywords.get(0).getNum()-1);
                        }
                        notesList.get(listNumber).deleteFromRealm();
                        Toast.makeText(getApplicationContext(),"Note deleted",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), KeywordNotesList.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("KEYWORD", s);
                        intent.putExtras(bundle);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(intent);
                    }
                });
            }
        });

    }
}