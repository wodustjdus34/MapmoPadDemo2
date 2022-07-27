package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class WriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);



        EditText descriptionInput = findViewById(R.id.descriptionInput);
        Button savememo = (Button) findViewById(R.id.savememo);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        savememo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = descriptionInput.getText().toString();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setDescription(description);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "SAVE IT", Toast.LENGTH_SHORT).show();
                finish();

                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });
    }
}