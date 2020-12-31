package com.example.camp_proj1;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ImageClickListener implements View.OnClickListener{
    Context context;
    int imageID;
    int[] imageIDlist;
    public ImageClickListener(Context context, int ImageID, int[] list){
        this.context = context;
        this.imageID = ImageID;
        this.imageIDlist = list;
    }

    public void onClick(View v){
        Intent intent = new Intent(context, GridViewClickActivity.class);
        intent.putExtra("image ID",imageID);
        context.startActivity(intent);
    }
}
