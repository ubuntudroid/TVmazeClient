package com.rainerlang.tvmazeclient.ui.episode_view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.data.model.Show;
import com.rainerlang.tvmazeclient.ui.base.mvp.BaseMvp;

import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;

public interface EpisodeMvp {

  interface View extends BaseMvp.View {

    @CallOnMainThread
    void onDataToView(@NonNull Episode episode);

    void startShowActivity(@NonNull Show show);

  }

  interface Presenter extends BaseMvp.Presenter {

    @NonNull
    Episode getEpisode();

    void onFragmentCreated(@NonNull Bundle bundle);

    void onGotoShowDetailsClicked();

  }

}
