package com.example.camp_proj1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


public class Fragment2 extends Fragment {

    public ArrayList<Integer> imageIDs = new ArrayList<Integer>();
    private GridViewAdapter adapter;

    public Fragment2() {
        imageIDs.clear();
        imageIDs.addAll(Image.imageIDS);
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
        adapter = new GridViewAdapter(context,imageIDs);
        gridViewImages.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        imageIDs.clear();
        imageIDs.addAll(Image.imageIDS);
        adapter.refresh(imageIDs);
        GridView gridViewImages = (GridView) getView().findViewById(R.id.gridViewImages);
        adapter = new GridViewAdapter(getContext(),imageIDs);
        gridViewImages.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

    }

}

class Image {
    public  static ArrayList<Integer> imageIDS = new ArrayList<Integer>();

   public static void setData(){
        imageIDS.clear();
        imageIDS.add(R.drawable.image1);
        imageIDS.add(R.drawable.image2);
        imageIDS.add(R.drawable.image3);
        imageIDS.add(R.drawable.image4);
        imageIDS.add(R.drawable.image5);
        imageIDS.add(R.drawable.image6);
        imageIDS.add(R.drawable.image7);
        imageIDS.add(R.drawable.image8);
        imageIDS.add(R.drawable.image9);
        imageIDS.add(R.drawable.image10);
        imageIDS.add(R.drawable.basic);
        imageIDS.add(R.drawable.basic2);
        imageIDS.add(R.drawable.basic3);
    }
    public static void deleteImage(int position){
       for(int i = position; i<imageIDS.size()-1; i++){
           imageIDS.set(i, imageIDS.get(i + 1));
       }
    }


}
