package com.example.kjk;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 123;

    private MapView mapView;
    private GeoPoint currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

// Initialize the OpenStreetMap configuration
        Configuration.getInstance().load(this, getPreferences(MODE_PRIVATE));

        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);

// Request location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            mapView.getController().setZoom(12);

// Get current location and center the map on it
            Location currentLocation = getCurrentLocation();
            if (currentLocation != null) {
                GeoPoint startPoint = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
                mapView.getController().setCenter(startPoint);
                this.currentLocation = startPoint;

// Add a marker for the target location
                GeoPoint location = new GeoPoint(12.990898, 80.246508);
                Marker marker = new Marker(mapView);
                marker.setPosition(location);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                mapView.getOverlays().add(marker);
            }
        }

// Add button for directions
        Button btnDirections = findViewById(R.id.btn_directions);
        btnDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDirectionsActivity();
            }
        });
    }

    private Location getCurrentLocation() {
        Location location = null;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            location = LocationUtil.getCurrentLocation(this);
        }
        return location;
    }

    private void launchDirectionsActivity() {
        if (currentLocation != null) {
// Launch the directions activity with the current location and marker location
            Intent intent = new Intent(MapActivity.this, DirectionActivity.class);
            intent.putExtra("currentLat", currentLocation.getLatitude());
            intent.putExtra("currentLng", currentLocation.getLongitude());
            intent.putExtra("markerLat", 12.990898);
            intent.putExtra("markerLng", 80.246508);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapView.getController().setZoom(12);

// Get current location and center the map on it
                    Location currentLocation = getCurrentLocation();
                    if (currentLocation != null) {
                        GeoPoint startPoint = new GeoPoint(currentLocation.getLatitude(), currentLocation.getLongitude());
                        mapView.getController().setCenter(startPoint);
                        this.currentLocation = startPoint;

// Add a marker for the target location
                        GeoPoint location = new GeoPoint(12.990898, 80.246508);
                        Marker marker = new Marker(mapView);
                        marker.setPosition(location);
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                        mapView.getOverlays().add(marker);
                    }
                }
                break;
            }
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Location location = LocationUtil.getCurrentLocation(this);
                    if (location != null) {
                        GeoPoint startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                        mapView.getController().setCenter(startPoint);
                        this.currentLocation = startPoint;
                    }
                }
                break;
            }
        }
    }
}