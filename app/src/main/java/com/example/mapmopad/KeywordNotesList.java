package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class KeywordNotesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword_notes_list);

        ImageButton addmemobtn = findViewById(R.id.addmemo2);
        addmemobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });

        ImageButton mainBtn = findViewById(R.id.main);
        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //String s = "it will be a keyword";
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String s = bundle.getString("KEYWORD");
        TextView keywordName = findViewById(R.id.category);
        keywordName.setText(s);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime", Sort.DESCENDING).contains("description", s).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyKeywordNotesAdapter myKeywordNotesAdapter = new MyKeywordNotesAdapter(notesList);
        recyclerView.setAdapter(myKeywordNotesAdapter);

        myKeywordNotesAdapter.setOnItemClickListener(new MyKeywordNotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), DetailKeywordNotes.class);
                Bundle bundle = new Bundle();
                bundle.putString("KEYWORD", s);
                bundle.putInt("POS", position);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes) {
                myKeywordNotesAdapter.notifyDataSetChanged();
            }
        });

    }
}