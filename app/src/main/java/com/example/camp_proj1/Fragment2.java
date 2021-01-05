package com.example.camp_proj1;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        Context context = view.getContext();
        gridViewImages = (GridView) view.findViewById(R.id.gridViewImages);
        adapter = new GridViewAdapter(context, imageIDs);
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
        adapter = new GridViewAdapter(getContext(), imageIDs);
        gridViewImages.setAdapter(adapter);
        if (size != imageIDs.size()) {
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


class Image {
    public static ArrayList<Integer> imageIDs = new ArrayList<Integer>();

    public static void setData() {
        imageIDs.clear();
        imageIDs.add(R.drawable.image1);
        https://tamaris-charter.com/wp-content/uploads/2018/05/Crystal-clear-sea-10.jpg
        imageIDs.add(R.drawable.image2);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPryjL181W3WOIz2jWkdTHq2gITFYfe80Ghg&usqp=CAU
        imageIDs.add(R.drawable.image3);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTOeVaoWqM740P9jD40cthOUBxizES1CP5yQ&usqp=CAU
        imageIDs.add(R.drawable.image4);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDtayXlm1EIIh4zKXlNa1AIzgrmPBl1YR20A&usqp=CAU
        imageIDs.add(R.drawable.image5);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtdib1qQ8kLZaA5KAi5t6dAiCJGa9p5oGJQg&usqp=CAU
        imageIDs.add(R.drawable.image6);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLhPlUIcCKYcmXAnKBW0R10Jx3M5hQfHB5bg&usqp=CAU
        imageIDs.add(R.drawable.image7);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfIaDqCihBeIWr2kCy0oM7vcZ2v2nmDZUc9g&usqp=CAU
        imageIDs.add(R.drawable.image8);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBfsumJqWUUXM5kOQPUfq06AXDi8JFMLJEMQ&usqp=CAU
        imageIDs.add(R.drawable.image9);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqk_HX_BBQA5O8DRg-dKdBWtNOxHUcfWVIWw&usqp=CAU
        imageIDs.add(R.drawable.image10);
        https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD4jqXgYIWvD_QO9-XKl5nmoroovaASo2kjA&usqp=CAU
        imageIDs.add(R.drawable.basic);
        imageIDs.add(R.drawable.basic2);
        imageIDs.add(R.drawable.basic3);

    }
}


