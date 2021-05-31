package com.example.lugdunum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lugdunum.R;

public class SettingsActivity extends AppCompatActivity {

    private ImageView mRulesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //creation of the link between SettingsActivity and RulesActivity
        mRulesButton = (ImageView) findViewById(R.id.rulesButton);
        mRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });
    }
}