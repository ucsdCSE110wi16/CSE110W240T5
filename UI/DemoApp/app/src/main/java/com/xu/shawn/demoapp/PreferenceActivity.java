package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class PreferenceActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<SeekBar> bars = new ArrayList<>();
    private ArrayList<Integer> progresses = new ArrayList<>();

    private Button btnGoChooseOne;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bars.add((SeekBar)findViewById(R.id.sB1));
        bars.add((SeekBar)findViewById(R.id.sB2));
        bars.add((SeekBar)findViewById(R.id.sB3));
        bars.add((SeekBar)findViewById(R.id.sB4));
        bars.add((SeekBar)findViewById(R.id.sB5));
        bars.add((SeekBar)findViewById(R.id.sB6));
        bars.add((SeekBar)findViewById(R.id.sB7));
        bars.add((SeekBar)findViewById(R.id.sB8));
        bars.add((SeekBar)findViewById(R.id.sB9));
        bars.add((SeekBar)findViewById(R.id.sB10));
        bars.add((SeekBar)findViewById(R.id.sB11));
        bars.add((SeekBar)findViewById(R.id.sB12));

        for(int i=0; i<2; i++){
            bars.get(i).setProgress(0);
        }
        for(int i=2; i<12; i++){
            bars.get(i).setProgress(50);
        }

        for(int i=0; i<12; i++){
            progresses.add(bars.get(i).getProgress());
        }

        btnGoChooseOne = (Button)findViewById(R.id.btnSubmit);
        btnGoChooseOne.setOnClickListener(this);

        btnLoad = (Button) findViewById(R.id.Load);
        btnLoad.setOnClickListener(this);

        for(int i=0; i<12;i++) {

            final int j = i;
            final SeekBar tmpBar = bars.get(i);

            tmpBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    int tmp = seekBar.getProgress();

                    if (tmp < 13) {
                        seekBar.setProgress(0);
                        progresses.set(j, seekBar.getProgress());
                    } else if (tmp >= 13 && tmp < 38) {
                        seekBar.setProgress(25);
                        progresses.set(j, seekBar.getProgress());
                    } else if (tmp >= 38 && tmp < 63) {
                        seekBar.setProgress(50);
                        progresses.set(j, seekBar.getProgress());
                    } else if (tmp >= 63 && tmp < 88) {
                        seekBar.setProgress(75);
                        progresses.set(j, seekBar.getProgress());
                    } else if (tmp >= 88) {
                        seekBar.setProgress(100);
                        progresses.set(j, seekBar.getProgress());
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:

                //save preferences to Firebase

                Firebase ref = new Firebase("https://luminous-inferno-8213.firebaseio.com/");
                AuthData authData = ref.getAuth();


                User update = new User();
                update.setUid(authData.getUid());
                ArrayList<Integer> toUpdate = new ArrayList<>();
                for(int i=0; i<12; i++){
                    toUpdate.add(progresses.get(i));
                }
                update.setPref(toUpdate);


                ref.child("user").child(authData.getUid()).setValue(update);


                Toast.makeText(PreferenceActivity.this, "Preferences saved! ^o^",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(PreferenceActivity.this, ChooseOneActivity.class);

                int[] values = new int[12];
                for(int i=0; i<12; i++){
                    values[i] = progresses.get(i);
                }

                intent.putExtra("values", values);
                startActivity(intent);

                break;
            case R.id.Load:
                //load from Firebase
                Firebase ref1 = new Firebase("https://luminous-inferno-8213.firebaseio.com/user");
                final AuthData authData1 = ref1.getAuth();
                Query queryRef1 = ref1.orderByChild("uid").equalTo(authData1.getUid());

                Toast.makeText(PreferenceActivity.this, "Preferences Loaded! ^o^",
                        Toast.LENGTH_SHORT).show();


                queryRef1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot != null && snapshot.getValue() != null) {

                            User tmp = snapshot.child(authData1.getUid()).getValue(User.class);

                            ArrayList<Integer> curr = tmp.getPref();
                            System.out.println(curr);
                            
                            for(int i=0; i<12;i++){
                                progresses.set(i,curr.get(i));
                                bars.get(i).setProgress(progresses.get(i));
                                bars.get(i).refreshDrawableState();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError error) {
                    }
                });

                break;
            default:
                break;

        }
    }
}
