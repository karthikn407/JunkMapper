package com.example.kjk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kjk.R;
import com.example.kjk.ui.login.LoginActivity;

public class SignupActivity extends AppCompatActivity {

        private EditText editTextUsername, editTextEmail, editTextPassword;
        private Button buttonSignUp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            editTextUsername = findViewById(R.id.editTextName);
            editTextEmail = findViewById(R.id.editTextEmail);
            editTextPassword = findViewById(R.id.editTextPassword);
            buttonSignUp = findViewById(R.id.buttonSignUp);

            buttonSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = editTextUsername.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();

                    if (TextUtils.isEmpty(username)) {
                        editTextUsername.setError("Username is required.");
                        return;
                    }

                    if (TextUtils.isEmpty(email)) {
                        editTextEmail.setError("Email is required.");
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        editTextPassword.setError("Password is required.");
                        return;
                    }

                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);

                    // TODO: Validate email and username are not already in use

                    // TODO: Create new user account
                }
            });
        }
    }


