package com.rainerlang.tvmazeclient.data.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ScheduleItem implements Parcelable {

  public abstract int id();
  public abstract String showName();
  public abstract String episodeName();
  public abstract String seasonNr();
  public abstract String episodeNr();
  public abstract String airtime();
  public abstract String runtime();

  @NonNull
  public static Builder builder() {
    return new AutoValue_ScheduleItem.Builder()
        .id(-1)
        .showName("")
        .episodeName("")
        .seasonNr("")
        .episodeNr("")
        .airtime("")
        .runtime("");
  }

  @AutoValue.Builder
  public abstract static class Builder {

    public abstract Builder id(int id);
    public abstract Builder showName(String showName);
    public abstract Builder episodeName(String episodeName);
    public abstract Builder seasonNr(String seasonNr);
    public abstract Builder episodeNr(String episodeNr);
    public abstract Builder airtime(String airtime);
    public abstract Builder runtime(String runtime);

    public abstract ScheduleItem build();
  }

}
