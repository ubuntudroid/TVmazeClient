package com.rainerlang.tvmazeclient.ui.show_view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.data.model.Show;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import rx.Observable;
import rx.functions.Action1;

import static com.rainerlang.tvmazeclient.Constants.EXTRA_SHOW;

class ShowPresenter extends BasePresenter<ShowMvp.View> implements ShowMvp.Presenter {

  private Show show;

  @Override
  public void onFragmentCreated(@NonNull Bundle arguments) {
    show = arguments.getParcelable(EXTRA_SHOW);
    sendToView(view -> view.onDataToView(show));
  }

  @NonNull
  @Override
  public Show getShow() {
    return show;
  }

  // here not used
  @Override
  public <T> void makeApiCall(@NonNull Observable<T> observable, @NonNull Action1<T> onNext) { }

}
