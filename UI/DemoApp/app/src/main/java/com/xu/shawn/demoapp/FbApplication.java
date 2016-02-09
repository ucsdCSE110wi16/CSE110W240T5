package com.xu.shawn.demoapp;
import com.firebase.client.Firebase;

/**
 * Created by diana on 16/2/8.
 * This class is just to make sure Firebase works for all activities.
 */


public class FbApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }

}
