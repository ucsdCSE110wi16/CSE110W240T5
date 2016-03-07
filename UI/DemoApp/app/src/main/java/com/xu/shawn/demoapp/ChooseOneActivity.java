package com.xu.shawn.demoapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class ChooseOneActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    public GoogleApiClient mGoogleApiClient;
    public LocationListener locationListener;
    public LocationManager locationManager;
    public String searchMap = "";
    public String PassJson = "";
    public double lati;
    public double longi;
    public ArrayList<String> namelist = new ArrayList<>();
    public ArrayList<String> photolist = new ArrayList<>();
    public String restName1 = "Loading..";
    public String restName2 = "Loading..";
    public int[] PreValue;
    public Bitmap bitmap1;
    public Bitmap bitmap2;
    public int RestIndex1;
    public int RestIndex2;
    public String[] PreList = {"japanese", "chinese","american","vietnamese","mexican","italian","french",
                                "fastfood","thai"};
    public ProgressDialog dialog;
    public boolean SetFirst = false;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    private static final String[] LOCATION_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION
    };

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

        //Put out a dialog for loading elements.
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading restaurants now!");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();

        RestaurantsPicker();

        TextView rest1 = (TextView) findViewById(R.id.textView2);
        rest1.setText(restName1);

        TextView rest2 = (TextView) findViewById(R.id.textView);
        rest2.setText(restName2);

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 500, locationListener);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                if(count > 1){
                    Refresh();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(namelist.size() > 0)
        {
            Intent intent = new Intent(this, RestInfoActivity.class);
            intent.putExtra("json", PassJson);
            intent.putExtra("index", RestIndex1);
            intent.putExtra("name", restName1);
            intent.putExtra("pic", bitmap1);
            Log.v("name", restName1);
            startActivity(intent);
        }
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
    public void onClick2(View v) {

        if(namelist.size() > 0)
        {
            Intent intent = new Intent(this, RestInfoActivity.class);
            intent.putExtra("json", PassJson);
            intent.putExtra("name", restName2);
            intent.putExtra("index", RestIndex2);
            intent.putExtra("pic", bitmap2);
            Log.v("name", restName2);
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

    //Pick Restaruants
    public void RestaurantsPicker(){
        //LocationManager and listener
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider
                // Use the new location to update the URL and call API
                // Stop listens when done.

                lati = location.getLatitude();
                longi = location.getLongitude();
                searchMap= "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location.getLatitude()+","+location.getLongitude();
                searchMap+="&keyword=";


                if(SetFirst == false) {

                    for (int i = 0; i < PreList.length; i++) {
                        if (PreValue[i + 2] > 50) {
                            searchMap += "|" + PreList[i];
//                            Log.v("preset", "" + PreValue[i + 2]);
                        }
                    }
                    int pricerange = PreValue[0]/50;
                    searchMap+="&minprice="+pricerange;
                    Log.v("Pricerange", ""+pricerange);
                }
                searchMap+="&radius=5000&types=restaurant&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM";
                //Key word &keyword=japanese
                Log.v("link", searchMap);
                new GetJson().execute(searchMap);
                //new GetJson().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM");
                stoplisten();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {Log.v("Enable", "Provider");}

            public void onProviderDisabled(String provider) { Log.v(" Disable ", "Provider");}
        };
    }

    public void Shake(View view){
        Refresh();
    }
    //Reload this activity.
    public void Refresh(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void stoplisten(){
        //Stop the location updates.
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    class GetJson extends AsyncTask<String, Void, ArrayList<String>> {
        //Fetch Json Object
        private String json = null;
        private ArrayList<String> restNameList = new ArrayList<>();
        private ArrayList<String> restPhotoList = new ArrayList<>();

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

            //Parse JSon
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.optJSONArray("results");

                for(int i=0; i < jsonArray.length(); i++){
                    //Get the name of the restuarants.
                    JSONObject temp = jsonArray.getJSONObject(i);
                    String name = temp.optString("name").toString();
                    String photoRef = "";
                    if(!temp.isNull("photos")) {
                        //Get the photo references
                        JSONArray pArr = temp.optJSONArray("photos");
                        for (int j = 0; j < pArr.length(); j++) {
                            JSONObject photoObj = pArr.getJSONObject(j);
                            photoRef = photoObj.optString("photo_reference").toString();
                        }
                    }

                    //Adding photo and name to the list
//                    Log.v("photo", photoRef);
                    restPhotoList.add(photoRef);
//                    Log.v("names ",name);
                    restNameList.add(name);
                }

            } catch (Throwable t){
                Log.v("Throw", "Can't parse");
            }

            //Put the restaurant names and photo into a list.
            namelist = restNameList;
            photolist = restPhotoList;

            //Randomize restaurants, will fix after preference database is set.
            //SetFirst means the first one is set.
            if(SetFirst == false) {
                RestIndex1 = (int) (Math.random() * namelist.size());
                restName1 = namelist.get(RestIndex1);
            } else {
                do {
                    RestIndex2 = (int) (Math.random() * namelist.size());
                    restName2 = namelist.get(RestIndex2);
                } while (namelist.get(RestIndex1) == namelist.get(RestIndex2));
            }

            //Set the string ref
            String Reference = "";

            //Get the photo from the API.
            try {
                if(SetFirst == false) {
                    if (photolist.get(RestIndex1) != "") {
                        Reference = photolist.get(RestIndex1);
                        String imageUrl1 = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + Reference + "&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM";
                        bitmap1 = BitmapFactory.decodeStream((InputStream) new URL(imageUrl1).getContent());
                        bitmap1 = Bitmap.createScaledBitmap(bitmap1, 200, 200, true);
                    }
                } else {
                    if (photolist.get(RestIndex2) != "") {
                        Reference = photolist.get(RestIndex2);
                        String imageUrl2 = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + Reference + "&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM";
                        bitmap2 = BitmapFactory.decodeStream((InputStream) new URL(imageUrl2).getContent());
                        bitmap2 = Bitmap.createScaledBitmap(bitmap2, 200, 200, true);
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return restNameList;
        }

        protected void onPostExecute(ArrayList<String> list) {
            //Set up the images.
            if(bitmap1 != null) {
                ImageView i = (ImageView) findViewById(R.id.imgViewPic);
                i.setImageBitmap(bitmap1);
            }

            if(bitmap2 != null) {
                ImageView j = (ImageView) findViewById(R.id.imgViewPic2);
                j.setImageBitmap(bitmap2);
            }

            //Set up the textview with the text.
            if(SetFirst == false) {
                Log.v("here", "no");
                TextView rest1 = (TextView) findViewById(R.id.textView2);
                rest1.setText(namelist.get(RestIndex1));
                SetFirst = true;
                searchMap= "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lati+","+longi;
                searchMap+="&keyword=";
                searchMap+="&radius=3000&types=restaurant&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM";
                Log.v("link", searchMap);
                new GetJson().execute(searchMap);

            } else {
                Log.v("Second", "yes");
                TextView rest2 = (TextView) findViewById(R.id.textView);
                rest2.setText(namelist.get(RestIndex2));
                dialog.hide();
                stoplisten();
            }
        }
    }
}