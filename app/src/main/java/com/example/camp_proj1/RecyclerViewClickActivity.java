package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Button callButton = findViewById(R.id.callbutton);
        callButton.setOnClickListener(new cbClickListener());

        imageView.setImageResource(intent.getIntExtra("image",0));
        textView.setText(intent.getStringExtra("name"));
        textView2.setText(intent.getStringExtra("number"));
        textView3.setText(intent.getStringExtra("email"));


    }
}
class cbClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {

        String phonecall = "tel:01012344321";
        Context context = v.getContext();
        context.startActivity(new Intent("android.intent.action.CALL", Uri.parse(phonecall)));

    }
}

