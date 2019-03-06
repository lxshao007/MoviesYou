package com.example.l0s01in.moviesyou;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Models.Review;
import com.example.l0s01in.moviesyou.Models.Trailer;
import com.example.l0s01in.moviesyou.database.MovieDatabase;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";

    private MovieRepository movieRepo;
    private MovieDatabase database;
    LiveData<List<Movie>> favoriteMovies;

    public MovieViewModel(Application application){
        super(application);
        movieRepo = new MovieRepository();
        database = MovieDatabase.getInstance(this.getApplication());
    }

    public LiveData<List<Movie>> getMovies(String type) {
        return movieRepo.getMovies(type);
    }

    public LiveData<Movie> getMovie(String id) {
        return movieRepo.getMovie(id);
    }

    public LiveData<List<Trailer>> getTrailers(String id) {
        return movieRepo.getTrailers(id);
    }
    public LiveData<List<Review>> getReviews(String id) {
        return movieRepo.getReviews(id);
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return database.movieDao().loadFavoriteMovies();

    }

    public LiveData<Movie> getFavoriteMoviebyId(Movie movie){
        Log.i("loadFavorite____", database.movieDao().loadFavoriteMovieById(movie.getId()).toString());
        return database.movieDao().loadFavoriteMovieById(movie.getId());
    }

    public void addFavorite(Movie movie) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.movieDao().insertMovie(movie);
            }
        });
    }

    public void deleteFavorite(Movie movie) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                database.movieDao().deleteMovie(movie);
            }
        });
    }

}
