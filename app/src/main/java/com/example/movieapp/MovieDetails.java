package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetails extends AppCompatActivity {

    ImageView imageView;
    TextView title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ResultsItem resultsItem= (ResultsItem) getIntent().getSerializableExtra("data");

        imageView = findViewById(R.id.imageView2);
        title = findViewById(R.id.detial_title);
        description = findViewById(R.id.details);

        title.setText(resultsItem.getTitle());
        description.setText(resultsItem.getOverview());

        Glide
                .with(getApplicationContext())
                .load("https://image.tmdb.org/t/p/original/" + resultsItem.getPosterPath())
                .placeholder(R.drawable.sr)
                .into(imageView);
    }
}