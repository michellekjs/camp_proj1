package com.example.camp_proj1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theme, menu);
        menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.palette));
        return true;
    }

    public String theme;


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        Intent intent = new Intent(getApplicationContext(),TextReviseActivity.class);

        if (id == R.id.theme_1) {
            Toast.makeText(this, "mint", Toast.LENGTH_SHORT).show();
            Utils.changeToTheme(this,Utils.THEME_DEFAULT);

            TextSetting.theme = 1;
            return true;
        }
        if (id ==R.id.theme_2) {
            Toast.makeText(this, "orange", Toast.LENGTH_SHORT).show();
            Utils.changeToTheme(this,Utils.THEME_ORANGE);

            TextSetting.theme = 2;
            return true;
        }
        if (id == R.id.theme_3) {
            Toast.makeText(this, "pink", Toast.LENGTH_SHORT).show();
            Utils.changeToTheme(this,Utils.THEME_PINK);

            TextSetting.theme = 3;
            return true;
        }
        if (id == R.id.theme_4) {
            Toast.makeText(this, "black", Toast.LENGTH_SHORT).show();
            Utils.changeToTheme(this,Utils.THEME_BLACK);

            TextSetting.theme = 4;
            return true;
        }

        if (id == R.id.theme_5) {
            Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();
            Utils.changeToTheme(this,Utils.THEME_RED);

            TextSetting.theme = 5;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);


        if(CheckAppFirstExecute()) jsonParsing();

        //TabLayout
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Tab 1"));
        tabs.addTab(tabs.newTab().setText("Tab 2"));
        tabs.addTab(tabs.newTab().setText("Tab 3"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);
        Image.setData();

        //어답터설정
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(myPagerAdapter);

        //탭메뉴를 클릭하면 해당 프래그먼트로 변경-싱크화
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        //viewPager.setCurrentItem(1);
    }




    //액션바 숨기기
    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }


    public void jsonParsing()
    {
        String json="";
        DBHelper userDBhelper = new DBHelper(this, "information.db", null, 1);
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
            String sql = "SELECT * FROM USERDATA";
            Cursor cursor =  userDBhelper.getReadableDatabase().rawQuery(sql, null);
            if(cursor.getCount()==0){
                for(int i=0; i<pArray.length(); i++)
                {
                    int rand = new Random().nextInt(images.length);
                    JSONObject pObject = pArray.getJSONObject(i);
                    String sqlInsert = "INSERT INTO USERDATA"+ " (NAME, NUMBER, EMAIL, IMAGES) VALUES (\'" + pObject.getString("name") +"\', " + pObject.getString("pn") +", \'" + pObject.getString("email") +"\', " + images[rand] +")";
                    userDBhelper.getWritableDatabase().execSQL(sqlInsert);
                    //userDBhelper.close();
                }
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public boolean CheckAppFirstExecute(){
        SharedPreferences pref = getSharedPreferences("IsFirst" , Activity.MODE_PRIVATE);
        boolean isFirst = pref.getBoolean("isFirst", false);
        if(!isFirst){ //최초 실행시 true 저장
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("isFirst", true);
            editor.commit();
        }

        return !isFirst;
    }

}

class TextSetting{

    public static int theme=0;

}