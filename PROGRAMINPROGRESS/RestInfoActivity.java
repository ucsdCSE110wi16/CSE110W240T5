package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGoback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        btnGoback = (Button)findViewById(R.id.Goback);
        btnGoback.setOnClickListener(this);

        TextView resName = (TextView) findViewById(R.id.resName);
        resName.setText();

        TextView starRate = (TextView) findViewById(R.id.starRate);
        starRate.setText();

        TextView typFood = (TextView) findViewById(R.id.typFood);
        typFood.setText();

        TextView price = (TextView) findViewById(R.id.price);
        price.setText();

        TextView description = (TextView) findViewById(R.id.description);
        description.setText();

        TextView phoneNum = (TextView) findViewById(R.id.phoneNum);
        phoneNum.setText();

        TextView address = (TextView) findViewById(R.id.address);
        address.setText();

        TextView map = (TextView) findViewById(R.id.map);
        map.setText();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });-[
    }





    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);
    }


}
