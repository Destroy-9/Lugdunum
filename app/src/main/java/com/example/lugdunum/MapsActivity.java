package com.example.lugdunum;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements LocationListener {

    private LocationManager mLm;
    public static final int PERMS_CALL_ID = 1234;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    ImageView mHelpButton;
    TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
        fragmentManager.beginTransaction().hide(mapFragment).commit();
        mDescription = (TextView) findViewById(R.id.description);
        mHelpButton = (ImageView) findViewById(R.id.HelpButton);
        mHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDescription.getVisibility() == View.VISIBLE) {
                    mDescription.setVisibility(View.GONE);
                    fragmentManager.beginTransaction().show(mapFragment).commit();
                }
                else {
                    mDescription.setVisibility(View.VISIBLE);
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
                MapsActivity.this.mMap = googleMap;
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

        Circle[] interets = new Circle[11];
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