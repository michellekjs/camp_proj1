package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class AddNewUserInfo extends AppCompatActivity {
    int[] images = {R.drawable.basic,R.drawable.basic2,R.drawable.basic3};
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        setContentView(R.layout.addnewuserinfo);
        Intent intent = getIntent();
        DBHelper userDBhelper = new DBHelper(this, "information.db", null, 1);
        Button savebutton = (Button) findViewById(R.id.save);
        Button cancelbutton = (Button) findViewById(R.id.cancel);
        EditText nameText = (EditText) findViewById(R.id.nameText);
        EditText numberText = (EditText) findViewById(R.id.numberText);
        EditText emailText = (EditText) findViewById(R.id.emailText);
        ImageView imageView = (ImageView) findViewById(R.id.sampleimage);
        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        imageView.setClipToOutline(true);

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
