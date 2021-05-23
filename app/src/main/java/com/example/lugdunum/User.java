package com.example.lugdunum;

public class User {

    private String mPseudo;
    private String mPassword;

    public User (String pseudo, String password){
        mPseudo = pseudo;
        mPassword = password;
    }

    /*public User (){
        mPseudo = null;
        mPassword = null;
    }*/

    public String getPseudo() {
        return mPseudo;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPseudo(String pseudo) {
        mPseudo = pseudo;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public void setAll(String pseudo, String password){
        mPseudo = pseudo;
        mPassword = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName= " + mPseudo + "mPassword= " + mPassword + '\'' +
                '}';
    }
}
