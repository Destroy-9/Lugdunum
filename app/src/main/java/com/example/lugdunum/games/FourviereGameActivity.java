package com.example.lugdunum.games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lugdunum.R;

public class FourviereGameActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView mstatnoire;
    private ImageView mStatmarie;
    private ImageView mStatcroix;
    private ImageView mStatlion;
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

        mstatnoire = (ImageView) findViewById(R.id.blackStatue);
        mStatmarie = (ImageView) findViewById(R.id.marieStatue);
        mStatcroix = (ImageView) findViewById(R.id.croix);
        mStatlion = (ImageView) findViewById(R.id.lion);
        mstatnoire.setTag(1);
        mStatmarie.setTag(2);
        mStatcroix.setTag(3);
        mStatlion.setTag(4);
        mstatnoire.setOnClickListener(this);
        mStatmarie.setOnClickListener(this);
        mStatcroix.setOnClickListener(this);
        mStatlion.setOnClickListener(this);

        mImageView = (ImageView) findViewById(R.id.endroit);
        switch(mTabEndroit[mIndexEndroit]){
            case 4 : mImageView.setImageResource(R.drawable.fourviere_puzzle_lion);
            break;
            case 2: mImageView.setImageResource(R.drawable.fourviere_puzzle_marie_statue);
            break;
            case 3: mImageView.setImageResource(R.drawable.fourviere_puzzle_cross);
            break;
            case 1: mImageView.setImageResource(R.drawable.fourviere_puzzle_black_statue);
            break;

        }




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
            switch(mTabEndroit[mIndexEndroit]){
                case 4 : mImageView.setImageResource(R.drawable.fourviere_puzzle_lion);
                    break;
                case 2: mImageView.setImageResource(R.drawable.fourviere_puzzle_marie_statue);
                    break;
                case 3: mImageView.setImageResource(R.drawable.fourviere_puzzle_cross);
                    break;
                case 1: mImageView.setImageResource(R.drawable.fourviere_puzzle_black_statue);
                    break;

            }




        }

    }

    private void startGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Bienvenue")
                .setMessage("Regarde autour de toi, cela te permettra d'associer les statues au bon environnement !")
                .setPositiveButton("Jouer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Start
                        mScore = 0;
                        mIndexEndroit = 0;
                        switch(mTabEndroit[mIndexEndroit]){
                            case 4 : mImageView.setImageResource(R.drawable.fourviere_puzzle_lion);
                                break;
                            case 2: mImageView.setImageResource(R.drawable.fourviere_puzzle_marie_statue);
                                break;
                            case 3: mImageView.setImageResource(R.drawable.fourviere_puzzle_cross);
                                break;
                            case 1: mImageView.setImageResource(R.drawable.fourviere_puzzle_black_statue);
                                break;

                        }

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void tryAgain() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Dommage")
                .setMessage("Tu as eu " + mScore + " réponses positives!")
                .setPositiveButton("Essaie encore", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Try Again
                        mScore = 0;
                        mIndexEndroit = 0;
                        switch(mTabEndroit[mIndexEndroit]){
                            case 4 : mImageView.setImageResource(R.drawable.fourviere_puzzle_lion);
                                break;
                            case 2: mImageView.setImageResource(R.drawable.fourviere_puzzle_marie_statue);
                                break;
                            case 3: mImageView.setImageResource(R.drawable.fourviere_puzzle_cross);
                                break;
                            case 1: mImageView.setImageResource(R.drawable.fourviere_puzzle_black_statue);
                                break;

                        }

                    }
                })
                .setNeutralButton("à l'aide!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        builder.setTitle("Aide")
                                .setMessage("4 3 1 2")
                                .setPositiveButton("Go", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mScore = 0;
                                        mIndexEndroit = 0;
                                        switch(mTabEndroit[mIndexEndroit]){
                                            case 4 : mImageView.setImageResource(R.drawable.fourviere_puzzle_lion);
                                                break;
                                            case 2: mImageView.setImageResource(R.drawable.fourviere_puzzle_marie_statue);
                                                break;
                                            case 3: mImageView.setImageResource(R.drawable.fourviere_puzzle_cross);
                                                break;
                                            case 1: mImageView.setImageResource(R.drawable.fourviere_puzzle_black_statue);
                                                break;

                                        }
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