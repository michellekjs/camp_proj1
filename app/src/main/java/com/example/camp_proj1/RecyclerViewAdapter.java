package com.example.camp_proj1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.lang.Object;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private ArrayList<UserInfo> mPersons;
    private LayoutInflater mInflate;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<UserInfo> persons) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mPersons = persons;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView imageView;
        public TextView number;
        public TextView email;
        ImageView search;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.user_name);
            //email = (TextView) itemView.findViewById(R.id.user_email);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            number = (TextView) itemView.findViewById(R.id.user_number);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.recyclerview, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.name.setText(mPersons.get(position).name);
        holder.number.setText(mPersons.get(position).phoneNumber);
        holder.imageView.setImageResource(mPersons.get(position).getPhoto());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            //ArrayList<UserInfo> list = new ArrayList<UserInfo>();
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecyclerViewClickActivity.class);

                intent.putExtra("image",mPersons.get(position).getPhoto());
                intent.putExtra("name", mPersons.get(position).name);
                intent.putExtra("number", mPersons.get(position).phoneNumber);
                intent.putExtra("email", mPersons.get(position).email);

                v.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mPersons.size();
    }


    //ViewHolder

}
