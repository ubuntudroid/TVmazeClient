package com.rainerlang.tvmazeclient.helper;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxHelper {

  public static <T> Observable.Transformer<T, T> applySchedulersIo() {
    return observable ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
  }

}
