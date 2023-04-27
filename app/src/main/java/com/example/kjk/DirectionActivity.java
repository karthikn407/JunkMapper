package com.example.kjk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DirectionActivity extends AppCompatActivity {

    private double currentLat;
    private double currentLng;
    private double markerLat;
    private double markerLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Get the current and marker locations from the intent
        Intent intent = getIntent();
        currentLat = intent.getDoubleExtra("currentLat", 0);
        currentLng = intent.getDoubleExtra("currentLng", 0);
        markerLat = intent.getDoubleExtra("markerLat", 0);
        markerLng = intent.getDoubleExtra("markerLng", 0);

// Build the directions URL using OpenStreetMap
        String url = "https://www.openstreetmap.org/directions?engine=osrm_car&route=" +
                currentLat + "%2C" + currentLng + "%3B" + markerLat + "%2C" + markerLng;

// Launch the user's default navigation app
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(mapIntent);

// Finish this activity so that the user is taken back to the map
        finish();
    }
}