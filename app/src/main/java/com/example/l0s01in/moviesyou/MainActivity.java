package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.ViewModelProviders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.loading_indicator) ProgressBar mProgressBar;

    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";
    private static final String FAVORITE = "favorite";

    private String renderPage;
    private ImageListAdapter mAdapter;
    private String itemSaved;

    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        renderPage = POPULAR;
        if (savedInstanceState != null) {
            renderPage = savedInstanceState.getString("itemSaved") ;
        }
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        switch (renderPage) {
            case POPULAR:
                renderPopular();
                break;
            case TOP_RATED:
                renderTopRate();
                break;
            case FAVORITE:
                renderFavorite();
                break;
            default:
                renderPopular();
                break;
        }
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicked = item.getItemId();
        switch(itemClicked) {
            case R.id.action_popular:
                Toast.makeText(this, "switch to popular", Toast.LENGTH_SHORT).show();
                renderPopular();
                itemSaved = POPULAR;
                return true;
            case R.id.action_top_rated:
                Toast.makeText(this, "switch to top rated", Toast.LENGTH_SHORT).show();
                renderTopRate();
                itemSaved = TOP_RATED;
                return true;
            case R.id.action_favorite:
                Toast.makeText(this, "switch to favorite", Toast.LENGTH_SHORT).show();
                renderFavorite();
                itemSaved = FAVORITE;
                return true;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("itemSaved", itemSaved);
    }

    private void renderPopular() {
        movieViewModel.getMovies(POPULAR).observe(this, movies -> {
            mAdapter = new ImageListAdapter(movies);
            mRecyclerView.setAdapter(mAdapter);
        });
    }

    private void renderTopRate() {
        movieViewModel.getMovies(TOP_RATED).observe(this, movies -> {
            mAdapter = new ImageListAdapter(movies);
            mRecyclerView.setAdapter(mAdapter);
        });
    }

    private void renderFavorite() {
        movieViewModel.getFavoriteMovies().observe(this, movies -> {
            mAdapter = new ImageListAdapter(movies);
            mRecyclerView.setAdapter(mAdapter);
        });
    }

}
