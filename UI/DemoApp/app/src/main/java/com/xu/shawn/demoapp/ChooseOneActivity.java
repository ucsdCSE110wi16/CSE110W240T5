package com.xu.shawn.demoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;

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
    public String PassJson = "";
    public ArrayList<String> namelist = new ArrayList<>();
    public ArrayList<String> photolist = new ArrayList<>();
    public boolean setup = false;
    public String restName1 = "Loading..";
    public String restName2 = "Loading..";
    public int[] PreValue;

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
        Intent intent = getIntent();
        PreValue = intent.getIntArrayExtra("values");

        final ArrayList <String> restArray = new ArrayList<>();

        AlertDialog alertDialog = new AlertDialog.Builder(ChooseOneActivity.this).create();
        alertDialog.setTitle("Welcome!");
        alertDialog.setMessage("Thank you for using our app!\n " +
                "We are searching for the best restaurants around you now!\n" +
                "Bon Appétit！");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "I am ready!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        //LocationManager and listener
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
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
                searchMap+="&radius=1200&types=restaurant&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM";
                //Key word &keyword=japanese
                Log.v("link", searchMap);
                new GetJson().execute(searchMap);
                //new GetJson().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM");
                stoplisten();
            }



            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) { Log.v(" what ", "what");}
        };

        TextView rest1 = (TextView) findViewById(R.id.textView2);
        rest1.setText(restName1);

        TextView rest2 = (TextView) findViewById(R.id.textView);
        rest2.setText(restName2);

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, locationListener);

        btnGoResInfo = (Button)findViewById(R.id.GotoRes);
        btnGoResInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(namelist.size() > 0)
        {Log.v("before ", setup+" "+namelist.get(0));
        Intent intent = new Intent(this, RestInfoActivity.class);
        intent.putExtra("json", PassJson);
        startActivity(intent);}
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(ChooseOneActivity.this).create();
            alertDialog.setTitle("Oops..");
            alertDialog.setMessage("Looks like the Internet's kinda slow.\n" +
                    "Try it again in a few seconds? Still love ya!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void stoplisten(){
        locationManager.removeUpdates(locationListener);

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
                PassJson = json;

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
                    String photoRef = "";
//                    for(int j = 0; j < photoArr.length() ; j++)
//                    {
//                        photoRef = temp.optString("photo_reference").toString();
//                    }
//                    String photo = temp.getJSONObject("photo").optString("photo_reference");
                    Log.v("names ",name);
                    restNameList.add(name);
                }

            } catch (Throwable t){
                Log.v("Throw","Can't parse");
            }
            namelist = restNameList;

            return restNameList;
        }

        protected void onPostExecute(ArrayList<String> list) {
            //Can get intent of something.
            setup = true;
            Log.v("setup", setup + "");
            int ran = (int)(Math.random() * namelist.size());
            TextView rest1 = (TextView) findViewById(R.id.textView2);
            rest1.setText(namelist.get(ran));

            int ran2 =(int) Math.random() * namelist.size();
            while(ran2 == ran) {ran2 =(int) Math.random() * 20;}
            TextView rest2 = (TextView) findViewById(R.id.textView);
            rest2.setText(namelist.get(ran2));
        }
    }
}


