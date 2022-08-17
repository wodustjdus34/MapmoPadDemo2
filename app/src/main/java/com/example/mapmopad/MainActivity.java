package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
    TextView userNameoutput;
    EditText userNameinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(config);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameoutput = findViewById(R.id.userNameOutput);
        userNameinput = findViewById(R.id.userNameinput);

        ImageButton userNameMaker = findViewById(R.id.userNameBtn);
        userNameMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userNameoutput.setText(userNameinput.getText().toString());
            }
        });

        Button userName = (Button) findViewById(R.id.searchBtn);
        userName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        ImageButton addmemo = findViewById(R.id.addmemo);
        addmemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });

        ImageButton categoryBtn = findViewById(R.id.category);
            categoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), KeywordList.class);
                    startActivity(intent);
                }
            });

        }



    }
