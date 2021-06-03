package com.example.lugdunum.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lugdunum.R;
import com.example.lugdunum.games.CuriosityGameActivity;
import com.example.lugdunum.games.FourviereGameActivity;

public class ChoiceGameActivity extends AppCompatActivity {

    private Button mGoCuriosites;
    private Button mGoFourviere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choixgame);

        //creation of the link between choicegame and fourvieregame
        mGoFourviere = (Button) findViewById(R.id.button_game_3);
        mGoFourviere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceGameActivity.this, FourviereGameActivity.class);
                startActivity(intent);
            }
        });

        //creation of the link between curiositygame and fourvieregame
        mGoCuriosites = (Button) findViewById(R.id.button_game_1);
        mGoCuriosites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceGameActivity.this, CuriosityGameActivity.class);
                startActivity(intent);
            }
        });

    }
}