package com.example.lugdunum;

public class MapContent {

    public int numCercle;

    public MapContent(){
        numCercle = 0;
    }

    public void incCercle(){
        if(numCercle<6){
            numCercle++;
        }
    }
}
