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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    public ArrayList<UserInfo> information= new ArrayList<>();
    private RecyclerViewAdapter adapter;
    DBHelper userDBhelper;
    MenuItem mSearch;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_1, container, false);
        Context context = view.getContext();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), 1));
        recyclerView.setHasFixedSize(true);

        setHasOptionsMenu(true);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new FloatingActionButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, AddNewUserInfo.class);
                context.startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(context, information);
        recyclerView.setAdapter(adapter);
        if(datachecker.isChanged){
            setViewWithDB();
            datachecker.isChanged = false;
        }
        else{
            information = datachecker.savedinfo;
        }

        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
        setViewWithDB();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment1_menu, menu);
        mSearch = menu.findItem(R.id.)
    }

    public void setViewWithDB(){
        userDBhelper = new DBHelper(getContext(), "information.db", null, 1);
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
        datachecker.savedinfo = information;
    }

}

class datachecker {
    public static Boolean isChanged = true;
    public static ArrayList<UserInfo> savedinfo = new ArrayList<>();
}

