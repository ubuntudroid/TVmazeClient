package com.rainerlang.tvmazeclient.ui.base.mvp;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiView;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public interface BaseMvp {

  interface View extends TiView {

  }

  interface Presenter {

    void manageSubscription(@NonNull Subscription subscription);

    boolean isApiCalled();

    void onError(@NonNull Throwable throwable);

    <T> void makeApiCall(@NonNull Observable<T> observable, @NonNull Action1<T> onNext);

  }

}
