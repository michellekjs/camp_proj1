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

public class GridViewAdapter extends BaseAdapter {
    Context context = null;
    int[] imageIDs = null;

    public GridViewAdapter(Context context, int[] imageIDs){
        this.context = context;
        this.imageIDs = imageIDs;
    }
    @Override
    public int getCount() {
        return (null!=imageIDs)? imageIDs.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return (null!=imageIDs)? imageIDs[position] : 0;
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
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs[position]);
            //gridview 사진크기
            bmp = Bitmap.createScaledBitmap(bmp, 450, 450, false);

            imageView = new ImageView(context);
            imageView.setImageBitmap(bmp);
            ImageClickListener imageViewClickListener = new ImageClickListener(context, position, imageIDs);

            imageView.setOnClickListener(imageViewClickListener);
        }
        return imageView;
    }


}
