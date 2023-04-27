package com.example.kjk;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.kjk.ml.Model;
import com.example.kjk.ui.login.LoginActivity;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class CaptureScreenActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;

    private ActivityResultLauncher<Intent> takePictureLauncher;
    private Button captureButton;
    private ImageView imageView;
    private TextView predictedClassTextView;
    private Button gallery;
    private Button binButton;
    int imageSize = 32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_screen);
        imageView = findViewById(R.id.imageView);
        gallery=findViewById(R.id.button2);

        binButton = findViewById(R.id.button_nearbybin);
        predictedClassTextView = findViewById(R.id.classified);

        binButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Capture image logic goes here
                Intent intent = new Intent(CaptureScreenActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
                predictedClassTextView.setText("Prepare your plastic for recycling: Make sure that the plastic you want to recycle is clean and dry. Rinse out any food containers, and remove any labels or caps. It's also important to make sure that the plastic is separated by its recycling number.");
            }
        });

        captureButton = findViewById(R.id.capture);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(CaptureScreenActivity.this,
                            new String[]{Manifest.permission.CAMERA},
                            PERMISSION_REQUEST_CODE);
                } else {

                    dispatchTakePictureIntent();
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }




    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureLauncher.launch(takePictureIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);




        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null)
             {
                Uri imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }







    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open camera
                dispatchTakePictureIntent();
            } else {
                // Permission denied, show message
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
// Handle home action
            Intent intent = new Intent(CaptureScreenActivity.this, HomeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_articles) {
            Intent intent = new Intent(CaptureScreenActivity.this, ArticleActivity.class);
            startActivity(intent);
// Handle activity action
            return true;
        }
        else if (id == R.id.nav_about) {
// Handle help action
            Intent intent = new Intent(CaptureScreenActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        else if (id == R.id.nav_logout) {
// Handle help action
            Intent intent = new Intent(CaptureScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_start_game) {
            Intent intent = new Intent(CaptureScreenActivity.this,RecyclingGameActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }
}
