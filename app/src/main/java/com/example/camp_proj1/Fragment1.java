package com.example.camp_proj1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Fragment1 extends Fragment {
    public ArrayList<UserInfo> information=  new ArrayList<>();
    private RecyclerViewAdapter adapter;
    ArrayList<UserInfo> tmpdata = new ArrayList<>();
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

        tmpdata.addAll(information);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setViewWithDB();
        datachecker.savedinfo = information;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment1_menu, menu);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                information.clear();

               for (int i = 0; i<tmpdata.size();i++){
                   if(tmpdata.get(i).getName().toLowerCase().contains(newText)){
                        information.add(tmpdata.get(i));
                   }

               }
                adapter.notifyDataSetChanged();
                return false;
            }


        });


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
            else{

            }
        }
        finally {
            cursor.close();
        }
        datachecker.savedinfo = information;
        tmpdata.clear();
        tmpdata.addAll(information);
    }

}

class datachecker {
    public static Boolean isChanged = true;
    public static ArrayList<UserInfo> savedinfo = new ArrayList<>();
}

