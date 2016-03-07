package com.xu.shawn.demoapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{


    RadioButton button[] = new RadioButton[10];
    private View fullView;
    Intent intent;
    private Button btnGoPre;
    String activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGoPre = (Button)findViewById(R.id.logout);
        btnGoPre.setOnClickListener(this);

        button[0] = (RadioButton)findViewById(R.id.radioButton1);
        button[1] = (RadioButton)findViewById(R.id.radioButton2);
        button[2] = (RadioButton)findViewById(R.id.radioButton3);
        button[3] = (RadioButton)findViewById(R.id.radioButton4);
        button[4] = (RadioButton)findViewById(R.id.radioButton5);
        button[5] = (RadioButton)findViewById(R.id.radioButton6);
        button[6] = (RadioButton)findViewById(R.id.radioButton7);
        button[7] = (RadioButton)findViewById(R.id.radioButton8);
        button[8] = (RadioButton)findViewById(R.id.radioButton9);
        button[9] =(RadioButton)findViewById(R.id.radioButton10);

        fullView = (View)findViewById(R.id.profile);
        fullView.setOnTouchListener(imageViewSwiped);

        intent = getIntent();
        activity = intent.getStringExtra("activity");


        //get information from server
        Firebase ref1 = new Firebase("https://luminous-inferno-8213.firebaseio.com/user");
        final AuthData authData1 = ref1.getAuth();
        Query queryRef1 = ref1.orderByChild("uid").equalTo(authData1.getUid());

        queryRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot != null && snapshot.getValue() != null) {

                    User tmp = snapshot.child(authData1.getUid()).getValue(User.class);

                    ArrayList<Integer> curr = tmp.getPref();

                    for (int j = 2; j < 12; j++) {
                        int i = j - 2;
                        if (curr.get(j) < 25) {
                            button[i].setText("I HATE IT!");
                            button[i].refreshDrawableState();
                        } else if (curr.get(j) < 50) {
                            button[i].setText("I DON'T LIKE IT!");
                            button[i].refreshDrawableState();
                        } else if (curr.get(j) == 50) {
                            button[i].setText("WHATEVER!");
                            button[i].refreshDrawableState();
                        } else if (curr.get(j) < 75) {
                            button[i].setText("I LIKE IT!");
                            button[i].refreshDrawableState();
                        } else {
                            button[i].setText("MY FAVORITE!");
                            button[i].refreshDrawableState();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        Drawable d = btnGoPre.getBackground();

    }

    View.OnTouchListener imageViewSwiped = new OnSwipeTouchListener() {
        @Override
        public void onSwipeLeft() {
            if(activity == "PreferenceActivity")
            {
                Intent intent = new Intent(ProfileActivity.this, PreferenceActivity.class);
                startActivity(intent);
            }
            else if(activity == "ChooseOneActivity")
            {
                Intent intent = new Intent(ProfileActivity.this, ChooseOneActivity.class);
                startActivity(intent);
            }
            else
            {
                Intent intent = new Intent(ProfileActivity.this, RestInfoActivity.class);
                startActivity(intent);
            }
        }

    };

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
