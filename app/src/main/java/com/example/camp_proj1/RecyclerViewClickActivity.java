package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class RecyclerViewClickActivity extends AppCompatActivity {
    public ImageButton back_btn;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewclickevent);
        Intent intent = getIntent();

        TextView textView = findViewById(R.id.name);
        TextView textView2 = findViewById(R.id.number);
        TextView textView3 = findViewById(R.id.email);
        ImageView imageView = findViewById(R.id.image);
        imageView.setBackground(new ShapeDrawable(new OvalShape()));
        imageView.setClipToOutline(true);


        Button callButton = findViewById(R.id.callbutton);
        callButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                String phonecall = "tel:01012344321";
                Context context = v.getContext();
                context.startActivity(new Intent("android.intent.action.CALL", Uri.parse(phonecall)));

            }
        });

        Button deleteButton = findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                DBHelper userDBhelper = new DBHelper(v.getContext(), "information.db", null, 1);
                String sqlInsert = String.format("DELETE FROM USERDATA WHERE NAME = ('%s')",textView.getText());
                userDBhelper.getWritableDatabase().execSQL(sqlInsert);
                userDBhelper.close();
                finish();

            }
        });



        //imageView.setImageResource(intent.getIntExtra("image",0));
        imageView.setImageResource(R.drawable.basic);
        textView.setText(intent.getStringExtra("name"));
        textView2.setText(intent.getStringExtra("number"));
        textView3.setText(intent.getStringExtra("email"));

        back_btn = findViewById(R.id.goback_btn);
        back_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                finish();
            }

        });

    }
}




