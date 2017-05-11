package com.rainerlang.tvmazeclient.helper;

import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.data.model.ScheduleItem;

import rx.functions.Func1;

import static com.rainerlang.tvmazeclient.helper.StringHelper.nullToEmpty;

public class EpisodeToScheduleItemFunc implements Func1<Episode, ScheduleItem> {

  @Override
  public ScheduleItem call(Episode episode) {
    return ScheduleItem.builder()
        .id(episode.id())
        .showName(episode.show()==null ? "" : nullToEmpty(episode.show().name()))
        .episodeName(episode.name())
        .seasonNr(String.valueOf(episode.season()))
        .episodeNr(String.valueOf(episode.number()))
        .airtime(episode.airtime())
        .runtime(String.valueOf(episode.runtime()))
        .build();
  }

}