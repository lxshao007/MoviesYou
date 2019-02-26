package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Models.Review;
import com.example.l0s01in.moviesyou.Models.Trailer;
import com.example.l0s01in.moviesyou.Utils.ModelUtils;
import com.example.l0s01in.moviesyou.Utils.NetworkUtils;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_title) TextView mMovieTitle;
    @BindView(R.id.movie_image) ImageView mMovieImage;
    @BindView(R.id.movie_year) TextView mMovieYear;
    @BindView(R.id.movie_length) TextView mMovieLength;
    @BindView(R.id.movie_rate) TextView mMovieRate;
    @BindView(R.id.movie_brief) TextView mMovieBrief;

    MovieViewModel movieViewModel;

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
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getTrailers(movie.getId()).observe(this, trailers -> {
                TrailerListAdapter trailerListAdapter = new TrailerListAdapter(getApplicationContext(), trailers);
                ListView trailerListView = findViewById(R.id.trialer_list);
                trailerListView.setAdapter(trailerListAdapter);
                setListViewHeightBasedOnChildren(trailerListView);
            });
        movieViewModel.getReviews(movie.getId()).observe(this, reviews ->  {

                ReviewListAdapter reviewListAdapter = new ReviewListAdapter(getApplicationContext(), reviews);
                ListView reviewListView = findViewById(R.id.review_list);
                reviewListView.setAdapter(reviewListAdapter);
                setListViewHeightBasedOnChildren(reviewListView);
        });
    }


    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
