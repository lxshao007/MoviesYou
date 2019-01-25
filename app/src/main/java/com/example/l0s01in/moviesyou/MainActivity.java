package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.loading_indicator) ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (NetworkUtils.checkNetwork(this) == false) {
            return;
        }
        retriveMovies();
//        MainActivityViewModel model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
//        model.getMovies().observe(this, new Observer<List<Movie>>() {
//            @Override
//            public void onChanged(@Nullable List<Movie> movies) {
//                ImageListAdapter mAdapter = new ImageListAdapter(movies);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//        });
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private void retriveMovies() {
        LiveData<List<Movie>> movies = null;
        try {
            movies = NetworkUtils.getMovies(POPULAR);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        movies.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                ImageListAdapter mAdapter = new ImageListAdapter(movies);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
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
            MainActivityViewModel.updateMovies(POPULAR);
            return true;
        }
        if (itemClicked == R.id.action_top_rated) {
            MainActivityViewModel.updateMovies(TOP_RATED);
            return true;
        }
        if (itemClicked == R.id.action_favorite) {
            Toast.makeText(this, "favorite clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
