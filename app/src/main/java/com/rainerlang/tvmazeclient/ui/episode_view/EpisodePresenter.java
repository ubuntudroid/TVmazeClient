package com.rainerlang.tvmazeclient.ui.episode_view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import rx.Observable;
import rx.functions.Action1;

import static com.rainerlang.tvmazeclient.Constants.EXTRA_EPISODE;

class EpisodePresenter extends BasePresenter<EpisodeMvp.View> implements EpisodeMvp.Presenter {

  private Episode episode;

  @Override
  public void onFragmentCreated(@NonNull Bundle arguments) {
    episode = arguments.getParcelable(EXTRA_EPISODE);
    sendToView(view -> view.onDataToView(episode));
  }

  @NonNull
  @Override
  public Episode getEpisode() {
    return episode;
  }

  @Override
  public void onGotoShowDetailsClicked() {
    sendToView(view -> view.startShowActivity(episode.show()));
  }

  // here not used
  @Override
  public <T> void makeApiCall(@NonNull Observable<T> observable, @NonNull Action1<T> onNext) { }

}
