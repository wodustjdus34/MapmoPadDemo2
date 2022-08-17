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
import android.widget.Toast;

import java.security.Key;

import io.realm.Realm;
import io.realm.RealmChangeListener;
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

        RealmResults<User> users = realm.where(User.class).findAll();
        RealmResults<Keyword> keywords = realm.where(Keyword.class).sort("num", Sort.DESCENDING).findAll();
        RealmResults<Note> notesList = realm.where(Note.class).findAll();

        keywords.addChangeListener(new RealmChangeListener<RealmResults<Keyword>>() {
            @Override
            public void onChange(RealmResults<Keyword> results) {
                keywords.size();
            }
        });

        if (keywords.size() == 0 || notesList.size() < 5) {
            setContentView(R.layout.activity_main);
            userNameoutput = findViewById(R.id.userNameOutput);
            userNameinput = findViewById(R.id.userNameinput);

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            if(!users.isEmpty()) {
                userNameinput.setText(users.get(0).getName());
                userNameoutput.setText(users.get(0).getName());
            }
            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String username = userNameinput.getText().toString();
                    realm.beginTransaction();
                    if(users.isEmpty()) {
                        User user = realm.createObject(User.class);
                        user.setName(username);
                    }
                    else users.setString("name", username);
                    realm.commitTransaction();
                    finish();
                    Toast.makeText(getApplicationContext(), "Name changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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
            Button category1 = findViewById(R.id.category1);
            category1.setText(keywords.get(0).getCategory());

            category1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category1, view); }
            });


            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            if(!users.isEmpty()) {
                userNameinput.setText(users.get(0).getName());
                userNameoutput.setText(users.get(0).getName());
            }

            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = userNameinput.getText().toString();
                    realm.beginTransaction();
                    if(users.isEmpty()) {
                        User user = realm.createObject(User.class);
                        user.setName(username);
                        }
                    else users.setString("name", username);

                    realm.commitTransaction();
                    finish();
                    Toast.makeText(getApplicationContext(),"Name changed",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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
            Button category1 = findViewById(R.id.category1);
            Button category2 = findViewById(R.id.category2);
            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());

            category1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category1, view); }
            });
            category2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category2, view); }
            });

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            if(!users.isEmpty()) {
                userNameinput.setText(users.get(0).getName());
                userNameoutput.setText(users.get(0).getName());
            }

            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = userNameinput.getText().toString();
                    realm.beginTransaction();
                    if(users.isEmpty()) {
                        User user = realm.createObject(User.class);
                        user.setName(username);
                    }
                    else users.setString("name", username);
                    realm.commitTransaction();
                    finish();
                    Toast.makeText(getApplicationContext(), "Name changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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
            Button category1 = findViewById(R.id.category1);
            Button category2 = findViewById(R.id.category2);
            Button category3 = findViewById(R.id.category3);
            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());
            category3.setText(keywords.get(2).getCategory());

            category1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category1, view); }
            });
            category2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category2, view); }
            });
            category3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category3, view); }
            });

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            if(!users.isEmpty()) {
                userNameinput.setText(users.get(0).getName());
                userNameoutput.setText(users.get(0).getName());
            }

            userNameMaker.setOnClickListener(new View.OnClickListener() {
                String username = userNameinput.getText().toString();
                @Override
                public void onClick(View v) {
                    realm.beginTransaction();
                    if(users.isEmpty()) {
                        User user = realm.createObject(User.class);
                        user.setName(username);
                    }
                    else users.setString("name", username);
                    realm.commitTransaction();
                    finish();
                    Toast.makeText(getApplicationContext(), "Name changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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
            Button category1 = findViewById(R.id.category1);
            Button category2 = findViewById(R.id.category2);
            Button category3 = findViewById(R.id.category3);
            Button category4 = findViewById(R.id.category4);
            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());
            category3.setText(keywords.get(2).getCategory());
            category4.setText(keywords.get(3).getCategory());

            category1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category1, view); }
            });
            category2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category2, view); }
            });
            category3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category3, view); }
            });
            category4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category4, view); }
            });

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);
            if(!users.isEmpty()) {
                userNameinput.setText(users.get(0).getName());
                userNameoutput.setText(users.get(0).getName());
            }
            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = userNameinput.getText().toString();
                    realm.beginTransaction();
                    if(users.isEmpty()) {
                        User user = realm.createObject(User.class);
                        user.setName(username);
                    }
                    else users.setString("name", username);
                    realm.commitTransaction();
                    finish();
                    Toast.makeText(getApplicationContext(), "Name changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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

            Button category1 = findViewById(R.id.category1);
            Button category2 = findViewById(R.id.category2);
            Button category3 = findViewById(R.id.category3);
            Button category4 = findViewById(R.id.category4);
            Button category5 = findViewById(R.id.category5);

            category1.setText(keywords.get(0).getCategory());
            category2.setText(keywords.get(1).getCategory());
            category3.setText(keywords.get(2).getCategory());
            category4.setText(keywords.get(3).getCategory());
            category5.setText(keywords.get(4).getCategory());

            category1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category1, view); }
            });
            category2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category2, view); }
            });
            category3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category3, view); }
            });
            category4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category4, view); }
            });
            category5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onCategoryClick(category5, view); }
            });

            ImageButton userNameMaker = findViewById(R.id.userNameBtn);

            if(!users.isEmpty()) {
                userNameinput.setText(users.get(0).getName());
                userNameoutput.setText(users.get(0).getName());
            }

            userNameMaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = userNameinput.getText().toString();
                    realm.beginTransaction();
                    if(users.isEmpty()) {
                        User user = realm.createObject(User.class);
                        user.setName(username);
                    }
                    else users.setString("name", username);
                    realm.commitTransaction();
                    finish();
                    Toast.makeText(getApplicationContext(), "Name changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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

    public void onCategoryClick(Button button, View v) {
        String s = button.getText().toString();
        Intent intent = new Intent(getApplicationContext(), KeywordNotesList.class);
        Bundle bundle = new Bundle();
        bundle.putString("KEYWORD", s);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
    }

}
