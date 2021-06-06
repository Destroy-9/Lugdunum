package com.example.lugdunum.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.lugdunum.MapContent;
import com.example.lugdunum.R;
import com.example.lugdunum.Scenario;
import com.example.lugdunum.User;
import com.example.lugdunum.games.CuriosityGameActivity;
import com.example.lugdunum.games.FourviereGameActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

public class HistoryActivity extends FragmentActivity implements LocationListener {

    // Partie histoire
    private TextSwitcher mTextHistory;
    private ImageView mNextBtn;
    private ImageView mBackBtn;
    private ImageView mImage;
    private TextView mPoem;
    private String history[];
    private Scenario scenario;
    private int messageCount;
    private int currentIndex;
    private int gameNumber;
    private boolean trionValidate;
    private boolean curiositesValidate;
    private boolean theatreValidate;
    private boolean theatrePlayValidate;
    private boolean fourviereValidate;
    private boolean trabouleValidate;
    private User mUser;

    // Partie Map
    private LocationManager mLm;
    public static final int PERMS_CALL_ID = 1234;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    ImageView mHelpButton;
    Circle[] interets;
    FragmentManager fragmentManager;
    public final MapContent mContent = new MapContent();


    /****************************************************************************************************
     ****************************************************************************************************
     *
     **********                              Partie HISTOIRE                                   **********
     *
     ****************************************************************************************************
     ****************************************************************************************************/
    public void nonValidate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Attention !")
                .setMessage("Tu n'es pas à l'endroit indiqué. Cela t'empêchera de profiter pleinement de la suite de l'histoire. Souhaites-tu continuer malgré tout ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nextBtn_function();
                        setInvisibleCircle();
                        mContent.incCercle();
                        fragmentManager.beginTransaction().hide(mapFragment).commit();
                        mImage.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentIndex--;
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    public void nextBtn_function(){
        if (gameNumber == 1) {
            Intent intent = new Intent(HistoryActivity.this, CuriosityGameActivity.class);
            startActivity(intent);
        } else if (gameNumber == 2) {
            Intent intent = new Intent(HistoryActivity.this, FourviereGameActivity.class);
            startActivity(intent);
        } else if (gameNumber == 3) {
            HistoryActivity.this.finish();
        }
        mNextBtn.setImageResource(R.drawable.next_button);
        scenario.incState();
        mImage.setImageResource(scenario.getCurrentRhino());
        mImage.setTag(scenario.getCurrentRhino());
        if (mPoem.getVisibility() == View.VISIBLE) {
            mPoem.setVisibility(View.GONE);
        }
        history = scenario.getHistory();
        messageCount = history.length;
        currentIndex = 0;

        mTextHistory.setText(history[currentIndex]); // set Text in TextSwitcher
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // initialization of booleans
        trionValidate = false;
        curiositesValidate = false;
        theatreValidate = false;
        theatrePlayValidate = false;
        fourviereValidate = false;
        trabouleValidate = false;

        // get The references if Button and TextSwitcher
        mNextBtn = (ImageView) findViewById(R.id.nextButton);
        mBackBtn = (ImageView) findViewById(R.id.previousButton);
        mTextHistory = (TextSwitcher) findViewById(R.id.historySwitcher);
        mImage = (ImageView) findViewById(R.id.image);
        mPoem = (TextView) findViewById(R.id.poem);

        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        mTextHistory.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub
                // create a TextView
                TextView t = new TextView(HistoryActivity.this);
                // set the gravity of text to top and center horizontal
                t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                // set displayed text size
                t.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24.f);
                t.setTextColor(Color.parseColor("#870000"));
                return t;
            }
        });

        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        Animation in_back = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out_back = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // set the animation type to TextSwitcher
        mTextHistory.setInAnimation(in);
        mTextHistory.setOutAnimation(out);

        // Array of String to Show In TextSwitcher
        mUser = (User) getApplicationContext();
        scenario = new Scenario(mUser.getPseudo());
        history = scenario.getHistory();
        messageCount = history.length;
        // to keep current Index of textID array
        currentIndex = -1;

        //text and image appear on start
        currentIndex++;
        mTextHistory.setCurrentText(history[currentIndex]);
        mImage.setImageResource(scenario.getCurrentRhino());
        mImage.setTag(scenario.getCurrentRhino());
        mBackBtn.setVisibility(View.GONE);
        // ClickListener for NEXT button
        // When clicked on Button TextSwitcher will switch between labels
        // The current label will go out and next label will come in with specified animation
        mNextBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                if (currentIndex == messageCount) {
                    if ((scenario.getState() == 0 && !trionValidate) || (scenario.getState() == 1 && !curiositesValidate) || (scenario.getState() == 5 && !theatreValidate) || (scenario.getState() == 6 && !theatrePlayValidate) || (scenario.getState() == 10 && !fourviereValidate) || (scenario.getState() == 14 && !trabouleValidate)){
                        nonValidate();
                    }
                    else {
                        nextBtn_function();
                    }
                }
                else {
                    mTextHistory.setText(history[currentIndex]); // set Text in TextSwitcher
                }

                // If index reaches maximum then reset it
                if (currentIndex == messageCount-1 || messageCount == 1){
                    gameNumber = scenario.getContent(mImage, mPoem, mNextBtn);
                }

                if (mBackBtn.getVisibility() == View.GONE){
                    mBackBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                mTextHistory.setInAnimation(in_back);
                mTextHistory.setOutAnimation(out_back);

                currentIndex--;
                // If index reaches maximum then reset it
                if (currentIndex == 0 && scenario.getState() == 0){
                    if (mBackBtn.getVisibility() == View.VISIBLE){
                        mBackBtn.setVisibility(View.GONE);
                    }
                }
                else if (currentIndex == -1) {
                    scenario.decState();
                    history = scenario.getHistory();
                    messageCount = history.length;
                    currentIndex = messageCount - 1;
                    mImage.setImageResource(scenario.getCurrentRhino());
                    mImage.setTag(scenario.getCurrentRhino());
                    if (mPoem.getVisibility() == View.VISIBLE) {
                        mPoem.setVisibility(View.GONE);
                    }

                    gameNumber = scenario.getContent(mImage, mPoem, mNextBtn);

                }
                else{
                    mNextBtn.setImageResource(R.drawable.next_button);
                    mImage.setImageResource(scenario.getCurrentRhino());
                    mImage.setTag(scenario.getCurrentRhino());
                    if (mPoem.getVisibility() == View.VISIBLE) {
                        mPoem.setVisibility(View.GONE);
                    }
                }
                mTextHistory.setText(history[currentIndex]); // set Text in TextSwitcher

                mTextHistory.setInAnimation(in);
                mTextHistory.setOutAnimation(out);
            }
        });


 /**************************************************************************************************
  **************************************************************************************************
  *
  *********                              Partie MAP                                        *********
  *
  **************************************************************************************************
  **************************************************************************************************/
        fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        fragmentManager.beginTransaction().hide(mapFragment).commit();
        mImage.setVisibility(View.VISIBLE);
        //mDescription = (TextView) findViewById(R.id.description);
        mHelpButton = (ImageView) findViewById(R.id.HelpButton);
        mHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImage.getVisibility() == View.VISIBLE) {
                    mImage.setVisibility(View.GONE);
                    if ((Integer)mImage.getTag() == 0 && mPoem.getVisibility() == View.VISIBLE){
                        mPoem.setVisibility(View.GONE);
                    }
                    fragmentManager.beginTransaction().show(mapFragment).commit();
                }
                else {
                    mImage.setVisibility(View.VISIBLE);
                    if ((Integer)mImage.getTag() == 0 && mPoem.getVisibility() == View.GONE){
                        mPoem.setVisibility(View.VISIBLE);
                    }
                    fragmentManager.beginTransaction().hide(mapFragment).commit();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    private void checkPermission (){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMS_CALL_ID);
            return;
        }

        mLm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (mLm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            mLm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 2, this);
        }
        if (mLm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            mLm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 2, this);
        }
        if (mLm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            mLm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 2, this);
        }
        loadMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMS_CALL_ID) {
            checkPermission();
        }
    }

    private void loadMap() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            @SuppressWarnings("MissingPermission")
            public void onMapReady(GoogleMap googleMap) {
                HistoryActivity.this.mMap = googleMap;
                googleMap.setMyLocationEnabled(true);
                afficheCerles();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();


        if (mLm != null){
            mLm.removeUpdates(this);
        }
    }


    public void afficheCerles() {

        CircleOptions cercleType1 = new CircleOptions();
        cercleType1
                .center(new LatLng(45.756773, 4.816251))
                .radius(100)
                .strokeColor(Color.RED)
                .strokeWidth(5)
                .fillColor(0x55FF6666);

        interets = new Circle[7];
        for (int i = 0; i < interets.length; i++) {
            switch (i) {
                case 0: //fontaine trion
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.7561167, 4.8185359));
                    break;
                case 1: //jardin des curiosités
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.755072, 4.821873));
                    break;
                case 2: // places minimes
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.7571, 4.8210));
                    break;
                case 3: //entrée théatre
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.7591, 4.8213));
                    break;
                case 4: //théatreJeu
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.7597, 4.8199));
                    break;
                case 5: //fourvière
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.7624, 4.8219));
                    break;
                case 6: //entrée escalier
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.7627, 4.8253));
                    break;
            }
        }
        setInvisibleCircle();
        Toast.makeText(this, "vive babar", Toast.LENGTH_LONG).show();
        interets[mContent.numCercle].setVisible(true);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Toast.makeText(this, "Location: "+latitude+"/"+longitude, Toast.LENGTH_LONG).show();

        if (mMap != null) {
            LatLng here = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here,15));
            interets[mContent.numCercle].setVisible(true);

            testPos(location);

        }
    }

    public void testPos(@NonNull Location location){
        float results [] = new float [1];

        Location.distanceBetween(
                interets[mContent.numCercle].getCenter().latitude, interets[mContent.numCercle].getCenter().longitude,
                location.getLatitude(), location.getLongitude(),
                results
        );
        if (results[0]<interets[mContent.numCercle].getRadius()){
            setInvisibleCircle();
            fragmentManager.beginTransaction().hide(mapFragment).commit();
            mImage.setVisibility(View.VISIBLE);
            setGame(mContent.numCercle);
            mContent.incCercle();
        }
    }

    public void setInvisibleCircle(){
        for (int i=0; i< interets.length; i++){
            interets[i].setVisible(false);
        }
    }

    public void setGame(int numero){
        switch (numero){
            case 0:
                trionValidate = true;
                break;
            case 1:
                curiositesValidate = true;
                break;
            case 3:
                theatreValidate = true;
                break;
            case 4:
                theatrePlayValidate = true;
                break;
            case 5:
                fourviereValidate = true;
                break;
            case 6:
                trabouleValidate = true;
                break;

        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}