package com.example.camp_proj1;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

public class ImageClickListener implements View.OnClickListener{
    Context context;
    int imageID;
    ArrayList<Integer> imageIDs = new ArrayList<Integer>();

    public ImageClickListener(Context context, int ImageID, ArrayList<Integer> list){
        this.context = context;
        this.imageID = ImageID;
        imageIDs.addAll(list);
    }

    public void onClick(View v){
        Intent intent = new Intent(context, GridViewClickActivity.class);
        intent.putExtra("image ID",imageID);
        intent.putIntegerArrayListExtra("ImageList",imageIDs);
        context.startActivity(intent);
    }
}
