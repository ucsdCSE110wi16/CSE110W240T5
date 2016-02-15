package com.xu.shawn.demoapp;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ChooseOneActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private Button btnGoResInfo;
    public GoogleApiClient mGoogleApiClient;
    public LocationListener locationListener;
    public LocationManager locationManager;
    public String searchMap = "";

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

        //Name of restaurants
        String restName1 = "Rest1";
        String restName2 = "Rest2";

        final ArrayList <String> restArray = new ArrayList<>();

        //LocationManager and listener
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new android.location.LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider
                // Use the new location to update the URL and call API
                // Stop listens when done.
                Log.v("location lat", ""+location.getLatitude());
                Log.v("location long", ""+location.getLongitude());
                searchMap= "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
                searchMap+=location.getLatitude();
                searchMap+=",";
                searchMap+=location.getLongitude();
                searchMap+="&radius=1000&types=food&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM";
                Log.v("link", searchMap);
                new GetJson().execute(searchMap);
                //new GetJson().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM");
                stoplisten();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, locationListener);


        TextView rest1 = (TextView) findViewById(R.id.textView2);
        rest1.setText("Subway");

        TextView rest2 = (TextView) findViewById(R.id.textView);
        rest2.setText("Tapioca express");


        btnGoResInfo = (Button)findViewById(R.id.GotoRes);
        btnGoResInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RestInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void stoplisten(){
        locationManager.removeUpdates(locationListener);
    }

}

class GetJson extends AsyncTask<String, Void, ArrayList<String>> {

    private String json = null;
    private ArrayList<String> restNameList = new ArrayList<>();

    protected ArrayList<String> doInBackground(String... params) {

        try {
            URL urls= new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) urls.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);

            BufferedReader reader = new BufferedReader(isr);

            StringBuilder builder = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line+"\n");
            }
            reader.close();
            json = builder.toString();
            Log.v("json ", json);

        } catch (IllegalStateException e1) {
            Log.e("IllegalStateException", e1.toString());
            e1.printStackTrace();
        } catch (IOException e2) {
            Log.e("IOException", e2.toString());
            e2.printStackTrace();
        } catch(Exception e3) {
            Log.e("Exception", e3.toString());
            e3.printStackTrace();
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("results");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject temp = jsonArray.getJSONObject(i);
                String name = temp.optString("name").toString();
                restNameList.add(name);
            }

        } catch (Throwable t){
            Log.v("Throw","Can't parse");
        }
        return restNameList;
    }

    protected void onPostExecute(ArrayList<String> list) {
        //Can get intent of something.
    }
}
