package com.example.utspapb2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.utspapb2.R;
import com.example.utspapb2.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, SensorEventListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private boolean hasPickedStyle = false;

    private float latitude = (float) -7.775288;
    private float longitude = (float) 110.373761;

    private SensorManager sensorManager;

    private Sensor sensorLight;


    private SharedPreferences sharedPreferences;
    public static final String preference = "com.example.utspapb2";
    public static final String LATITUDE_KEY = "latitudeKey";
    public static final String LONGITUDE_KEY = "longitudeKey";
    public static final String POI_NAME_KEY = "poinameKey";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sharedPreferences = getSharedPreferences(preference, Context.MODE_PRIVATE);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(POI_NAME_KEY, "Sekolah Vokasi UGM");

        editor.putFloat(LATITUDE_KEY, latitude);

        editor.putFloat(LONGITUDE_KEY, longitude);

        editor.commit();


    }

    protected void onStart() {
        super.onStart();
        if (sensorLight != null) {
            // menambahkan listener magnetometer ke sensormanager apabila ada
            sensorManager.registerListener(this, sensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            System.out.println("No Light Sensor");
            hasPickedStyle = true;
        }
    }

    protected void onStop() {
        super.onStop();
        // unregister listener yang diregister ke sensormanager sebelumnya
        sensorManager.unregisterListener(this);
        hasPickedStyle = false;
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng custom = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(custom).title("Sekolah Vokasi UGM"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(custom, 15));

        setMapLongClick(mMap);
        setPoiClick(mMap);
        enableMyLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                hasPickedStyle = true;
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                hasPickedStyle = true;
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                hasPickedStyle = true;
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                hasPickedStyle = true;
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void setMapLongClick(final GoogleMap map) {

        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                map.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }

    private void setPoiClick(final GoogleMap map) {
        map.setOnPoiClickListener(new GoogleMap.OnPoiClickListener() {
            @Override
            public void onPoiClick(@NonNull PointOfInterest pointOfInterest) {
                Marker poiMarker = mMap.addMarker(new MarkerOptions()
                        .position(pointOfInterest.latLng)
                        .title(pointOfInterest.name));
                poiMarker.showInfoWindow();


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(POI_NAME_KEY, pointOfInterest.name);

                float latitudeFloat = (float) pointOfInterest.latLng.latitude;
                editor.putFloat(LATITUDE_KEY, latitudeFloat);

                float longitudeFloat = (float) pointOfInterest.latLng.longitude;
                editor.putFloat(LONGITUDE_KEY, longitudeFloat);

                editor.apply();

                Intent intent = new Intent("android.intent.action.MY_SHARED_PREFERENCES_CHANGED");
                getApplicationContext().sendBroadcast(intent);

            }
        });
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];

        switch (sensorType) {
            case (Sensor.TYPE_LIGHT):
                changeMapTheme(currentValue);
                break;
            default:
                break;
        }
    }


    public void changeMapTheme(float value) {
        if (!hasPickedStyle) {
            if (value <= 20000) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
            else if (value > 20000) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}