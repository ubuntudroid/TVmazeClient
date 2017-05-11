package com.rainerlang.tvmazeclient.data;

import com.rainerlang.tvmazeclient.data.model.Episode;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface TVmazeApi {

  @GET("/schedule")
  Observable<List<Episode>> getSchedule(@Query("country") String country, @Query("date") String date);

}
