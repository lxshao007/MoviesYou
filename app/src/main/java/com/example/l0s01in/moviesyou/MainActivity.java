package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.l0s01in.moviesyou.Models.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";

    MovieViewModel viewModel;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.loading_indicator) ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovies(POPULAR).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                Log.i("movies_____", movies.toString());
                ImageListAdapter mAdapter = new ImageListAdapter(movies);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

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
        if (itemClicked == R.id.action_popular) {
            viewModel.getMovies(POPULAR);
            return true;
        }
        if (itemClicked == R.id.action_top_rated) {
            viewModel.getMovies(TOP_RATED);
            return true;
        }
        if (itemClicked == R.id.action_favorite) {
            Toast.makeText(this, "favorite clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
