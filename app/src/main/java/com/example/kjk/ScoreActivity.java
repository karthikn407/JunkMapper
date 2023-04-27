package com.example.kjk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_activity);

// get score from previous activity
        int score = getIntent().getIntExtra("score", 0);

// display score value and emoji based on score
        TextView scoreValueTextView = findViewById(R.id.scoreValueTextView);
        TextView emojiTextView = findViewById(R.id.emojiTextView);
        if (score >= 10) {
            scoreValueTextView.setTextColor(getResources().getColor(R.color.green_color));
            emojiTextView.setText("😊");
        } else if (score >= 5) {
            scoreValueTextView.setTextColor(getResources().getColor(R.color.yellow_color));
            emojiTextView.setText("😐");
        } else {
            scoreValueTextView.setTextColor(getResources().getColor(R.color.red_color));
            emojiTextView.setText("😢");
        }
        scoreValueTextView.setText(String.valueOf(score));

// play again button
        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, RecyclingGameActivity.class);
                startActivity(intent);
            }
        });
    }
}

