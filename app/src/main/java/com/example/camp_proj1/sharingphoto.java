package com.example.camp_proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class sharingphoto extends AppCompatActivity {


    public String url = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //int position =
        int position = getIntent().getIntExtra("showImage",0);
        File file = new File("/data/data/com.example.camp_proj1/files/image.txt");
        StringBuffer filecontent = new StringBuffer("");
        FileInputStream fis = null;
        int n;
        if (file.exists()) {

            try {
                fis = openFileInput("image.txt");
                byte[] buffer = new byte[1024];
                while ((n = fis.read(buffer)) != -1) {
                    filecontent.append(new String(buffer, 0, n));
                }
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            url = filecontent.toString();


        }
        ImageView imageView = findViewById(R.id.sharingphoto);
        Glide.with(this).load(url).into(imageView);

    }


}