package com.hufs.hw6_1_sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefs"; // 공유 프레퍼런스 이름
    TextView name; // 참조 변수 생성
    EditText value;
    String imageName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView)findViewById(R.id.TextView01);
        value = (EditText)findViewById(R.id.EditText01);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0); //공유 프레퍼런스를 얻음
        imageName = settings.getString("imageName", ""); // 프레퍼런스 파일에서 값을 읽어옴
        value.setText(imageName); // 에딧 텍스트의 내용을 정함
    }

    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit(); // 편집이 가능한 객체를 가져옴
        imageName = value.getText().toString(); // 에딧 텍스트의 내용을 문자열로 변환
        editor.putString("imageName", imageName); // 에디터에 내용을 넣어줌
        editor.commit(); // 싱크를 맞추기 위해 에디터에 커밋을 날림
    }
}