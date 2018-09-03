package com.example.l0s01in.moviesyou.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class ModelUtils {
    private static final Gson gson = new Gson();
    public static <T> T toObject(String json, TypeToken<T> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> String toString(T object, TypeToken<T> typeToken) {
        return gson.toJson(object, typeToken.getType());
    }
}
