package com.example.l0s01in.moviesyou;


import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Models.Movies;
import com.example.l0s01in.moviesyou.Models.Reviews;
import com.example.l0s01in.moviesyou.Models.Trailers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("{type}?api_key=dc9c32d2f24f8caf8469ab79fa450506")
    Call<Movies> getMovies(@Path("type") String type);

    @GET("{id}?api_key=dc9c32d2f24f8caf8469ab79fa450506")
    Call<Movie> getMovie(@Path("id") String id);

    @GET("{id}/videos?api_key=dc9c32d2f24f8caf8469ab79fa450506")
    Call<Trailers> getTrailers(@Path("id") String id);

    @GET("{id}/reviews?api_key=dc9c32d2f24f8caf8469ab79fa450506")
    Call<Reviews> getReviews(@Path("id") String id);

}
