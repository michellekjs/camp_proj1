package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Adapter;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

public class GridViewClickActivity extends AppCompatActivity {

    //private static int pagesNum = 3;
    private ViewPager mPager;
    ClickEventSliderAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridviewclickevent);
        mPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ClickEventSliderAdapter(this);
        Intent receivedIntent = getIntent();
        int imageID = (Integer)receivedIntent.getExtras().get("image ID");
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(imageID);
        mPager.setPageTransformer(true, new ZoomOutPageSlide());

    }

    private void setImage(ImageView imageView) {

        Intent receivedIntent = getIntent();
        int imageID = (Integer)receivedIntent.getExtras().get("image ID");
        imageView.setImageResource(imageID);
    }




}
