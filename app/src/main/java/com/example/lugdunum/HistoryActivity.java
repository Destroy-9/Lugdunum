package com.example.lugdunum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.lugdunum.games.CuriosityGameActivity;
import com.example.lugdunum.games.FourviereGameActivity;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import static android.net.wifi.WifiConfiguration.Status.strings;
import static androidx.core.content.ContextCompat.startActivity;

public class HistoryActivity extends FragmentActivity implements LocationListener {

    // Partie histoire
    private TextSwitcher mTextHistory;
    private Button mNextBtn;
    private Button mBackBtn;
    private ImageView mImage;
    private ImageView mPoem;
    private String history[];
    private Scenario scenario;
    private int messageCount;
    private int currentIndex;
    private int gameNumber;

    // Partie Map
    private LocationManager mLm;
    public static final int PERMS_CALL_ID = 1234;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    ImageView mHelpButton;
    //TextView mDescription;
    Circle[] interets;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

/***************************************************************************************************
****************************************************************************************************
*
**********                              Partie HISTOIRE                                   **********
*
****************************************************************************************************
****************************************************************************************************/

        // get The references if Button and TextSwitcher
        mNextBtn = (Button) findViewById(R.id.nextButton);
        mBackBtn = (Button) findViewById(R.id.backButton);
        mTextHistory = (TextSwitcher) findViewById(R.id.historySwitcher);
        mImage = (ImageView) findViewById(R.id.image);
        mPoem = (ImageView) findViewById(R.id.poem);

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
        Animation in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        Animation in_back = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out_back = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // set the animation type to TextSwitcher
        mTextHistory.setInAnimation(in);
        mTextHistory.setOutAnimation(out);

        // Array of String to Show In TextSwitcher
        scenario = new Scenario();
        history = scenario.getHistory();
        messageCount = history.length;
        // to keep current Index of textID array
        currentIndex = -1;

        //text and image appear on start
        currentIndex++;
        mTextHistory.setCurrentText(history[currentIndex]);
        mImage.setImageResource(scenario.getCurrentRhino());
        mBackBtn.setVisibility(View.GONE);
        // ClickListener for NEXT button
        // When clicked on Button TextSwitcher will switch between labels
        // The current label will go out and next label will come in with specified animation
        mNextBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                // If index reaches maximum then reset it
                if (currentIndex == messageCount-1 || messageCount == 1){
                    gameNumber = scenario.getContent(mImage, mPoem, mNextBtn);
                }
                if (currentIndex == messageCount) {
                    if (gameNumber == 1){
                        Intent intent = new Intent(HistoryActivity.this, CuriosityGameActivity.class);
                        startActivity(intent);
                    }
                    else if (gameNumber == 2){
                        Intent intent = new Intent(HistoryActivity.this, FourviereGameActivity.class);
                        startActivity(intent);
                    }
                    else if (gameNumber == 3){
                        HistoryActivity.this.finish();
                    }
                    mNextBtn.setText("Suivant");
                    scenario.incState();
                    mImage.setImageResource(scenario.getCurrentRhino());
                    mPoem.setImageResource(0);
                    history = scenario.getHistory();
                    messageCount = history.length;
                    currentIndex = 0;
                }
                mTextHistory.setText(history[currentIndex]); // set Text in TextSwitcher

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
                    mNextBtn.setText("Suivant");
                    messageCount = history.length;
                    currentIndex = messageCount - 1;
                    mImage.setImageResource(scenario.getCurrentRhino());
                    mPoem.setImageResource(0);

                    gameNumber = scenario.getContent(mImage, mPoem, mNextBtn);

                }
                else{
                    mImage.setImageResource(scenario.getCurrentRhino());
                    mPoem.setImageResource(0);
                    mNextBtn.setText("Suivant");
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
                    fragmentManager.beginTransaction().show(mapFragment).commit();
                }
                else {
                    mImage.setVisibility(View.VISIBLE);
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

        interets = new Circle[11];
        for (int i = 0; i < interets.length; i++) {
            switch (i) {
                case 0:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.756773, 4.816251));
                    break;
                case 1:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.755072, 4.821873));
                    break;
                case 2:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.75617, 4.820167));
                    break;
                case 3:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.756939, 4.819727));
                    break;
                case 4:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.75645, 4.821691));
                    break;
                case 5:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.758034, 4.821782));
                    break;
                case 6:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.758713, 4.819754));
                    break;
                case 7:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.762301, 4.822372));
                    break;
                case 8:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.762678, 4.823016));
                    break;
                case 9:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.76107, 4.824555));
                    break;
                case 10:
                    interets[i] = mMap.addCircle(cercleType1);
                    interets[i].setCenter(new LatLng(45.762111, 4.82746));
                    break;
                default:
                    System.out.println("ca passe ici");
            }
        }
        interets[0].setVisible(true);
        interets[1].setVisible(false);
        interets[2].setVisible(false);
        interets[3].setVisible(false);
        interets[4].setVisible(false);
        interets[5].setVisible(false);
        interets[6].setVisible(false);
        interets[7].setVisible(false);
        interets[8].setVisible(false);
        interets[9].setVisible(false);
        interets[10].setVisible(false);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Toast.makeText(this, "Location: "+latitude+"/"+longitude, Toast.LENGTH_LONG).show();

        if (mMap != null) {
            LatLng here = new LatLng(latitude, longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here,15));
            //mMap.addMarker(new MarkerOptions().position(here).title("Vous êtes ici"));
            testPos(location);
        }
    }

    public void testPos(@NonNull Location location){
        int res=0;
        Toast.makeText(this, "ca passe ici", Toast.LENGTH_LONG).show();
        //LatLng test = new LatLng(45.756796,4.816242);
        float results [] = new float [1];
        while (!interets[res].isVisible() && res<10){
            res++;
        }
        Location.distanceBetween(
                interets[res].getCenter().latitude, interets[res].getCenter().longitude,
                location.getLatitude(), location.getLongitude(),
                results
        );
        if (results[0]<interets[res].getRadius()){
            interets[res].setVisible(false);
            interets[res+1].setVisible(true);
            mImage.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction().hide(mapFragment).commit();
            setGame(res);

        }
        Toast.makeText(this, "ca passe la", Toast.LENGTH_LONG).show();
    }

    public void setGame(int numero){
        Intent intent;
        switch (numero){
            case 0:
                intent = new Intent(HistoryActivity.this, CuriosityGameActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(HistoryActivity.this, FourviereGameActivity.class);
                startActivity(intent);
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