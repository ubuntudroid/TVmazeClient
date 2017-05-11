package com.rainerlang.tvmazeclient.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class ShowImageUrl implements Parcelable {

  @Nullable
  public abstract String medium();
  @Nullable
  public abstract String original();

  public static TypeAdapter<ShowImageUrl> typeAdapter(Gson gson) {
    return new AutoValue_ShowImageUrl.GsonTypeAdapter(gson);
  }

}
