package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AddNewUserInfo extends AppCompatActivity {
    int[] images = {R.drawable.basic,R.drawable.basic2,R.drawable.basic3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnewuserinfo);
        Intent intent = getIntent();
        DBHelper userDBhelper = new DBHelper(this, "information.db", null, 1);
        Button savebutton = (Button) findViewById(R.id.save);
        Button cancelbutton = (Button) findViewById(R.id.cancel);
        EditText nameText = (EditText) findViewById(R.id.nameText);
        EditText numberText = (EditText) findViewById(R.id.numberText);
        EditText emailText = (EditText) findViewById(R.id.emailText);

        savebutton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                String name = nameText.getText().toString();
                String number = numberText.getText().toString();
                String email = emailText.getText().toString();
                int rand = new Random().nextInt(images.length);
                String sqlInsert = String.format("INSERT INTO USERDATA VALUES('%s', '%s', '%s', '%d');", name, number, email, images[rand]);
                userDBhelper.getWritableDatabase().execSQL(sqlInsert);
                userDBhelper.close();
                datachecker.isChanged = true;
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
