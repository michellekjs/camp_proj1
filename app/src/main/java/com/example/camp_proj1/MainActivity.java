package com.example.camp_proj1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<UserInfo> information = new ArrayList<>();
    DBHelper userDBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDBhelper = new DBHelper(this, "user.db", null, 1);
        //add data (if first time)
        jsonParsing();
        //TabLayout
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        //어답터설정
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(myPagerAdapter);

        //탭메뉴를 클릭하면 해당 프래그먼트로 변경-싱크화
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }

    public ArrayList<UserInfo> jsonParsing()
    {
        String json="";
        try {
            InputStream is = getAssets().open("user.json");
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
                // sqlInsert = "INSERT OR REPLACE INTO USERDATA"+ " (NAME, NUMBER, EMAIL, IMAGES) VALUES (\'" + pObject.getString("name") +"\', " + pObject.getString("pn") +", \'" + pObject.getString("email") +"\', " + images[rand] +")";

                //userDB.execSQL(sqlInsert);
                UserInfo user = new UserInfo(pObject.getString("name"),pObject.getString("pn"), pObject.getString("email"), images[rand]);
                information.add(user);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return information;
    }




}