package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

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

        Button userName = (Button) findViewById(R.id.userName);
        userName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });

        Button addmemo = (Button) findViewById(R.id.addmemo);
        addmemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                startActivity(intent);
            }
        });

        Button keyword = (Button) findViewById(R.id.keyword);
        keyword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KeywordTest.class);
                startActivity(intent);
            }
        });
    }
}