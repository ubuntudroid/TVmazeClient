package com.rainerlang.tvmazeclient.ui.base.mvp;

import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.helper.RxHelper;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx.RxTiPresenterSubscriptionHandler;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class BasePresenter<V extends BaseMvp.View> extends TiPresenter<V> implements BaseMvp.Presenter {

  private boolean apiCalled;
  private final RxTiPresenterSubscriptionHandler subscriptionHandler = new RxTiPresenterSubscriptionHandler(this);

  @Override
  public void manageSubscription(@NonNull Subscription subscription) {
    subscriptionHandler.manageSubscription(subscription);
  }

  @Override
  public boolean isApiCalled() {
    return apiCalled;
  }

  @Override
  public void onError(@NonNull Throwable throwable) {
    apiCalled = true;
    // eg Crashlytics, Dialog, SnackBar, Toast,...
    throwable.printStackTrace();
  }

  @Override
  public <T> void makeApiCall(@NonNull Observable<T> observable, @NonNull Action1<T> onNext) {
    manageSubscription(
        observable
            .compose(RxHelper.applySchedulersIo())
            .subscribe(onNext, this::onError, () -> apiCalled = true)
    );
  }

}
