package course.hello.shaun.apisample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;


import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import java.util.HashMap;

public class ShowRest extends AppCompatActivity {

    private static final String TAG = "showrest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_show_rest);
        Intent intent = getIntent();



        HashMap<CharSequence, Place> hashMap = (HashMap<CharSequence, Place>)intent.getSerializableExtra("map");
        Log.i(TAG, String.format("Showtest test"+hashMap.size()));
        String resturants = "";
        for (HashMap.Entry<CharSequence, Place> entry : hashMap.entrySet())
        {
            resturants+=entry.getKey() + "\n";
            Log.i(TAG, String.format(resturants));
        }
        //TextView textView = new TextView(this);
        TextView testView = new TextView(this);
        testView.setTextSize(40);
        testView.setText(resturants);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.rt);
        layout.addView(testView);



    }

}
