package com.example.l0s01in.moviesyou.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.l0s01in.moviesyou.Models.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM FavoriteMovie WHERE isFavorited = 1")
    List<FavoriteMovie> loadFavoritedMovies();

    @Insert
    void insertMovie(FavoriteMovie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(FavoriteMovie movie);

    @Delete
    void deleteMovie(FavoriteMovie movie);
}
