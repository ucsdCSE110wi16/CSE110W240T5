package com.xu.shawn.demoapp;
import com.firebase.client.Firebase;
import com.firebase.client.AuthData;

import com.firebase.client.FirebaseError;
import java.util.*;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnprofile;
    private Button btnRegister;
    Intent intent = new Intent();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnprofile = (Button) findViewById(R.id.email_sign_in_button);
        btnprofile.setOnClickListener(this);

        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.email_sign_in_button:
                //connect to Firebase account to login

                EditText etId = (EditText) findViewById(R.id.email);
                String emailToSearch = etId.getText().toString();

                EditText etId1 = (EditText) findViewById(R.id.password);
                String passwordToSearch = etId1.getText().toString();

                final Firebase ref = new Firebase("https://luminous-inferno-8213.firebaseio.com/");

                // Create a handler to handle the result of the authentication
                Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        // Authenticated successfully with payload authData
                        Map<String, String> map = new HashMap<String, String>();

                        map.put("provider", authData.getProvider());
                        if(authData.getProviderData().containsKey("displayName")) {
                            map.put("displayName", authData.getProviderData().get("displayName").toString());
                        }
                        ref.child("users").child(authData.getUid()).setValue(map);
                        Toast.makeText(LoginActivity.this, "You are logged in.", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this, PreferenceActivity.class);
                        signInButtonClick();
                    }

                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        // Authenticated failed with error firebaseError
                        Toast.makeText(LoginActivity.this, "You are not logged in. Please try again.",
                                Toast.LENGTH_SHORT).show();
                        /*switch (firebaseError.getCode()) {
                            case FirebaseError.USER_DOES_NOT_EXIST:
                                // handle a non existing user
                                break;
                            case FirebaseError.INVALID_PASSWORD:
                                // handle an invalid password
                                break;
                            default:
                                // handle other errors
                                break;
                        }
                        */
                    }
                };
                //check the input username and password match or not
                ref.authWithPassword(emailToSearch, passwordToSearch, authResultHandler);


                break;

            case R.id.register:

                intent = new Intent(this, RegisterActivity.class);
                registerButtonClick();
                break;
            default:
                break;
        }

    }

    private void signInButtonClick() { startActivity(intent);}

    private void registerButtonClick() {  startActivity(intent);}


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.xu.shawn.demoapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.xu.shawn.demoapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
