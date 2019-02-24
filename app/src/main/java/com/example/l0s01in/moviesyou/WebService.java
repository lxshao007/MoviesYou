package com.example.l0s01in.moviesyou;


import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Models.Movies;
import com.example.l0s01in.moviesyou.Models.Review;
import com.example.l0s01in.moviesyou.Models.Trailer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("{type}?api_key=")
    Call<Movies> getMovies(@Path("type") String type);

    @GET("{id}?api_key=")
    Call<Movie> getMovie(@Path("id") String id);

    @GET("{id}/videos?api_key=")
    Call<Trailer> getTrailers(@Path("id") String id);

    @GET("{id}/reviews?api_key=")
    Call<Review> getReviews(@Path("id") String id);

}
