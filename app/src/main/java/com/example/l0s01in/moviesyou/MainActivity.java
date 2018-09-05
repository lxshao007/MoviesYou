package com.example.l0s01in.moviesyou;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        if (NetworkUtils.checkNetwork(this) == true) {
            new LoadPictures(POPULAR).execute();
        } else {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
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
        if (itemClicked == R.id.action_popular) {
            new LoadPictures(POPULAR).execute();
            return true;
        }
        if (itemClicked == R.id.action_top_rated) {
            new LoadPictures(TOP_RATED).execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class LoadPictures extends AsyncTask<Void, Void, List<Movie>>{

        final String type;
        public LoadPictures(String type) {
            this.type = type;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(Void... voids) {
            try {
                return NetworkUtils.getMovies(type);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            mProgressBar.setVisibility(View.INVISIBLE);
            if (movies != null){
                ImageListAdapter mAdapter = new ImageListAdapter(movies);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }
}
