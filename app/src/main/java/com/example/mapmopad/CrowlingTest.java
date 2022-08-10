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
    String keyword;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crowling_test);
        TextView textView = (TextView) findViewById(R.id.textView);
        final Bundle bundle = new Bundle();

        String URL = "https://m.some.co.kr/analysis/social/association?keyword=%EC%8A%A4%EC%BF%BC%ED%8A%B8&startDate=20220709&endDate=20220808&sources=blog%2cnews%2ctwitter";

        new Thread() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    //크롤링 할 구문
                    doc = Jsoup.connect(URL).get();
                    Elements contents = doc.select("#mostRelevantKeyword");
                    keyword += "keyword: " + contents.text();
                    bundle.putString("relevantkeyord", keyword);
                    Message keyword = handler.obtainMessage();
                    keyword.setData(bundle);
                    handler.sendMessage(keyword);

                } catch (IOException e) {
                    e.printStackTrace();
                    }
            }
        }.start();
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message keyword) {
            Bundle bundle = keyword.getData();    //new Thread에서 작업한 결과물 받기
            textView.setText(bundle.getString("relevantkeyord"));    //받아온 데이터 textView에 출력
        };
    };

}