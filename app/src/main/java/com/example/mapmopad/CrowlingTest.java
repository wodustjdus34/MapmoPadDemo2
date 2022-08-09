package com.example.mapmopad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.BreakIterator;

public class CrowlingTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowling_test);
        TextView textView = (TextView) findViewById(R.id.textView);
        final Bundle bundle = new Bundle();

        String URL = "https://m.some.co.kr/analysis/social/association?keyword=%EC%8A%A4%EC%BF%BC%ED%8A%B8&startDate=20220709&endDate=20220808&sources=blog%2cnews%2ctwitter";
        boolean isEmpty;

        new Thread() {
            @Override
            public void run() {
                try {
                    String keyword;
                    //크롤링 할 구문
                    Document doc = Jsoup.connect(URL).get();
                    Elements temele = doc.select("analysis-desc-card-item analysis-desc-card-item-date");
                    boolean isEmpty = temele.isEmpty();
                    if (isEmpty == false) { //null값이 아니면 크롤링 실행
                        keyword = temele.get(0).text(); //크롤링 하면 "현재온도30'c" 이런식으로 뽑아와지기 때문에, 현재온도를 잘라내고 30'c만 뽑아내는 코드
                        bundle.putString("temperature", keyword);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    Handler handler;

    {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                Bundle bundle = msg.getData();    //new Thread에서 작업한 결과물 받기
                BreakIterator textView = null;
                textView.setText(bundle.getString("temperature"));    //받아온 데이터 textView에 출력
            }

            ;
        };
    }

}