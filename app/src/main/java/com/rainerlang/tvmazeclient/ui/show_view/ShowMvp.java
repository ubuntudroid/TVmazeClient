package com.rainerlang.tvmazeclient.ui.show_view;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.data.model.Show;
import com.rainerlang.tvmazeclient.ui.base.mvp.BaseMvp;

import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;

public interface ShowMvp {

  interface View extends BaseMvp.View {

    @CallOnMainThread
    void onDataToView(@NonNull Show show);

  }

  interface Presenter extends BaseMvp.Presenter {

    @NonNull
    Show getShow();

    void onFragmentCreated(@NonNull Bundle bundle);

  }

}
