package com.example.lugdunum;

public class MapContent {
    /** -------------------------------------------------------------------------------------------------------------
     * la Map ne peut pas être envoyée ailleurs que dans le historyActivity
     * --> si on veut créer avant, il faut pas utiliser la map
     * --> si on crée le tableau d'interets dans historyActivity, il faut un token de vérification ici pour qu'elle ne soit crée que 1 fois --> SOLUTION RETENUE
     *           ----------------------------------------------------------------------------------------------------- **/

    public int numActiv;
    public int numCercle;

    public MapContent (){
        numCercle=0;
        numActiv=0;



    }
    public void incCercle(){
        if (numCercle<6){
            numCercle++;
        }
    }
}
