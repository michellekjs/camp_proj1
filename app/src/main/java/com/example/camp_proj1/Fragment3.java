package com.example.camp_proj1;
//캘린더 메인화면
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Fragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public String fname = null;
    public String memo = null;
    public CalendarView cal;
    public Button cha_Btn, del_Btn, save_Btn;
    public TextView diaryTextView, textView3;
    public EditText contextEditText;
    public String filename = null;
    public Integer n;


    public Fragment3() {
        // Required empty public constructor
        Fragment fragment3 = new Fragment();

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

        View view = inflater.inflate(R.layout.fragment_3, container, false);

        ExtendedFloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new faClickListener());

        del_Btn = view.findViewById(R.id.del_Btn);
        del_Btn.setOnClickListener(new delClickListener());

        cha_Btn = view.findViewById(R.id.cha_Btn);
        cha_Btn.setOnClickListener(new editClickListener());


        cal = (CalendarView) view.findViewById(R.id.calendarView);
        diaryTextView = view.findViewById(R.id.diaryTextView);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                filename = String.format("%d,%d,%d", year, month + 1, dayOfMonth); //파일이름 비교대상
                FileInputStream fis = null;
                StringBuffer filecontent = new StringBuffer("");

                try {
                    fis = getActivity().openFileInput(filename + ".txt");
                    byte[] buffer = new byte[1024];
                    while ((n = fis.read(buffer)) != -1) {
                        filecontent.append(new String(buffer, 0, n));
                    }
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                diaryTextView.setText(filecontent);

            }


        });
        return view;
    }

    @SuppressLint("WrongConstant")
    public void removeDiary() {
        FileOutputStream fos = null;

        try {
            fos = getActivity().openFileOutput(filename + ".txt", Context.MODE_PRIVATE);
            String content = "";
            fos.write((content).getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class delClickListener implements View.OnClickListener {
        @Override

        public void onClick(View v) {

            removeDiary();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(Fragment3.this).attach(Fragment3.this).commit();

            Context context = v.getContext();
            CharSequence text = "Schedule Deleted!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

    }


    class faClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, TextEditActivity.class);
            context.startActivity(intent);

        }
    }


    class editClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            removeDiary();
            Context context = v.getContext();
            Intent intent = new Intent(context, TextEditActivity.class);
            context.startActivity(intent);
        }


    }
}





