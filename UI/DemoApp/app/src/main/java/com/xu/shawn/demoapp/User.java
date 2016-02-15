package com.xu.shawn.demoapp;

/**
 * Created by diana on 16/2/8.
 * This class is used to set user preferences in Firebase account.
 */
public class User {

    private String userName;
    private int asian;
    private int european;
    private int hispanic;
    private int african;
    private int others;

    public User(){}

    public User(String userID, int asi, int euro, int hisp, int afri, int othe){

        this.asian = asi;
        this.european = euro;
        this.hispanic = hisp;
        this.african = afri;
        this.others = othe;
    }

    public String getUserName(){
        return userName;
    }

    public int getAsian(){
        return asian;
    }
    public int getEuropean(){
        return european;
    }
    public int getHispanic(){
        return hispanic;
    }
    public int getAfrican(){
        return african;
    }
    public int getOthers(){
        return others;
    }


}
