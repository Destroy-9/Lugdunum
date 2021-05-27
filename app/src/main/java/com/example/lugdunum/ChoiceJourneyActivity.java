package com.example.lugdunum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChoiceJourneyActivity extends AppCompatActivity {

    private ImageView mSettingsIcon;
    private Button mMapButton;
    private Button mGameButton;
    private ImageView mHistoryButton;


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

        mMapButton = (Button) findViewById(R.id.mapButton);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ChoiceJourneyActivity.this, "Chargement du parcours", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChoiceJourneyActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


        //creation of the link between mainActivity and ChoiceJourneyActivity
        mGameButton = (Button) findViewById(R.id.gameButton);
        mGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceJourneyActivity.this, ChoiceGameActivity.class);
                startActivity(intent);
            }
        });

        mHistoryButton = (ImageView) findViewById(R.id.historyButton);
        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceJourneyActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}