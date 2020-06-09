package com.example.fragmentsdrawer.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static String fromArrayList(ArrayList<String> arrayList) {
        return new Gson().toJson(arrayList);
    }

    @TypeConverter
    public static ArrayList<String> fromString(String json) {
        Type listType =  new TypeToken<ArrayList<String>>(){}.getType();
        return new Gson().fromJson(json, listType);
    }

}
