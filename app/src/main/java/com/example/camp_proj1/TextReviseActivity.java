package com.example.camp_proj1;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


//캘린더의 fab를 누르면 나오는 페이지 구성
public class TextReviseActivity extends  AppCompatActivity {
    public String date=null;
    public String str=null;
    public Button save_Btn;
    public ImageButton return_Btn;
    public EditText contextReviseText;
    public Integer n;
    public StringBuffer filecontent = null;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TextSetting.theme==1) {
            setTheme(R.style.Theme_Camp_proj1);

        }
        if (TextSetting.theme==2) {
            //Utils.changeToTheme(this,Utils.THEME_ORANGE);
            setTheme(R.style.Theme_Camp_proj3);

        }
        if (TextSetting.theme==3) {
            //Utils.changeToTheme(this,Utils.THEME_PINK);
            setTheme(R.style.Theme_Camp_proj4);

        }
        if (TextSetting.theme==4) {
            //Utils.changeToTheme(this,Utils.THEME_BLACK);
            setTheme(R.style.Theme_Camp_proj5);
        }

        if (TextSetting.theme==5) {
            //Utils.changeToTheme(this,Utils.THEME_RED);
            setTheme(R.style.Theme_Camp_proj6);

        }

        setContentView(R.layout.textrevise);
        save_Btn=findViewById(R.id.save_Btn);
        contextReviseText=findViewById(R.id.contextReviseText);
        return_Btn=findViewById(R.id.return_Btn);


        Intent intent = getIntent();
        String file = intent.getStringExtra("content");
        String date = intent.getStringExtra("date");

       //contextReviseText.setText(file,TextView.BufferType.EDITABLE);
       contextReviseText.setText(file);

        //Intent intent=new Intent(TextReviseActivity.this,Fragment3.class);


        save_Btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                saveDiary(date + ".txt");
                finish();
            }

        });

        class BtnOnClick implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                finish();
            }
        }
        BtnOnClick btnOnClick  = new BtnOnClick();
        return_Btn.setOnClickListener(new BtnOnClick());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("WrongConstant")
    public void saveDiary(String readText){
        try{
            FileOutputStream fos = openFileOutput(readText, MODE_PRIVATE);
            String content = contextReviseText.getText().toString(); //인풋 받은 것을 스트링 형태로 content에 넣음
            fos.write((content).getBytes());
            fos.close(); //파일에 컨텐츠를 넣고 닫음
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //파일 저장
}

