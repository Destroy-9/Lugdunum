package com.example.lugdunum;

import android.app.Application;

public class User extends Application {

    private String mPseudo;
    private String mPassword;
    private String mID;

    public User (String pseudo, String password){
        mPseudo = pseudo;
        mPassword = password;
        mID = null;
    }

    public User (){
        mPseudo = null;
        mPassword = null;
        mID = null;
    }

    public String getPseudo() {
        return mPseudo;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getID () { return mID; }

    public void setPseudo(String pseudo) {
        mPseudo = pseudo;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public void setPseudoPassword(String pseudo, String password){
        mPseudo = pseudo;
        mPassword = password;
    }

    public void setID (String ID){
        mID = ID;
    }

    public boolean debugMode () {
        if (this.getPseudo().equals("DijkstraGo2021") && this.getPassword().equals("PWEB<3")) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName= " + mPseudo + "mPassword= " + mPassword + '\'' +
                '}';
    }
}
