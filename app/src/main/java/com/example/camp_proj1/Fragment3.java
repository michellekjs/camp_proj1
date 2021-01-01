package com.example.camp_proj1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Fragment3 extends Fragment  {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

      // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment3 calender = new fragment3();
        
        return inflater.inflate(R.layout.fragment_3, container, false);
    }


    public class fragment3 extends AppCompatActivity {

        public String fname=null;
        public String str=null;
        public CalendarView calendarView;
        public Button cha_Btn,del_Btn,save_Btn;
        public TextView diaryTextView,textView2,textView3;
        public EditText contextEditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_3);
            calendarView=findViewById(R.id.calendarView);
            diaryTextView=findViewById(R.id.diaryTextView);
            save_Btn=findViewById(R.id.save_Btn);
            del_Btn=findViewById(R.id.del_Btn);
            cha_Btn=findViewById(R.id.cha_Btn);
            textView2=findViewById(R.id.textView2);
            textView3=findViewById(R.id.textView3);
            contextEditText=findViewById(R.id.contextEditText);
            //로그인 및 회원가입 엑티비티에서 이름을 받아옴
            Intent intent=getIntent();
            String name="qwe";
            final String userID=intent.getStringExtra("userID");
            textView3.setText(name+"님의 달력 일기장");

            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    diaryTextView.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    diaryTextView.setText(String.format("%d / %d / %d",year,month+1,dayOfMonth));
                    contextEditText.setText("");
                    checkDay(year,month,dayOfMonth,userID);
                }
            });
            save_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveDiary(fname);
                    str=contextEditText.getText().toString();
                    textView2.setText(str);
                    save_Btn.setVisibility(View.INVISIBLE);
                    cha_Btn.setVisibility(View.VISIBLE);
                    del_Btn.setVisibility(View.VISIBLE);
                    contextEditText.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);

                }
            });
        }

        public void  checkDay(int cYear,int cMonth,int cDay,String userID){
            fname=""+userID+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//저장할 파일 이름설정
            FileInputStream fis=null;//FileStream fis 변수
            try{
                fis=openFileInput(fname);

                byte[] fileData=new byte[fis.available()];
                fis.read(fileData);
                fis.close();

                str=new String(fileData);

                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView2.setText(str);

                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);

                cha_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        contextEditText.setVisibility(View.VISIBLE);
                        textView2.setVisibility(View.INVISIBLE);
                        contextEditText.setText(str);

                        save_Btn.setVisibility(View.VISIBLE);
                        cha_Btn.setVisibility(View.INVISIBLE);
                        del_Btn.setVisibility(View.INVISIBLE);
                        textView2.setText(contextEditText.getText());
                    }

                });
                del_Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textView2.setVisibility(View.INVISIBLE);
                        contextEditText.setText("");
                        contextEditText.setVisibility(View.VISIBLE);
                        save_Btn.setVisibility(View.VISIBLE);
                        cha_Btn.setVisibility(View.INVISIBLE);
                        del_Btn.setVisibility(View.INVISIBLE);
                        removeDiary(fname);
                    }
                });
                if(textView2.getText()==null){
                    textView2.setVisibility(View.INVISIBLE);
                    diaryTextView.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    contextEditText.setVisibility(View.VISIBLE);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @SuppressLint("WrongConstant")
        public void removeDiary(String readDay){
            FileOutputStream fos=null;

            try{
                fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
                String content="";
                fos.write((content).getBytes());
                fos.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @SuppressLint("WrongConstant")
        public void saveDiary(String readDay){
            FileOutputStream fos=null;

            try{
                fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
                String content=contextEditText.getText().toString();
                fos.write((content).getBytes());
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}