package com.rainerlang.tvmazeclient.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rainerlang.tvmazeclient.BuildConfig;
import com.rainerlang.tvmazeclient.R;
import com.rainerlang.tvmazeclient.ui.base.mvp.BaseMvp;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import net.grandcentrix.thirtyinch.TiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;

public abstract class BaseActivity<V extends BaseMvp.View, P extends BasePresenter<V>> extends TiActivity<P, V> implements BaseMvp.View {

  @Nullable
  @BindView(R.id.toolbar) Toolbar toolbar;

  @LayoutRes
  protected abstract int layout();

  protected abstract boolean canBack();

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Icepick.saveInstanceState(this, outState);
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (layout() != 0) {
      setContentView(layout());
      ButterKnife.bind(this);
    }
    Icepick.setDebug(BuildConfig.DEBUG);
    if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
      Icepick.restoreInstanceState(this, savedInstanceState);
    }
    setupToolbarAndStatusBar(toolbar);
  }

  private void setupToolbarAndStatusBar(@Nullable Toolbar toolbar) {
    setSupportActionBar(toolbar);
    if (canBack()) {
      getSupportActionBar().setHomeButtonEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (canBack() && item.getItemId() == android.R.id.home) {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

}
