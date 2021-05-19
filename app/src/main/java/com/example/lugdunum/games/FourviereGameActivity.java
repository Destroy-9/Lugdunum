package com.example.lugdunum.games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lugdunum.R;

public class FourviereGameActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mStatue1;
    private ImageView mStatue2;
    private ImageView mStatue3;
    private ImageView mStatue4;
    private ImageView mImageView;

    private boolean mEnableTouchEvents;

    private int mScore;
    private int mCurrentEndroit;
    private int [] mTabEndroit;
    private int mIndexEndroit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourviere_game);

        startGame();

        mTabEndroit = new int[]{4, 1, 2, 3};

        mStatue1 = (ImageView) findViewById(R.id.statue1);
        mStatue2 = (ImageView) findViewById(R.id.statue2);
        mStatue3 = (ImageView) findViewById(R.id.statue3);
        mStatue4 = (ImageView) findViewById(R.id.statue4);
        mStatue1.setTag(1);
        mStatue2.setTag(2);
        mStatue3.setTag(3);
        mStatue4.setTag(4);
        mStatue1.setOnClickListener(this);
        mStatue2.setOnClickListener(this);
        mStatue3.setOnClickListener(this);
        mStatue4.setOnClickListener(this);

        mImageView = (ImageView) findViewById(R.id.endroit);
        mImageView.setImageDrawable(Drawable.createFromPath("Endroit " + mTabEndroit[mIndexEndroit])); //A CHANGER AVEC IMAGE FOND

        mEnableTouchEvents = true;

    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (mEnableTouchEvents = true) {

            mEnableTouchEvents = false;

            if ( responseIndex == mTabEndroit[mIndexEndroit] ){
                mScore ++;
            }

            if ( mIndexEndroit == 3 ) {
                if (mScore != 4){
                    tryAgain();
                }else {
                    endGame();
                }

            }else {
                mIndexEndroit++;
            }

            mText.setText("Endroit " + mTabEndroit[mIndexEndroit]);


        }

    }

    private void startGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bienvenue")
                .setMessage("Regarde autour de toi, cela te permettra d'associer les statues au bon environnement !")
                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Start
                        mScore = 0;
                        mIndexEndroit = 0;
                        mText.setText("Endroit " + mTabEndroit[mIndexEndroit]);

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void tryAgain() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Dommage")
                .setMessage("Tu as seulement eu " + mScore + " réponses positives!")
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Try Again
                        mScore = 0;
                        mIndexEndroit = 0;
                        mText.setText("Endroit " + mTabEndroit[mIndexEndroit]);

                    }
                })
                .setNeutralButton("Aled", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        builder.setTitle("Aide")
                                .setMessage("4 1 2 3")
                                .setPositiveButton("Go", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mScore = 0;
                                        mIndexEndroit = 0;
                                        mText.setText("Endroit " + mTabEndroit[mIndexEndroit]);
                                    }
                                })
                                .setCancelable(false)
                                .create()
                                .show();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bravo !")
                .setMessage("Tu as associé toutes les statues")
                .setPositiveButton("FIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}