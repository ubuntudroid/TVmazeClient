package com.rainerlang.tvmazeclient.ui.schedule_view;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.data.model.ScheduleItemView;
import com.rainerlang.tvmazeclient.data.repository.Repository;
import com.rainerlang.tvmazeclient.helper.EpisodeToScheduleItemFunc;
import com.rainerlang.tvmazeclient.helper.RxHelper;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import java.util.ArrayList;

import rx.Observable;

class SchedulePresenter extends BasePresenter<ScheduleMvp.View> implements ScheduleMvp.Presenter {

  private Repository repository;

  private ArrayList<Episode> episodes = new ArrayList<>();

  SchedulePresenter(Repository repository) {
    this.repository = repository;
  }

  @Override
  public void onItemClicked(ScheduleItemView scheduleItemView, int position) {
    manageSubscription(
        Observable.from(episodes)
            .takeFirst(episode -> episode.id()==scheduleItemView.getModel().id())
            .compose(RxHelper.applySchedulersIo())
            .subscribe(episode -> sendToView(view -> view.startEpisodeActivity(episode))));
  }

  @Override
  public void onError(@NonNull Throwable throwable) {
    super.onError(throwable);
  }

  @NonNull
  @Override
  public ArrayList<Episode> getEpisodes() {
    return episodes;
  }

  @Override
  public void onCallApi(@NonNull String country, @NonNull String date) {
    sendToView(ScheduleMvp.View::showProgress);
    makeApiCall(
        repository.getScheduleByCountryAndDate(country, date)
            .flatMap(episodes -> Observable.zip(
                Observable.just(episodes),
                Observable.just(episodes)
                    .flatMapIterable(episodes1 -> episodes1)
                    .map(new EpisodeToScheduleItemFunc())
                    .toList(),
                Pair::new
            )),
        pairList -> {
          sendToView(ScheduleMvp.View::hideProgress);
          episodes = (ArrayList<Episode>) pairList.first;
          sendToView(view -> view.onNotifyAdapter(pairList.second));
        });
  }

}
