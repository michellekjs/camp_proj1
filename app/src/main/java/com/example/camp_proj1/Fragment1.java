package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment1 extends Fragment {
    public ArrayList<UserInfo> information = new ArrayList<>();
    DBHelper userDBhelper;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);

        userDBhelper = new DBHelper(getContext(), "info.db", null, 1);
        Context context = view.getContext();


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
        recyclerView.setHasFixedSize(true);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new fabClickListener());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, information);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        information.clear();
        String sql = "SELECT * FROM USERDATA";
        Cursor cursor =  userDBhelper.getReadableDatabase().rawQuery(sql, null);
        try{
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    UserInfo infoColum = new UserInfo(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3) );
                    information.add(infoColum);
                }
            }
            else{}

        }
        finally {
            cursor.close();
        }

    }

}

class fabClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, AddNewUserInfo.class);
        context.startActivity(intent);

        //UserInfo addInformation = (UserInfo)intent.getExtras().get("added");
    }
}

