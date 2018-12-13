package com.example.l0s01in.moviesyou.Utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetworkUtils {
    final private static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    final private static String POPULAR = "popular";
    final private static String TOP_RATED = "top_rated";

    private static final TypeToken<List<Movie>> Movie_LIST_TYPE = new TypeToken<List<Movie>>(){};
    private static final TypeToken<Movie> Movie_TYPE = new TypeToken<Movie>(){};

    final private static String API_KEY = "";

    private static final OkHttpClient client = new OkHttpClient();

    private static Request makeRequest(String url) {
        Log.d("url", url);
        return new Request.Builder().url(url).build();
    }

    private static Response getResponse(Request request) throws IOException {

        return client.newCall(request).execute();
    }

    private static <T> T parseResponse(Response response, TypeToken<T> typeToken) throws IOException, JSONException {
        String responseString = response.body().string();
        JSONObject responseObject = new JSONObject(responseString);
        JSONArray moviesJsonArray = responseObject.getJSONArray("results");
        return ModelUtils.toObject(moviesJsonArray.toString(), typeToken);
    }

    public static List<Movie> getMovies(String type) throws IOException, JSONException {
        String url;
        switch (type) {
            case POPULAR:
                url = BASE_URL + POPULAR + "?api_key=" + API_KEY;
                break;
            case TOP_RATED:
                url = BASE_URL + TOP_RATED + "?api_key=" + API_KEY;
                break;
            default:
                url = BASE_URL + POPULAR + "?api_key=" + API_KEY;
                break;
        }
        return parseResponse(getResponse(makeRequest(url)), Movie_LIST_TYPE);
    }


    public static Movie getMovie(String id) throws IOException, JSONException {
        String url = BASE_URL + id + "?api_key=" + API_KEY;
        return parseResponse(getResponse(makeRequest(url)), Movie_TYPE);
    }

    public static boolean checkNetwork(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }


}
