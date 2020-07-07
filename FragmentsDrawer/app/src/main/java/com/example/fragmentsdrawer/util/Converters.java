package com.example.fragmentsdrawer.util;

import android.util.Pair;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static String fromArrayList(ArrayList<Pair<String, String>> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @TypeConverter
    public static ArrayList<Pair<String, String>> fromString(String json) {
        Type listType =  new TypeToken<ArrayList<Pair<String, String>>>(){}.getType();
        return new Gson().fromJson(json, listType);
    }

}
