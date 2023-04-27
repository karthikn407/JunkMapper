package com.example.kjk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RecyclingGameActivity extends AppCompatActivity {
    private String[] recyclableObjects = {"Glass bottle", "Plastic bottle", "Aluminum can", "Newspaper", "Cardboard box"};
    private ArrayAdapter<String> adapter;
    private String currentObject;
    private ListView listView;
    private int score = 0;
    private int numQuestionsAnswered = 0;

    private Button checkButton;
    private Button submitButton;

    private List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        images = new ArrayList<>();
        images.add(R.drawable.glass_bottle);
        images.add(R.drawable.plastic_bottle);
        images.add(R.drawable.aluminium_can);
        images.add(R.drawable.newspaper);
        images.add(R.drawable.cardboard_box);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, recyclableObjects);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedObject = (String) parent.getItemAtPosition(position);
                if (selectedObject.equals(currentObject)) {
                    Toast.makeText(RecyclingGameActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    score++;
                    TextView scoreTextView = findViewById(R.id.scoreTextView);
                    scoreTextView.setText("Score: " + score);
                    startGame();
                } else {
                    Toast.makeText(RecyclingGameActivity.this, "Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclingGameActivity.this, ScoreActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });

        startGame();
    }


    private void startGame() {
// randomly select a recyclable object to find
        Random random = new Random();
        currentObject = recyclableObjects[random.nextInt(recyclableObjects.length)];
        TextView textView = findViewById(R.id.textView);
        textView.setText("Find the Object");

// randomly select an image to display
        Collections.shuffle(images);
        int currentImageIndex = -1;
        switch (currentObject) {
            case "Glass bottle":
                currentImageIndex = images.indexOf(R.drawable.glass_bottle);
                break;
            case "Plastic bottle":
                currentImageIndex = images.indexOf(R.drawable.plastic_bottle);
                break;
            case "Aluminum can":
                currentImageIndex = images.indexOf(R.drawable.aluminium_can);
                break;
            case "Newspaper":
                currentImageIndex = images.indexOf(R.drawable.newspaper);
                break;
            case "Cardboard box":
                currentImageIndex = images.indexOf(R.drawable.cardboard_box);
                break;
        }

        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(images.get(currentImageIndex));

// clear previous selections
        listView.clearChoices();
        for (int i = 0; i < listView.getCount(); i++) {
            listView.setItemChecked(i, false);
        }
    }
}