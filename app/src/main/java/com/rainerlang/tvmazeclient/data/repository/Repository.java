package com.rainerlang.tvmazeclient.data.repository;

import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.data.model.Episode;

import java.util.List;

import rx.Observable;

public interface Repository {

  Observable<List<Episode>> getScheduleByCountryAndDate(@NonNull String country, @NonNull String date);

}
