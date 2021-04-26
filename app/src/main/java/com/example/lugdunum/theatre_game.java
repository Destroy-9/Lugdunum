package com.example.lugdunum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class theatre_game extends AppCompatActivity {
    private TextView question1;
    private ImageView img;
    private Button found_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre_game);

        // Wire widgets
        question1 = (TextView) findViewById(R.id.question);
        img = (ImageView) findViewById(R.id.findThisPlace);
        found_btn = (Button) findViewById(R.id.yes_btn);

        found_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}