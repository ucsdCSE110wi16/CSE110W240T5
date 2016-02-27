package com.xu.shawn.demoapp;

import java.util.ArrayList;

/**
 * Created by diana on 16/2/8.
 * This class is used to save user preferences in a Firebase account.
 */
public class User {

    private String uid;
    private ArrayList<Integer> pref = new ArrayList<>();

    public User (){
        for(int i=0; i<2; i++){
            pref.add(0);
        }
        for(int i=2; i<12; i++){
            pref.add(50);
        }
    }

    public void setUid(String s){ this.uid = s;}
    public void setPref(ArrayList<Integer> s){ this.pref = s;}


    public String getUid() {return this.uid;}
    public ArrayList<Integer> getPref() { return this.pref;}




}
