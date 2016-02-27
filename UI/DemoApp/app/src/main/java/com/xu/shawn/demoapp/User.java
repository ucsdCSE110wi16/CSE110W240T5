package com.xu.shawn.demoapp;

import java.util.ArrayList;

/**
 * Created by diana on 16/2/8.
 * This class is used to save user preferences in a Firebase account.
 */
public class User {

    private String uid;
    private ArrayList<Integer> pref = new ArrayList<>();

//    private int s1;
//    private int s2;
//    private int s3=50;
//    private int s4=50;
//    private int s5=50;
//    private int s6=50;
//    private int s7=50;
//    private int s8=50;
//    private int s9=50;
//    private int s10=50;
//    private int s11=50;
//    private int s12=50;

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
//    public void setSa(int s){ this.sa = s;}
//    public void setSb(int s){ this.sb = s;}
//    public void setSc(int s){ this.sc = s;}
//    public void setSd(int s){ this.sd = s;}
//    public void setSe(int s){ this.se = s;}
//    public void setSf(int s){ this.sf = s;}
//    public void setSg(int s){ this.sg = s;}
//    public void setSh(int s){ this.sh = s;}
//    public void setSi(int s){ this.si = s;}
//    public void setSj(int s){ this.sj = s;}
//    public void setSk(int s){ this.sk = s;}
//    public void setSl(int s){ this.sl = s;}


    public String getUid() {return this.uid;}
    public ArrayList<Integer> getPref() { return this.pref;}
//    public int getSa() {return this.sa;}
//    public int getSb() {return this.sb;}
//    public int getSc() {return this.sc;}
//    public int getSd() {return this.sd;}
//    public int getSe() {return this.se;}
//    public int getSf() {return this.sf;}
//    public int getSg() {return this.sg;}
//    public int getSh() {return this.sh;}
//    public int getSi() {return this.si;}
//    public int getSj() {return this.sj;}
//    public int getSk() {return this.sk;}
//    public int getSl() {return this.sl;}



}
