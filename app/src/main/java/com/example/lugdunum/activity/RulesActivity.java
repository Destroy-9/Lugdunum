package com.example.lugdunum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lugdunum.R;

public class RulesActivity extends AppCompatActivity {

    private ImageView mGotoJourneyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        //creation of the link between RulesActivity and ChoiceJourneyActivity
        mGotoJourneyButton = (ImageView) findViewById(R.id.button_to_go_back_to_choicejourneyactivity);
        mGotoJourneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RulesActivity.this, ChoiceJourneyActivity.class);
                startActivity(intent);
            }
        });
    }
}