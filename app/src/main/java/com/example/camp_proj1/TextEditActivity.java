package com.example.camp_proj1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


//캘린더의 fab를 누르면 나오는 페이지 구성
public class TextEditActivity extends  AppCompatActivity {
    public String fname=null;
    public String str=null;
    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn;
    public ImageButton return_Btn;
    public TextView diaryTextView,textView3;
    public EditText contextEditText;
    public DatePicker datePicker;
    public String selecteddate,msg,file,date;
    public StringBuffer filecontent;

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

        setContentView(R.layout.postschedule);
        save_Btn=findViewById(R.id.save_Btn);
        contextEditText=findViewById(R.id.contextEditText);
        return_Btn=findViewById(R.id.return_Btn);



       // Intent intent=new Intent(TextEditActivity.this,Fragment3.class);

        datePicker=(DatePicker)findViewById(R.id.dataPicker);
        datePicker.init(2020, 12,1, new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker view, int year,int monthOfYear,int dayOfMonth){
                msg=String.format("%d,%d,%d", year , monthOfYear+1, dayOfMonth);
                fname=msg;

            }
        });

        Intent intent = getIntent();
        file = intent.getStringExtra("content");
        date = intent.getStringExtra("date");

        save_Btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                File file = new File("/data/data/com.example.camp_proj1/files/" + date + ".txt");
                StringBuffer filecontent = new StringBuffer("");
                if (file.exists()) {
                    AlertDialog.Builder msgbuilder = new AlertDialog.Builder(TextEditActivity.this)
                            .setTitle("Warning")
                            .setMessage("You already have a memo on this date. Do you want to overwrite it?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    file.delete();
                                    saveDiary(fname + ".txt");
                                    finish();

                                    //원래있던거 지우고 다시 저장
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            });
                    AlertDialog alert = msgbuilder.create();
                    alert.show();
                } else {
                    saveDiary(fname + ".txt");
                    finish();
                }
            }
        });



            class BtnOnClick implements View.OnClickListener {
                @Override
                public void onClick(View v) {
                    saveDiary(fname + ".txt");
                    finish();
                }
            }
                BtnOnClick btnOnClick = new BtnOnClick();
                return_Btn.setOnClickListener(new BtnOnClick());
            }


            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("WrongConstant")
            public void saveDiary(String readText) {
                try {
                    FileOutputStream fos = openFileOutput(readText, MODE_PRIVATE);
                    String content = contextEditText.getText().toString(); //인풋 받은 것을 스트링 형태로 content에 넣음
                    fos.write((content).getBytes());
                    fos.close(); //파일에 컨텐츠를 넣고 닫음
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




            //파일 저장
        }

