package com.rainerlang.tvmazeclient.ui.schedule_view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.data.model.ScheduleItem;
import com.rainerlang.tvmazeclient.data.model.ScheduleItemView;
import com.rainerlang.tvmazeclient.ui.base.mvp.BaseMvp;

import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;

import java.util.ArrayList;
import java.util.List;

interface ScheduleMvp {

  interface View extends BaseMvp.View, SwipeRefreshLayout.OnRefreshListener {

    @CallOnMainThread
    void showProgress();

    @CallOnMainThread
    void hideProgress();

    @CallOnMainThread
    void onNotifyAdapter(@Nullable List<ScheduleItem> items);

    void startEpisodeActivity(@NonNull Episode episode);

  }

  interface Presenter extends BaseMvp.Presenter {

    @NonNull
    ArrayList<Episode> getEpisodes();

    void onCallApi(@NonNull String country, @NonNull String date);

    void onItemClicked(ScheduleItemView episodeItemView, int position);

  }
}
