package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Models.Movies;
import com.example.l0s01in.moviesyou.Models.Review;
import com.example.l0s01in.moviesyou.Models.Reviews;
import com.example.l0s01in.moviesyou.Models.Trailer;
import com.example.l0s01in.moviesyou.Models.Trailers;
import com.google.gson.reflect.TypeToken;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private static final TypeToken<Movie> Movie_TYPE = new TypeToken<Movie>(){};

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    WebService webService = retrofit.create(WebService.class);

    public LiveData<List<Movie>> getMovies(String type) {
        final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
        webService.getMovies(type).enqueue(new Callback<Movies>(){
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
             Movies resp = response.body();
             movies.setValue(resp.results);
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });
        return movies;
    }
    public LiveData<Movie> getMovie(String id) {
        final MutableLiveData<Movie> movie = new MutableLiveData<>();
        webService.getMovie(id).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
        return movie;
    }

    public LiveData<List<Trailer>> getTrailers(String id) {
        final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
        webService.getTrailers(id).enqueue(new Callback<Trailers>() {
            @Override
            public void onResponse(Call<Trailers> call, Response<Trailers> response) {
                Trailers resp = response.body();
                trailers.setValue(resp.results);
            }

            @Override
            public void onFailure(Call<Trailers> call, Throwable t) {

            }
        });
        return trailers;
    }

    public LiveData<List<Review>> getReviews(String id) {
        final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
        webService.getReviews(id).enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                Reviews resp = response.body();
                reviews.setValue(resp.results);
            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

            }
        });
        return reviews;
    }


}
