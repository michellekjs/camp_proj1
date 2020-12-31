package com.example.camp_proj1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.InputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Fragment1 extends Fragment {
    public ArrayList<UserInfo> information = new ArrayList<>();

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
        information.clear();
        jsonParsing();

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, information);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment

        return view;
    }


    public ArrayList<UserInfo> jsonParsing()
    {
        String json="";
        try {
            InputStream is = getActivity().getAssets().open("user.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray pArray = jsonObject.getJSONArray("info");
            int[] images = {R.drawable.basic,R.drawable.basic2,R.drawable.basic3};

            for(int i=0; i<pArray.length(); i++)
            {
                int rand = new Random().nextInt(images.length);
                JSONObject pObject = pArray.getJSONObject(i);
                UserInfo user = new UserInfo(pObject.getString("name"),pObject.getString("pn"), pObject.getString("email"), images[rand]);
                information.add(user);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return information;
    }



}