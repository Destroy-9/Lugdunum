package com.example.lugdunum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class choixgame extends AppCompatActivity {

    private Button mGoCuriosites;
    private Button mGoTheatre;
    private Button mGoFourviere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choixgame);

        //creation of the link between choixgame and fourvieregame
        mGoFourviere = (Button) findViewById(R.id.button_game_3);
        mGoFourviere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(choixgame.this, fourviere_game.class);
                startActivity(intent);
            }
        });

        //no links for the other games
        mGoTheatre = (Button) findViewById(R.id.button_game_2);
        mGoTheatre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not Available", Toast.LENGTH_SHORT).show();
            }
        });
        mGoCuriosites = (Button) findViewById(R.id.button_game_1);
        mGoCuriosites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Not Available", Toast.LENGTH_SHORT).show();
            }
        });

    }
}