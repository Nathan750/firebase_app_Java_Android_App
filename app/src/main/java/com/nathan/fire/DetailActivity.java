package com.nathan.fire;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView tvUsername, tvFullnames, tvEmail, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvUsername = findViewById(R.id.tvUsername);
        tvFullnames = findViewById(R.id.tvFullnames);
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword = findViewById(R.id.tvPassword);

        // Get the passed data
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tvUsername.setText(extras.getString("username"));
            tvFullnames.setText(extras.getString("fullnames"));
            tvEmail.setText(extras.getString("email"));
            tvPassword.setText(extras.getString("password"));
        }
    }
}
