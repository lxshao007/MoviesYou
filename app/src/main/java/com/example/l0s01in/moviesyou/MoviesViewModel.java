package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MoviesViewModel extends ViewModel{
    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";

    private static MutableLiveData<List<Movie>> movies;
    public LiveData<List<Movie>> getMovies(){
        if (movies == null) {
            movies = new MutableLiveData<List<Movie>>();
            loadMovies();
        }
        return movies;
    }

    private void loadMovies() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    movies.postValue(NetworkUtils.getMovies(POPULAR));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void updateMovies(String type){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    movies.postValue(NetworkUtils.getMovies(type));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
