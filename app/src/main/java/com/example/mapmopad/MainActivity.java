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

import java.security.Key;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

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
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Keyword> keywords = realm.where(Keyword.class).sort("num", Sort.DESCENDING).findAll();
        RealmResults<Note> notesList = realm.where(Note.class).findAll();

        if (keywords.size() == 0 || notesList.size() < 5) {

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
            userName.setOnClickListener(new View.OnClickListener() {
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


        else if (keywords.size() == 1) {
            setContentView(R.layout.activity_main1);
            userNameoutput = findViewById(R.id.userNameOutput);
            userNameinput = findViewById(R.id.userNameinput);
            TextView category1 = findViewById(R.id.category1);
            category1.setText(keywords.get(0).getCategory());

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userNameoutput.setText(userNameinput.getText().toString());
                }
            });

            Button userName = (Button) findViewById(R.id.searchBtn);
            userName.setOnClickListener(new View.OnClickListener() {
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


        else if (keywords.size() == 2) {
            setContentView(R.layout.activity_main2);
            userNameoutput = findViewById(R.id.userNameOutput);
            userNameinput = findViewById(R.id.userNameinput);
            TextView category1 = findViewById(R.id.category1);
            TextView category2 = findViewById(R.id.category2);
            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userNameoutput.setText(userNameinput.getText().toString());
                }
            });

            Button userName = (Button) findViewById(R.id.searchBtn);
            userName.setOnClickListener(new View.OnClickListener() {
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

        else if (keywords.size() == 3) {
            setContentView(R.layout.activity_main3);
            userNameoutput = findViewById(R.id.userNameOutput);
            userNameinput = findViewById(R.id.userNameinput);
            TextView category1 = findViewById(R.id.category1);
            TextView category2 = findViewById(R.id.category2);
            TextView category3 = findViewById(R.id.category3);
            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());
            category3.setText(keywords.get(2).getCategory());

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userNameoutput.setText(userNameinput.getText().toString());
                }
            });

            Button userName = (Button) findViewById(R.id.searchBtn);
            userName.setOnClickListener(new View.OnClickListener() {
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

        else if (keywords.size() == 4) {
            setContentView(R.layout.activity_main4);
            userNameoutput = findViewById(R.id.userNameOutput);
            userNameinput = findViewById(R.id.userNameinput);
            TextView category1 = findViewById(R.id.category1);
            TextView category2 = findViewById(R.id.category2);
            TextView category3 = findViewById(R.id.category3);
            TextView category4 = findViewById(R.id.category4);
            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());
            category3.setText(keywords.get(2).getCategory());
            category4.setText(keywords.get(3).getCategory());


            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userNameoutput.setText(userNameinput.getText().toString());
                }
            });

            Button userName = (Button) findViewById(R.id.searchBtn);
            userName.setOnClickListener(new View.OnClickListener() {
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


        else {
            setContentView(R.layout.activity_main5);
            userNameoutput = findViewById(R.id.userNameOutput);
            userNameinput = findViewById(R.id.userNameinput);

            TextView category1 = findViewById(R.id.category1);
            TextView category2 = findViewById(R.id.category2);
            TextView category3 = findViewById(R.id.category3);
            TextView category4 = findViewById(R.id.category4);
            TextView category5 = findViewById(R.id.category5);

            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());
            category3.setText(keywords.get(2).getCategory());
            category4.setText(keywords.get(3).getCategory());
            category5.setText(keywords.get(4).getCategory());

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userNameoutput.setText(userNameinput.getText().toString());
                }
            });

            Button userName = (Button) findViewById(R.id.searchBtn);
            userName.setOnClickListener(new View.OnClickListener() {
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

}
