package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    private RadioButton A_1;
    private RadioButton A_2;
    private RadioButton A_3;
    private RadioButton A_4;
    private RadioButton A_5;
    private RadioButton A_6;
    private RadioButton A_7;
    private RadioButton A_8;
    private RadioButton A_9;
    private RadioButton A_10;


    private Button btnGoPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGoPre = (Button)findViewById(R.id.logout);
        btnGoPre.setOnClickListener(this);

        A_1 = (RadioButton)findViewById(R.id.radioButton1);
        A_2 = (RadioButton)findViewById(R.id.radioButton2);
        A_3 = (RadioButton)findViewById(R.id.radioButton3);
        A_4 = (RadioButton)findViewById(R.id.radioButton4);
        A_5 = (RadioButton)findViewById(R.id.radioButton5);
        A_6 = (RadioButton)findViewById(R.id.radioButton6);
        A_7 = (RadioButton)findViewById(R.id.radioButton7);
        A_8 = (RadioButton)findViewById(R.id.radioButton8);
        A_9 = (RadioButton)findViewById(R.id.radioButton9);
        A_10 =(RadioButton)findViewById(R.id.radioButton10);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
