package com.example.camp_proj1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecyclerViewClickActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewclickevent);
        Intent intent = getIntent();

        TextView textView = findViewById(R.id.name);
        TextView textView2 = findViewById(R.id.number);
        TextView textView3 = findViewById(R.id.email);
        ImageView imageView = findViewById(R.id.image);

        imageView.setImageResource(intent.getIntExtra("image",0));
        textView.setText(intent.getStringExtra("name"));
        textView2.setText(intent.getStringExtra("number"));
        textView3.setText(intent.getStringExtra("email"));

    }
}

