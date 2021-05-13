package com.example.lugdunum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.android.gms.common.util.Strings;

import static android.net.wifi.WifiConfiguration.Status.strings;

public class HistoryActivity extends AppCompatActivity {

    private TextSwitcher mTextHistory;
    private Button mNextBtn;
    private ImageView mImage;
    private String history[];
    private Scenario scenario;
    private int messageCount;
    private int currentIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // get The references if Button and TextSwitcher
        mNextBtn = (Button) findViewById(R.id.nextButton);
        mTextHistory = (TextSwitcher) findViewById(R.id.historySwitcher);
        mImage = (ImageView) findViewById(R.id.image);

        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        mTextHistory.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub
                // create a TextView
                TextView t = new TextView(HistoryActivity.this);
                // set the gravity of text to top and center horizontal
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                // set displayed text size
                t.setTextSize(24);
                return t;
            }
        });

        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // set the animation type to TextSwitcher
        mTextHistory.setInAnimation(in);
        mTextHistory.setOutAnimation(out);

        // Array of String to Show In TextSwitcher
        scenario = new Scenario();
        history = scenario.getHistory();
        messageCount = history.length;
        // to keep current Index of textID array
        currentIndex = -1;

        //text appear on start
        currentIndex++;
        mTextHistory.setCurrentText(history[currentIndex]);
        // ClickListener for NEXT button
        // When clicked on Button TextSwitcher will switch between labels
        // The current label will go out and next label will come in with specified animation
        mNextBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                // If index reaches maximum then reset it
                if (currentIndex == messageCount-1){
                    mImage.setImageResource(scenario.getContent());
                }
                if (currentIndex == messageCount) {
                    mImage.setImageResource(R.drawable.rhino);
                    scenario.incState();
                    history = scenario.getHistory();
                    messageCount = history.length;
                    currentIndex = 0;
                }
                mTextHistory.setText(history[currentIndex]); // set Text in TextSwitcher
            }
        });

    }
}