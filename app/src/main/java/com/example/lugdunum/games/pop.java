package com.example.lugdunum.games;
import  android.app.Activity;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.lugdunum.R;

public class pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_p_o_p);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }
}
