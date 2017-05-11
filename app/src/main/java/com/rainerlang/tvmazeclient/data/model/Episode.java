package com.rainerlang.tvmazeclient.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Episode implements Parcelable {

  public abstract int id();
  public abstract int season();
  public abstract int number();
  public abstract int runtime();
  public abstract String name();
  @Nullable
  public abstract String summary();
  @Nullable
  public abstract String airdate();
  public abstract String airtime();
  @Nullable
  public abstract ShowImageUrl image();

  @Nullable
  public abstract Show show();

  public static TypeAdapter<Episode> typeAdapter(Gson gson) {
    return new AutoValue_Episode.GsonTypeAdapter(gson);
  }

}
