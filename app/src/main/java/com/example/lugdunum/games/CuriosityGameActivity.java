package com.example.lugdunum.games;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.lugdunum.HistoryActivity;
import com.example.lugdunum.R;

public class CuriosityGameActivity extends Activity {

    private Button mButtonCrayonHidden;
    private Button mButtonNextGameIsBridge;
    private Button mButtonMoreInfo;
    private Button mButtonBridgeHidden;
    private Button mButtonNextGameIsChair;
    private Button mButtonChairHidden;
    private Button mButtonCloseView;
    private Button mButtonWrongTouch;
    private Button mButtonEnd;


    private ConstraintLayout mLayout;
    //private TextView mytext;
    //private View view;
    //private LayoutInflater LayoutInflater;
    //private PopupWindow popupWindow;
    private TextView mytxtvw;
    private TextView mTextViewWellSpotted;
    private TextView mTextViewFindBridge;
    private Toast toast;
    //private Context context;



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
        setContentView(R.layout.activity_curiosity_game);

        mButtonCrayonHidden = (Button) findViewById(R.id.crayonHidden);
        mButtonNextGameIsBridge = (Button) findViewById(R.id.nextGameIsBridge);
        mButtonMoreInfo = (Button) findViewById(R.id.moreInfo);
        mButtonBridgeHidden = (Button) findViewById(R.id.bridgeHidden);
        mButtonNextGameIsChair = (Button) findViewById(R.id.nextGameIsChair);
        mButtonChairHidden = (Button) findViewById(R.id.chairHidden);
        mButtonCloseView = (Button) findViewById(R.id.closeView);
        mButtonWrongTouch = (Button) findViewById(R.id.wrongTouch);
        mButtonEnd = (Button) findViewById(R.id.nextEnd);
        mytxtvw=(TextView)findViewById(R.id.myTextView);
        mTextViewWellSpotted =(TextView)findViewById(R.id.wellSpotted);
        mTextViewFindBridge =(TextView)findViewById(R.id.findBridge);
        mLayout = findViewById(R.id.ConstraintLayout);


        mButtonWrongTouch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(getApplicationContext(),"Essaie encore", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 100, 100); // last two args are X and Y are used for setting position
                toast.setDuration(2000);
                toast.show();
            }
        });


        mButtonCrayonHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toast != null) {
                    toast.cancel();  //if a toast exists it deletes it, allowing you to create a new one
                }
                mTextViewWellSpotted.setVisibility((mTextViewWellSpotted.getVisibility() == View.VISIBLE)
                                             ? View.GONE : View.VISIBLE);
                mytxtvw.setVisibility((mytxtvw.getVisibility() == View.VISIBLE)
                                              ? View.GONE : View.INVISIBLE);
                mButtonWrongTouch.setVisibility(View.INVISIBLE);
                mButtonCrayonHidden.setVisibility(View.INVISIBLE);
                mButtonNextGameIsBridge.setVisibility(View.VISIBLE);
                mButtonMoreInfo.setVisibility(View.VISIBLE);
                mLayout.setBackgroundResource(R.drawable.with_crayon);
            }
        });


        mButtonNextGameIsBridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewWellSpotted.setVisibility((mTextViewWellSpotted.getVisibility() == View.VISIBLE)
                                             ? View.GONE : View.INVISIBLE);
                mTextViewFindBridge.setVisibility((mTextViewFindBridge.getVisibility() == View.VISIBLE)
                                            ? View.GONE : View.VISIBLE);
                mButtonMoreInfo.setVisibility(View.GONE);
                Button button = (Button) v;
                button.setVisibility(View.INVISIBLE);
                mButtonBridgeHidden.setVisibility(View.VISIBLE);
                mLayout.setBackgroundResource(R.drawable.without_bridge);
            }
        });


        mButtonBridgeHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewFindBridge.setVisibility((mTextViewFindBridge.getVisibility() == View.VISIBLE)
                                            ? View.GONE : View.INVISIBLE);
                mLayout.setBackgroundResource(R.drawable.with_bridge);
                mButtonNextGameIsChair.setVisibility(View.VISIBLE);
            }
        });


        mButtonNextGameIsChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setBackgroundResource(R.drawable.without_chair);
                mTextViewFindBridge.setText("Il manque une chaise ici, peux-tu la trouver ?");
                mTextViewFindBridge.setVisibility((mTextViewFindBridge.getVisibility() == View.INVISIBLE)
                        ? View.GONE : View.VISIBLE);
                mButtonChairHidden.setVisibility(View.VISIBLE);
                mButtonNextGameIsChair.setVisibility(View.INVISIBLE);
            }
        });
        mButtonChairHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewFindBridge.setVisibility((mTextViewFindBridge.getVisibility() == View.VISIBLE)
                        ? View.GONE : View.INVISIBLE);
                mLayout.setBackgroundResource(R.drawable.with_chair);
                mButtonCloseView.setVisibility(View.VISIBLE);
                mButtonEnd.setVisibility(View.VISIBLE);
            }
        });

        mButtonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CuriosityGameActivity.this.finish();
            }
        });


        mButtonMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogTour();
            }
        });


        mButtonCloseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogShare();
            }
        });
    }
}
