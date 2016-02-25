package com.xu.shawn.demoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class RestInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGoback;
    public Bitmap pic;
    public String json;
    public int index;
    public String name;
    public String rating;
    public int price;
    public String add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGoback = (Button)findViewById(R.id.Goback);
        btnGoback.setOnClickListener(this);

        Intent intent = getIntent();
        json = intent.getStringExtra("json");
        index = intent.getIntExtra("index",0);
        pic=intent.getParcelableExtra("pic");

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("results");

            //Get the name of the restuarants.

            JSONObject temp = jsonArray.getJSONObject(index);
            name = temp.optString("name").toString();
            rating = temp.optString("rating").toString();
            price = temp.optInt("price_level");
            add = temp.optString("vicinity").toString();

            Log.v("names ", name);
            Log.v("rating ", rating);
            Log.v("price", price + "");

        } catch (Throwable t){
            Log.v("Throw", "Can't parse");
        }

        TextView nameView = (TextView) findViewById(R.id.RestName);
        nameView.setText(name);

        TextView RatingView = (TextView) findViewById(R.id.rating);
        RatingView.setText("Rating: "+rating);

        TextView AddressView = (TextView) findViewById(R.id.add);
        AddressView.setText(add);

        if(pic != null) {
            ImageView Pics = (ImageView) findViewById(R.id.Picture);
            Pics.setImageBitmap(pic);
        }

        String priceTag = "$";
        TextView PriceView = (TextView) findViewById(R.id.price);
        for (int i = 0;i<price;i++){
            priceTag+="$";
        }
        PriceView.setText(priceTag);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);

    }
}
