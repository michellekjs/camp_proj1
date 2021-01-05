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
    public static ArrayList<String> imageURLs = new ArrayList<>();



    public static void setData() {
        imageIDs.clear();
        imageURLs.clear();
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
        imageIDs.add( R.drawable.image11);
        imageIDs.add( R.drawable.image12);
        imageIDs.add( R.drawable.image13);
        imageIDs.add( R.drawable.image14);
        imageIDs.add( R.drawable.image15);
        imageIDs.add( R.drawable.image16);
        imageIDs.add( R.drawable.image17);
        imageIDs.add( R.drawable.image18);
        imageIDs.add( R.drawable.image19);
        imageIDs.add( R.drawable.image20);
        imageIDs.add( R.drawable.basic);
        imageIDs.add( R.drawable.basic2);
        imageIDs.add( R.drawable.basic3);
        imageURLs.add("https://tamaris-charter.com/wp-content/uploads/2018/05/Crystal-clear-sea-10.jpg");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPryjL181W3WOIz2jWkdTHq2gITFYfe80Ghg&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTOeVaoWqM740P9jD40cthOUBxizES1CP5yQ&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDtayXlm1EIIh4zKXlNa1AIzgrmPBl1YR20A&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtdib1qQ8kLZaA5KAi5t6dAiCJGa9p5oGJQg&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLhPlUIcCKYcmXAnKBW0R10Jx3M5hQfHB5bg&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfIaDqCihBeIWr2kCy0oM7vcZ2v2nmDZUc9g&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBfsumJqWUUXM5kOQPUfq06AXDi8JFMLJEMQ&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqk_HX_BBQA5O8DRg-dKdBWtNOxHUcfWVIWw&usqp=CAU");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD4jqXgYIWvD_QO9-XKl5nmoroovaASo2kjA&usqp=CAU");
        imageURLs.add("https://post-phinf.pstatic.net/MjAyMDA0MjRfMjgw/MDAxNTg3NjU3ODYxMTM2.5N8knsVqPXC_srBrLUJK6twnNRVV-WdhFuhMteixn2Ug.3wekrt8dKaETLpcA4A8WWCUsmvdvGgVrbkquhZxKpS8g.JPEG/Erin-Outdoors00.jpg?type=w1200");
        imageURLs.add("https://lh3.googleusercontent.com/proxy/mrPvdJIQKs37DeSIgyH155SxSUq6C7GJIOozTiNkW1WPPf4u3X46M4fhF9auueapvmpDNXm1uWXpD8apk7aECvE6rWLcrwGsNFC5Ln9lNXDa");
        imageURLs.add("https://www.madtimes.org/news/photo/201909/3015_5298_2630.jpg");
        imageURLs.add("https://t3.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3rWZ/image/fiYqC-m8arDti2dUPSrfAZ8wBSw.jpg");
        imageURLs.add("https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile27.uf.tistory.com%2Fimage%2F99B192495BA481842DC4E6");
        imageURLs.add("https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile1.uf.tistory.com%2Fimage%2F990B7A3E5A4788982C1CFE");
        imageURLs.add("https://images.chosun.com/resizer/MOq91mTGsNEp3AjZpKPFqU_VKHM=/616x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/AZIBJV4CXNDHPN5M6E23UQYNGE.jpg");
        imageURLs.add("https://www.adobe.com/content/dam/cc/us/en/creative-cloud/photography/discover/landscape-photography/CODERED_B1_landscape_P2b_690x455.jpg.img.jpg");
        imageURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPffncUA65GhOdw-qxQpPFzw_xVpkW8rJUpg&usqp=CAU");
        imageURLs.add("https://lh3.googleusercontent.com/proxy/dScR3Q2QpSWxv9sAGQS3z3vEs7ob2n8Z3rgY6K4wLc4dUS2dIZ3YSKS8AaQqU7_C1LncpF0k4VKP2X5I0AZlOV1Ote7E1kCKmX2nCzRqqfZR6GQ");


    }
}


