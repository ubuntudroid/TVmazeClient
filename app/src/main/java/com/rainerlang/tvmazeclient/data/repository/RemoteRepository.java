package com.rainerlang.tvmazeclient.data.repository;

import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.data.TVmazeApi;
import com.rainerlang.tvmazeclient.data.model.Episode;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RemoteRepository implements Repository {

  private TVmazeApi api;

  @Inject
  public RemoteRepository(TVmazeApi api) {
    this.api = api;
  }

  @Override
  public Observable<List<Episode>> getScheduleByCountryAndDate(@NonNull String country, @NonNull String date) {
    return api.getSchedule(country, date);
  }

}
