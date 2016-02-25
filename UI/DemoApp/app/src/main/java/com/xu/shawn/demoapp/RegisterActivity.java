package com.xu.shawn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import java.util.*;



public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnGoPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGoPre = (Button)findViewById(R.id.signUp);
        btnGoPre.setOnClickListener(this);

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
        EditText etId = (EditText) findViewById(R.id.email);
        String emailToStore = etId.getText().toString();

        EditText etId1 = (EditText) findViewById(R.id.password);
        String passWord1 = etId1.getText().toString();

        EditText etId2 = (EditText) findViewById(R.id.password2);
        String passWord2 = etId2.getText().toString();

        if(emailToStore.equals("")){
            Toast.makeText(RegisterActivity.this, "Please enter email →_→", Toast.LENGTH_SHORT).show();
        }
        else if(passWord1.equals("")){
            Toast.makeText(RegisterActivity.this, "Please enter passwords →_→", Toast.LENGTH_SHORT).show();
        }
        else if(passWord2.equals("")){
            Toast.makeText(RegisterActivity.this, "Please enter password again", Toast.LENGTH_SHORT).show();
        }
        else if(!passWord1.equals(passWord2)) {
            Toast.makeText(RegisterActivity.this, "Passwords don't match →_→", Toast.LENGTH_SHORT).show();
        }
        else {

            final Firebase ref = new Firebase("https://luminous-inferno-8213.firebaseio.com/");
            ref.createUser(emailToStore, passWord1, new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    System.out.println("Successfully created user account with uid: " + result.get("uid"));

                    String uid = (String) result.get("uid");

                    System.out.println(uid);

                    User newUser = new User();
                    newUser.setSa(0);
                    newUser.setSb(0);
                    newUser.setSc(50);
                    newUser.setSd(50);
                    newUser.setSe(50);
                    newUser.setSf(50);
                    newUser.setSg(50);
                    newUser.setSh(50);
                    newUser.setSi(50);
                    newUser.setSj(50);
                    newUser.setSk(50);
                    newUser.setSl(50);


                    System.out.println(newUser.getSa());
                    System.out.println(newUser.getSb());
                    System.out.println(newUser.getSc());
                    System.out.println(newUser.getSd());
                    System.out.println(newUser.getSe());
                    System.out.println(newUser.getSf());
                    System.out.println(newUser.getSg());
                    System.out.println(newUser.getSh());
                    System.out.println(newUser.getSi());
                    System.out.println(newUser.getSj());
                    System.out.println(newUser.getSk());
                    System.out.println(newUser.getSl());

                    ref.child("user").child(uid).setValue(newUser);

                    Toast.makeText(RegisterActivity.this, "Signed up successfully! Please log in ^o^", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onError(FirebaseError firebaseError) {
                }
            });
        }
    }
}
