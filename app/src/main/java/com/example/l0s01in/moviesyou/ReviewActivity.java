package com.example.l0s01in.moviesyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        String review = getIntent().getStringExtra("review");
        TextView reviewTextView = findViewById(R.id.review_textview);
        reviewTextView.setText(review);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
