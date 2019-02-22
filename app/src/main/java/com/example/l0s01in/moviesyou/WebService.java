package com.example.l0s01in.moviesyou;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("{type}?api_key=")
    Call<com.example.l0s01in.moviesyou.Models.Movies> getMovies(@Path("type") String type);

    @GET("{id}?api_key=")
    Call<com.example.l0s01in.moviesyou.Models.Movie> getMovie(@Path("id") String id);
}
