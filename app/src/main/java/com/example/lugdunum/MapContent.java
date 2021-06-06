package com.example.lugdunum;

public class mapContent {

    public int numCercle;

    public mapContent(){
        numCercle = 0;
    }

    public void incCercle(){
        if(numCercle<6){
            numCercle++;
        }
    }
}
