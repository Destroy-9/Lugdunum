package com.example.lugdunum;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);
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