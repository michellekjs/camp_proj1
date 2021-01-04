package com.example.camp_proj1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import static androidx.viewpager.widget.PagerAdapter.POSITION_NONE;

public class GridViewAdapter extends BaseAdapter {
    Context context = null;
    ArrayList<Integer> imageIDs = new ArrayList<Integer>();

    public GridViewAdapter(Context context, ArrayList<Integer> imageIDs){
        this.context = context;
        this.imageIDs.addAll(imageIDs);
    }
    @Override
    public int getCount() {
        return (null!=imageIDs)? imageIDs.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (null!=imageIDs)? imageIDs.get(position) : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;

        if(null != convertView){
            imageView = (ImageView) convertView;
        }
        else{
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs.get(position));
            //gridview 사진크기
            bmp = Bitmap.createScaledBitmap(bmp, 450, 450, false);

            imageView = new ImageView(context);
            imageView.setImageBitmap(bmp);
            ImageClickListener imageViewClickListener = new ImageClickListener(context, position, imageIDs);

            imageView.setOnClickListener(imageViewClickListener);
        }
        return imageView;
    }

    public void refresh(ArrayList<Integer> imageIDs){
        this.imageIDs.clear();
        this.imageIDs.addAll(imageIDs);
        notifyDataSetChanged();
    }

}
