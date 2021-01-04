package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;

import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GridViewClickActivity extends AppCompatActivity {

    //private static int pagesNum = 3;
    private ViewPager mPager;
    public ImageButton back_btn;
    ArrayList<Integer> imageIDs = new ArrayList<Integer>();
    ClickEventSliderAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewclickevent);
        mPager = (ViewPager) findViewById(R.id.pager);

        Intent receivedIntent = getIntent();
        int imageID = (Integer)receivedIntent.getExtras().get("image ID");

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/drawable";


        imageIDs = (ArrayList<Integer>) receivedIntent.getIntegerArrayListExtra("ImageList");
        adapter = new ClickEventSliderAdapter(this, imageIDs);
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(imageID);
        mPager.setPageTransformer(true, new ZoomOutPageSlide());
        BottomNavigationView buttomNavigationView = findViewById(R.id.navigationView);
        back_btn = findViewById(R.id.goback_btn);
        back_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                finish();
            }

        });
        buttomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.shareItem:{
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        String path = "/drawable/image1.jpg";
                        Uri screenshotUri = Uri.parse(path);	// android image path
                        sharingIntent.setType("image/png");
                        sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        startActivity(Intent.createChooser(sharingIntent, "Share image using"));
                        return true;
                    }
                    case R.id.delteItem : {

                        Image.imageIDs.remove(imageID);
                        finish();
                        return true;
                    }
                    default: return false;
                }
            }
        });


    }

    private void setImage(ImageView imageView) {

        Intent receivedIntent = getIntent();
        int imageID = (Integer)receivedIntent.getExtras().get("image ID");
        imageView.setImageResource(imageID);
    }

}
