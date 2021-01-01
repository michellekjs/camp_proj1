package com.example.camp_proj1;

import android.app.Activity;
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
                UserInfo newUser = new UserInfo(name, number, email, 1);

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
