package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

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
                long createdTime = System.currentTimeMillis();

                //함수에 description 가져다 씀
                //함수 돌고 나오는 변수(키워드 리스트)에 object변수 연결
                //기본 frequency는 0으로 초기화
                int frequency = 0;
                ArrayList category = new ArrayList();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setDescription(description);
                note.setCreatedTime(createdTime);

//                categorizing - 키워드 추출
                Keyword keyword = realm.createObject(Keyword.class);

                Vector<String> extractedKeyword = new Vector<String>(); //vector로 전환
                String[] split = description.split("\\s|,|\\."); //띄어쓰기, 줄바꿈 split
                for (int i = 0; i < split.length; i++) {
                    String str = split[i];
                    int index_last = str.length() - 1;
                    if (str.charAt(index_last) == '을' || str.charAt(index_last) == '를'
                            || str.charAt(index_last) == '은' || str.charAt(index_last) == '는'
                            || str.charAt(index_last) == '이' || str.charAt(index_last) == '가') {
                        extractedKeyword.add(str.substring(0, index_last));
                    }

                    //중복 삭제 (vector가 키워드를 포함하지 않는다면 add)
                    //하나씩 category Array에 add
                    for (String j : extractedKeyword) {
                        if(!extractedKeyword.contains(j))
                            extractedKeyword.add(j);

                        keyword.setObject(j);
                        keyword.setFrequency(++frequency);
                        category.add(new Keyword(j, frequency));
                    }
                }

                realm.commitTransaction();
                Toast.makeText(getApplicationContext(), "SAVE IT", Toast.LENGTH_SHORT).show();
                finish();

                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });
    }
}