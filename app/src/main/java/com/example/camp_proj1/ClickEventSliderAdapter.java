package com.example.camp_proj1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

public class ClickEventSliderAdapter extends PagerAdapter {


    private LayoutInflater inflater;
    private Context context;
    private int[] images;

    public ClickEventSliderAdapter(Context context){
        this.context = context;
        Fragment2 fragment2 = new Fragment2();
        images = fragment2.imageIDs;

    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.clickeventslider, container, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();
    }

}
