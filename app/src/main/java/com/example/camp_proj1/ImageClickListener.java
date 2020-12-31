package com.example.camp_proj1;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class ImageClickListener implements View.OnClickListener{
    Context context;
    int imageID;
    public ImageClickListener(Context context, int ImageID){
        this.context = context;
        this.imageID = ImageID;
    }

    public void onClick(View v){
        Intent intent = new Intent(context, GridViewClickActivity.class);
        intent.putExtra("image ID",imageID);
        context.startActivity(intent);
    }
}
