package com.example.camp_proj1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


public class Fragment2 extends Fragment {


    public int[] imageIDs = new int[]{
            R.drawable.image1,

            R.drawable.image2,
            R.drawable.image3,
            R.drawable.basic,
            R.drawable.basic2,
            R.drawable.basic3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10,
            R.drawable.image4,
            R.drawable.image4,
            R.drawable.image4,
            R.drawable.image4,

    };


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        Context context = view.getContext();
        GridView gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(context,imageIDs);
        gridViewImages.setAdapter(gridViewAdapter);
        return view;
    }
}