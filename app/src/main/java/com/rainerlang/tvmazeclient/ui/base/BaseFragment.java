package com.rainerlang.tvmazeclient.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainerlang.tvmazeclient.ui.base.mvp.BaseMvp;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import net.grandcentrix.thirtyinch.TiFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;

public abstract class BaseFragment<V extends BaseMvp.View, P extends BasePresenter<V>> extends TiFragment<P, V> implements BaseMvp.View {

  @Nullable private Unbinder unbinder;

  @LayoutRes
  protected abstract int fragmentLayout();

  protected abstract void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Icepick.saveInstanceState(this, outState);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    boolean hasSavedInstanceState = savedInstanceState != null && !savedInstanceState.isEmpty();
    if (hasSavedInstanceState) {
      Icepick.restoreInstanceState(this, savedInstanceState);
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    if (fragmentLayout() != 0) {
      View view = inflater.inflate(fragmentLayout(), container, false);
      unbinder = ButterKnife.bind(this, view);
      return view;
    }
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    onFragmentCreated(view, savedInstanceState);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }

}
