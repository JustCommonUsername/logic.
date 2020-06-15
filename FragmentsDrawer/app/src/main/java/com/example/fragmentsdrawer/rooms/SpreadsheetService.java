package com.example.fragmentsdrawer.rooms;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpreadsheetService {
    @GET("exec")
    Call<String> insert(@Query("email") String email, @Query("problem") String problem,
                        @Query("place") String place, @Query("date") String date, @Query("id") String id);
}
