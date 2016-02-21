package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import android.support.v7.app.AppCompatActivity;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getI ntent();
        Intent SName = getIntent();

        TextView output = (TextView)findViewById(R.id.textView1);

        String strJson = "";
        String data = "";

        try{
            JSONObject jsonRootObject = new JSONObject(SName);

            JSONArray jsonArray = jsonRootObject.optJSONArray();
            for(int i = 0; i < jsonArray.length(); i++){
                String name = jsonObject.optString("name").toString();
                String open_now = jsonObject.optString("open_now").toString();
                double rating = Double.parseDouble(jsonObject.optString("rating").toString());
                int price_level = Integer.parseInt(jsonObject.optString("price_level").toString());
                String vicinity = jsonObject.optString("vicinity").toString();

                data += "Node" + i + " : \n name = "+name + " : \n open_now = " +open_now+ " : \n rating = "
                        +rating+ " : \n price_level = " +price_level+ " : \n vicinity = " +vicinity+ "\n";
                output.setText(data);
            }

        } catch (JSONException e) {e.printStackTrace();}
    }



}
