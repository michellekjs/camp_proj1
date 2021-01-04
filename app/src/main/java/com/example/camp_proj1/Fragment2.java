package com.example.camp_proj1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class Fragment2 extends Fragment {

    public ArrayList<Integer> imageIDs = new ArrayList<Integer>();
    GridViewAdapter adapter;
    GridView gridViewImages;


    public Fragment2() {
        // Required empty public constructor
        imageIDs.clear();
        imageIDs.addAll(Image.imageIDs);
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
        gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);
        adapter = new GridViewAdapter(context,imageIDs);
        gridViewImages.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        int size = imageIDs.size();
        imageIDs.clear();
        imageIDs.addAll(Image.imageIDs);
        //adapter.refresh();
        adapter = new GridViewAdapter(getContext(),imageIDs);
        gridViewImages.setAdapter(adapter);
        if(size != imageIDs.size()){
            Handler delayHandler = new Handler();
            delayHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast mine = Toast.makeText(getContext(), "삭제되었습니다", Toast.LENGTH_SHORT);
                    mine.show();
                }
            }, 1000);
        }




    }

}

class Image{
    public static ArrayList<Integer> imageIDs = new ArrayList<Integer>();

    public static void setData(){
        imageIDs.clear();
        imageIDs.add( R.drawable.image1);
        imageIDs.add( R.drawable.image2);
        imageIDs.add( R.drawable.image3);
        imageIDs.add( R.drawable.image4);
        imageIDs.add( R.drawable.image5);
        imageIDs.add( R.drawable.image6);
        imageIDs.add( R.drawable.image7);
        imageIDs.add( R.drawable.image8);
        imageIDs.add( R.drawable.image9);
        imageIDs.add( R.drawable.image10);
        imageIDs.add( R.drawable.basic);
        imageIDs.add( R.drawable.basic2);
        imageIDs.add( R.drawable.basic3);

    }


}