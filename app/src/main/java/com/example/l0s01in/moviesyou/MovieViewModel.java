package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.LiveData;

import android.arch.lifecycle.ViewModel;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Models.Review;
import com.example.l0s01in.moviesyou.Models.Trailer;

import java.util.List;

public class MovieViewModel extends ViewModel{
    private static final String POPULAR = "popular";
    private static final String TOP_RATED = "top_rated";

    private MovieRepository movieRepo;

    public MovieViewModel(){
        movieRepo = new MovieRepository();
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

}
