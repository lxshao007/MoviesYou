package com.example.l0s01in.moviesyou.Models;



public class Movie {

    public String vote_count;
    public String id;
    public String vote_average;
    public String title;
    public String popularity;
    private String poster_path;
    public String original_language;
    public String original_title;
    public int[] genre_ids;
    public String backdrop_path;
    public boolean adult;
    public String overview;
    public String release_date;

    public String getImgUrl() {
        return "http://image.tmdb.org/t/p/w200/" + poster_path;
    }

}
