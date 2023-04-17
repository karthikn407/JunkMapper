package com.example.kjk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kjk.databinding.ActivityLoginBinding;

public class HomeActivity extends AppCompatActivity {

    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getStartedButton = findViewById(R.id.welcome_button);

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle button click event
                // For example, start a new activity
                Intent intent = new Intent(HomeActivity.this, CaptureScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
