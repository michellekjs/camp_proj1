package com.example.camp_proj1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecyclerViewClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewclickevent);
        TextView textView = findViewById(R.id.clickviewtext);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("clickviewtext"));

    }
}
