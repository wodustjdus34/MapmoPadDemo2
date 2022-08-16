package com.example.mapmopad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Key;
import java.util.List;
import java.util.Vector;

import io.realm.Realm;
import io.realm.RealmResults;

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

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setDescription(description);
                note.setCreatedTime(createdTime);

                Vector<String> s = FindKeyword2(FindKeyword1(description));
                for(int i = 0; i<s.size(); i++){
                    RealmResults<Keyword> keywords = realm.where(Keyword.class).contains("category",s.get(i)).findAll();
                    if(keywords.isEmpty()){
                    Keyword keyword = realm.createObject(Keyword.class);
                    keyword.setCategory(s.get(i));
                    keyword.setNum(1);}
                    else{
                        keywords.setInt("num",keywords.get(0).getNum()+1);
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
    // (예 : 사과를 먹고 싶다. 사과는 몸에 좋다. 바나나는 내 취향이 아니다.)
    // 단어 나누기 (예 : {'사과를','먹고', '싶다', '', '사과는', '몸에', '좋다', '', '바나나는', '내', '취향이', '아니다'} )
    public String[] FindKeyword1(String s) {
        String[] list = s.split("\\s|,|\\.");
        return list;
    }

    // 목적어 추출하기 (예 : {'사과','바나나'}
    public Vector<String> FindKeyword2(String[] s){
        Vector<String> v = new Vector<>();

        for(int i = 0; i< s.length; i++){
            if(!s[i].equals("")){
                String sSub = s[i].substring(0, s[i].length()-1);
                if ( s[i].endsWith("을")&&!v.contains(sSub)){
                    v.add(sSub);
                }
            }
        }
        return v;
    }

    // 나눈 단어끼리 비교하기
    /*public Vector<String> FindKeyword3(String[] s1, String s2) {
        Vector<String> list = new Vector<>();
        Vector<String> l = new Vector<>();
        for (int i = 0; i<s1.length; i++)
    }*/
}