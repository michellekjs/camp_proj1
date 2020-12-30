package com.example.proj_1;

import android.os.Bundle;
import android.widget.TabHost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.widget.TabHost;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost) findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab1, null));
        spec.setContent(R.id.book);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(),R.drawable.tab2, null));
        spec.setContent(R.id.gallery);
        host.addTab(spec);

        spec = host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.tab3, null));
        spec.setContent(R.id.final_tab);
        host.addTab(spec);
    }


    public String getJsonString() {
        String json = null;
        try {
            InputStream is = getAssets().open("user_info.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public class Number{
        public String name;
        public String number;

        public String getName(){
            return name;
        }
        public String getNumber(){
            return number;
        }
        public void setName(String name){
            this.name = name;
        }
        public void setNumber(String number){
            this.number= number;
        }
    }

    private void jsonParsing(String json){
        ArrayList<Number> numberList = new ArrayList<Number>();

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray numberArray = jsonObject.getJSONArray("user_info");
            for (int i=0;i<numberArray.length();i++){
                JSONObject numberObject = numberArray.getJSONObject(i);
                Number number = new Number();
                number.setName(numberObject.getString("name"));
                number.setNumber(numberObject.getString("number"));
                numberList.add(number);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}







