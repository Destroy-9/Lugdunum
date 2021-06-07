package com.example.lugdunum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lugdunum.R;
import com.example.lugdunum.User;


public class ChoiceJourneyActivity extends AppCompatActivity {

    private ImageView mSettingsIcon;
    private Button mMapButton;
    private Button mGameButton;
    private ImageView mHistoryButton;
    private TextView mIntroText;
    private TextView mSignatureText;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_journey);

        mSettingsIcon = (ImageView) findViewById(R.id.settingsIcon);
        mMapButton = (Button) findViewById(R.id.mapButton);
        mGameButton = (Button) findViewById(R.id.gameButton);
        mHistoryButton = (ImageView) findViewById(R.id.historyButton);
        mIntroText = (TextView) findViewById(R.id.introduction_paragraph);
        mSignatureText = (TextView) findViewById(R.id.signature);

        mIntroText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 19.f);
        mSignatureText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 19);


        mUser = (User) getApplicationContext();
        // Debug mode activated
        if (mUser.debugMode()){
            mMapButton.setVisibility(View.VISIBLE);
            mGameButton.setVisibility(View.VISIBLE);
        }

        //creation of the link between ChoiceJourneyActivity and SettingsActivity
        mSettingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceJourneyActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        mMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(ChoiceJourneyActivity.this, "Chargement du parcours", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChoiceJourneyActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });


        // Creation of the link between mainActivity and ChoiceJourneyActivity
        mGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceJourneyActivity.this, ChoiceGameActivity.class);
                startActivity(intent);
            }
        });

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceJourneyActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}