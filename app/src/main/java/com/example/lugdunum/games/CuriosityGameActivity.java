package com.example.lugdunum.games;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;
import android.widget.ViewAnimator;

import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lugdunum.R;

public class CuriosityGameActivity extends Activity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;


    ConstraintLayout layout;
    TextView mytext;
    private View view;
    private LayoutInflater LayoutInflater;
    private PopupWindow popupWindow;
    private TextView mytxtvw;
    private TextView bienvu;
    private TextView text2;
    private Toast toast;
    private Context context;






    private void showDialogShare() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_window1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }


    private void showDialogTour() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_window);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        mytxtvw=(TextView)findViewById(R.id.myTextView);
        bienvu=(TextView)findViewById(R.id.myTextView1);
        text2=(TextView)findViewById(R.id.myTextView2);



        layout = findViewById(R.id.ConstraintLayout);

        button8.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("WrongConstant")
            @Override

            public void onClick(View v) {
                Toast toast=Toast.makeText(getApplicationContext(),"Essayez encore", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 100, 100); // last two args are X and Y are used for setting position
                toast.setDuration(2000);
                toast.show();

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {



            @Override


            public void onClick(View v) {
                if(toast != null) {
                    toast.cancel();  //if a toast exists it deletes it, allowing you to create a new one
                }

                bienvu.setVisibility((bienvu.getVisibility() == View.VISIBLE)
                                             ? View.GONE : View.VISIBLE);


                mytxtvw.setVisibility((mytxtvw.getVisibility() == View.VISIBLE)
                                              ? View.GONE : View.INVISIBLE);
                button8.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                layout.setBackgroundResource(R.drawable.avectourcrayon);


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bienvu.setVisibility((bienvu.getVisibility() == View.VISIBLE)
                                             ? View.GONE : View.INVISIBLE);
                text2.setVisibility((text2.getVisibility() == View.VISIBLE)
                                            ? View.GONE : View.VISIBLE);
                button3.setVisibility(View.GONE);
                Button button = (Button) v;
                button.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.VISIBLE);
                layout.setBackgroundResource(R.drawable.sanspont);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.setVisibility((text2.getVisibility() == View.VISIBLE)
                                            ? View.GONE : View.INVISIBLE);
                layout.setBackgroundResource(R.drawable.avecpont);
                button5.setVisibility(View.VISIBLE);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundResource(R.drawable.sanschaise);
                button6.setVisibility(View.VISIBLE);
                button5.setVisibility(View.INVISIBLE);


            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundResource(R.drawable.avecchaise);
                button7.setVisibility(View.VISIBLE);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogTour();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogShare();
            }
        });
    }
}
