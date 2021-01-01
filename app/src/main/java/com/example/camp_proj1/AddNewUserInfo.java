package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AddNewUserInfo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewuserinfo);
        Intent intent = getIntent();

        Button savebutton = (Button) findViewById(R.id.save);
        Button cancelbutton = (Button) findViewById(R.id.cancel);


        savebutton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText nameText = (EditText) findViewById(R.id.nameText);
                String name = nameText.getText().toString();
                EditText numberText = (EditText) findViewById(R.id.numberText);
                String number = nameText.getText().toString();
                EditText emailText = (EditText) findViewById(R.id.emailText);
                String email = nameText.getText().toString();
                DBHelper userDBhelper = new DBHelper(v.getContext(), "info.db", null, 1);
                int[] images = {R.drawable.basic,R.drawable.basic2,R.drawable.basic3};
                int rand = new Random().nextInt(images.length);
                String sqlInsert = String.format("INSERT INTO USERDATA VALUES('%s', '%s', '%s', '%d');", name, number, email, images[rand]);
                userDBhelper.getWritableDatabase().execSQL(sqlInsert);
                finish();
            }
        });
        cancelbutton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
