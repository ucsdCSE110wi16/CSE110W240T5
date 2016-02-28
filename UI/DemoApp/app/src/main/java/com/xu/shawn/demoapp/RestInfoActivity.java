package com.xu.shawn.demoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestInfoActivity extends AppCompatActivity{

    //private Button btnGoback;
    public Bitmap pic;
    public String json;
    public int index;
    public String name;
    public String rating;
    public int price;
    public String add;
    private View fullView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //btnGoback = (Button)findViewById(R.id.Goback);
        //btnGoback.setOnClickListener(this);

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
        fullView = (View)findViewById(R.id.rest_info);
        fullView.setOnTouchListener(imageViewSwiped);

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

    View.OnTouchListener imageViewSwiped = new OnSwipeTouchListener() {
        @Override
        public void onSwipeLeft() {
            Intent intent = new Intent(RestInfoActivity.this, ChooseOneActivity.class);
            startActivity(intent);
        }
        @Override
        public void onSwipeRight() {

            Intent intent = new Intent(RestInfoActivity.this, ProfileActivity.class);
            intent.putExtra("activity","RestInfoActivity");
            startActivity(intent);
        }
    };

    /*@Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);

    }*/
}
