package com.example.lugdunum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChoiceJourneyActivity extends AppCompatActivity {

    private ImageView mSettingsIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_journey);

        //creation of the link between ChoiceJourneyActivity and SettingsActivity
        mSettingsIcon = (ImageView) findViewById(R.id.settingsIcon);
        mSettingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceJourneyActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}