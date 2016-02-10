package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ChooseOneActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private Button btnGoResInfo;
    public GoogleApiClient mGoogleApiClient;
    public String TAG = "Choose";
    public static final int TYPE_RESTAURANT = 79;
    public HashMap<String, Place> Restaurants = new HashMap<>();
    public String restNames ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        setContentView(R.layout.activity_choose_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String restName1 = "Rest1";
        String restName2 = "Rest2";

        final ArrayList <String> restArray = new ArrayList<>();

        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);

        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                Random rand = new Random();
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    if (placeLikelihood.getPlace().getPlaceTypes().contains(TYPE_RESTAURANT)) {
                        Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                        //Restaurants.put(placeLikelihood.getPlace().getName().toString(), placeLikelihood.getPlace());
                        //restNames += "\n" + placeLikelihood.getPlace().getName().toString();
                        restArray.add(placeLikelihood.getPlace().getName().toString());
                        Log.v("inside ", "" + Restaurants.isEmpty() + " " + Restaurants.size());
                    }
                }
                if ( restArray.size() > 1) {
                    int a = rand.nextInt(restArray.size());
                    int b = rand.nextInt(restArray.size());
                    while (a == b) b = rand.nextInt(restArray.size());
                    TextView rest1 = (TextView) findViewById(R.id.textView2);
                    rest1.setText(restArray.get(a));

                    TextView rest2 = (TextView) findViewById(R.id.textView);
                    rest2.setText(restArray.get(b));}
                else {
                    TextView rest1 = (TextView) findViewById(R.id.textView2);
                    rest1.setText("Subway");

                    TextView rest2 = (TextView) findViewById(R.id.textView);
                    rest2.setText("Tapioca express");

                }
                    likelyPlaces.release();

            }
        });

        btnGoResInfo = (Button)findViewById(R.id.GotoRes);
        btnGoResInfo.setOnClickListener(this);


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
        Intent intent = new Intent(this, RestInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
