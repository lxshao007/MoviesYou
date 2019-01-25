package com.example.l0s01in.moviesyou;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.l0s01in.moviesyou.Models.Review;
import com.example.l0s01in.moviesyou.Models.Trailer;
import com.example.l0s01in.moviesyou.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class DetailActivityViewModel extends ViewModel{

    private MutableLiveData<List<Trailer>> trailers;
    private MutableLiveData<List<Review>> reviews;

    public LiveData<List<Trailer>> getTrailes(String id) {
        if (trailers == null) {
            trailers = new MutableLiveData<List<Trailer>>();
            loadTrailers(id);
        }
        return trailers;
    }

    private void loadTrailers(String id) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    trailers.postValue(NetworkUtils.getTrailers(id));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public LiveData<List<Review>> getReviews(String id) {
        if(reviews == null) {
            reviews = new MutableLiveData<List<Review>>();
            loadReviews(id);
        }
        return reviews;
    }

    private void loadReviews(String id) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reviews.postValue(NetworkUtils.getReviews(id));
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
