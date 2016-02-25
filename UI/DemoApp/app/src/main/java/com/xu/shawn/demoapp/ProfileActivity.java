package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    RadioButton button[] = new RadioButton[10];


    private Button btnGoPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGoPre = (Button)findViewById(R.id.logout);
        btnGoPre.setOnClickListener(this);

        button[0] = (RadioButton)findViewById(R.id.radioButton1);

        button[0].setText("Testing");
        button[0].refreshDrawableState();

        button[1] = (RadioButton)findViewById(R.id.radioButton2);
        button[2] = (RadioButton)findViewById(R.id.radioButton3);
        button[3] = (RadioButton)findViewById(R.id.radioButton4);
        button[4] = (RadioButton)findViewById(R.id.radioButton5);
        button[5] = (RadioButton)findViewById(R.id.radioButton6);
        button[6] = (RadioButton)findViewById(R.id.radioButton7);
        button[7] = (RadioButton)findViewById(R.id.radioButton8);
        button[8] = (RadioButton)findViewById(R.id.radioButton9);
        button[9] =(RadioButton)findViewById(R.id.radioButton10);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    public void setText(ArrayList<String> list)
    {
        for(int i =0; i<list.size(); i++)
        {
            button[i].setText(list.get(i));
        }
    }

    public String getButtonText(int i)
    {
        if(0<=i && i<=10)
            return button[i].getText().toString();
        else
            return "Wrong input";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout:

                Firebase ref = new Firebase("https://luminous-inferno-8213.firebaseio.com/");

                //AuthData authData = ref.getAuth();

                ref.unauth();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);

                break;
            case R.id.button11:
                //do nothing, this button is decoration
                break;
            default:
                break;
        }
    }
}
