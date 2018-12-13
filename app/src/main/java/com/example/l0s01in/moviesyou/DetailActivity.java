package com.example.l0s01in.moviesyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Utils.ModelUtils;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_title) TextView mMovieTitle;
    @BindView(R.id.movie_image) ImageView mMovieImage;
    @BindView(R.id.movie_year) TextView mMovieYear;
    @BindView(R.id.movie_length) TextView mMovieLength;
    @BindView(R.id.movie_rate) TextView mMovieRate;
    @BindView(R.id.movie_brief) TextView mMovieBrief;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Movie movie = ModelUtils.toObject(getIntent().getStringExtra("movies"), new TypeToken<Movie>(){});
        mMovieTitle.setText(movie.getTitle());
        Picasso.with(getApplicationContext())
                .load(movie.getImgUrl())
                .placeholder(R.drawable.place_holder)
                .into(mMovieImage);
        mMovieYear.setText(movie.getRelease_date());
        mMovieRate.setText(movie.getVote_average());
        mMovieBrief.setText(movie.getOverview());
    }
}
