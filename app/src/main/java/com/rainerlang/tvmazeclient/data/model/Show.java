package com.rainerlang.tvmazeclient.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class Show implements Parcelable {

  public abstract int id();
  public abstract String name();
  @Nullable
  public abstract String summary();
  @Nullable
  public abstract String airdate();
  @Nullable
  public abstract String airtime();
  @Nullable
  public abstract List<String> genres();
  public abstract String type();
  @Nullable
  public abstract ShowImageUrl image();

  public static TypeAdapter<Show> typeAdapter(Gson gson) {
    return new AutoValue_Show.GsonTypeAdapter(gson);
  }

}
