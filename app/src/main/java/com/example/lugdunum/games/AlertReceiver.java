package com.example.lugdunum.games;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

public class AlertReceiver extends BroadcastReceiver {
    private static boolean proximity=false;

    public static boolean isProximity() {
        return proximity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("Start onReceive");
        //theatre_game.found_btn.setEnabled(true);
        //intent.putExtra("done", true);
        proximity = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);

    }
}