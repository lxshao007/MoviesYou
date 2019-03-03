package com.example.l0s01in.moviesyou.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "FavoriteMovie")
public class FavoriteMovie {
    @ColumnInfo(name = "id")
    @PrimaryKey
    int id;

    @ColumnInfo(name = "isFavorited")
    boolean isFavorited;

    @ColumnInfo(name = "posterPath")
    String posterPath;

    public FavoriteMovie(int id, boolean isFavorited, String posterPath) {
        this.id = id;
        this.isFavorited = isFavorited;
        this.posterPath = posterPath;
    }
}
