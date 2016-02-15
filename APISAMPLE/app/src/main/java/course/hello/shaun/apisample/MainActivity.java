package course.hello.shaun.apisample;

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
import android.view.Menu;
import android.view.MenuItem;

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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "APItestingSample";

    public final static String Name = "course.hello.shaun.Demo.Name";
    public final static String Add = "course.hello.shaun.Demo.Add";
    public final static String Rate = "course.hello.shaun.Demo.Rate";
    public GoogleApiClient mGoogleApiClient;
    public static final int TYPE_RESTAURANT = 79;
    public HashMap<String, Place> Restaurants = new HashMap<>();
    public String restNames ="";
    public String searchMap = "";
    public double Lati = 0;
    public double Longi = 0;
    public LocationListener locationListener;
    public LocationManager locationManager;

    private static final int REQUEST_PLACE_PICKER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
                mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                        .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAround(View V){

        final Intent intent = new Intent(this, ShowRest.class);

        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);

        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    if (placeLikelihood.getPlace().getPlaceTypes().contains(TYPE_RESTAURANT)) {
                        Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                        Restaurants.put(placeLikelihood.getPlace().getName().toString(), placeLikelihood.getPlace());
                        restNames += "\n" + placeLikelihood.getPlace().getName().toString();
                        Log.v("inside ", "" + Restaurants.isEmpty() + " " + Restaurants.size());
                        intent.putExtra("restNames", restNames);
                        startActivity(intent);
                    }
                }
                likelyPlaces.release();
            }
        });

        Log.v("size", "" + Restaurants.isEmpty());

        //LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Acquire a reference to the system Location Manager

            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            locationListener = new android.location.LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                Log.v("location lat", ""+location.getLatitude());
                Log.v("location long", ""+location.getLongitude());
                Longi = location.getLongitude();
                Lati = location.getLatitude();
                searchMap= "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
                searchMap+=location.getLatitude();
                searchMap+=",";
                searchMap+=location.getLongitude();
                searchMap+="&radius=1000&types=food&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM";
                Log.v("link", searchMap);
                new GetJson().execute(searchMap);
                stoplisten();
                //new GetJson().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&key=AIzaSyD5smM39XCy0kjibJdhNoAnlPcqTynkObM");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, locationListener);

    }

    public void stoplisten(){
        locationManager.removeUpdates(locationListener);
    }

    public void showPlaces(View V) {
            // BEGIN_INCLUDE(intent)

            try {
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                Intent intent = intentBuilder.build(this);
                // Start the Intent by requesting a result, identified by a request code.
                startActivityForResult(intent, REQUEST_PLACE_PICKER);

                // Hide the pick option in the UI to prevent users from starting the picker
                // multiple times.

            } catch (GooglePlayServicesRepairableException e) {
                GooglePlayServicesUtil
                        .getErrorDialog(e.getConnectionStatusCode(), this, 0);
            } catch (GooglePlayServicesNotAvailableException e) {
                Toast.makeText(this, "Google Play Services is not available.",
                        Toast.LENGTH_LONG)
                        .show();
            }
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // BEGIN_INCLUDE(activity_result)
        if (requestCode == REQUEST_PLACE_PICKER) {
            // This result is from the PlacePicker dialog.

            if (resultCode == Activity.RESULT_OK) {
                /* User has picked a place, extract data.
                   Data is extracted from the returned intent by retrieving a Place object from
                   the PlacePicker.
                 */
                final Place place = PlacePicker.getPlace(data, this);

                /* A Place object contains details about that place, such as its name, address
                and phone number. Extract the name, address, phone number, place ID and place types.
                 */
                final CharSequence name = place.getName();
                final CharSequence address = place.getAddress();
                final CharSequence phone = place.getPhoneNumber();
                final float rating = place.getRating();
                final String placeId = place.getId();
                String attribution = PlacePicker.getAttributions(data);
                if(attribution == null){
                    attribution = "";
                }
                Intent intent = new Intent(this, DisplayPlacesActivity.class);

                intent.putExtra(Name,name);
                intent.putExtra(Add, address);
                intent.putExtra(Rate, rating);
                startActivity(intent);

                // Print data to debug log
                Log.d(TAG, "Place selected: " + placeId + " (" + name.toString() + ")");


            } else {
                Log.d(TAG, "Nothing man.");
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        // END_INCLUDE(activity_result)
    }

}


class GetJson extends AsyncTask<String, Void, JSONObject> {

    private Exception exception;

    protected JSONObject doInBackground(String... params) {

        String json = null;
        try {
            //String url = "https://maps.googleapis.com/maps/api/place/radarsearch/json?location=51.503186,-0.126446&radius=5000&types=museum&key=YOUR_API_KEY";
            URL urls= new URL(params[0]);
            //URL urls = new URL(url);
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
        } catch (Throwable t){

        }
        return jsonObject;
    }

    protected void onPostExecute(JSONObject json) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}