package com.rainerlang.tvmazeclient.ui.schedule_view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.rainerlang.tvmazeclient.R;
import com.rainerlang.tvmazeclient.helper.DateHelper;
import com.rainerlang.tvmazeclient.ui.base.BaseActivity;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import net.grandcentrix.thirtyinch.TiPresenter;

import java.util.GregorianCalendar;

import static com.rainerlang.tvmazeclient.Constants.COUNTRY_US;

public class ScheduleActivity extends BaseActivity {

  @Override
  protected int layout() {
    return R.layout.activity_base;
  }

  @Override
  protected boolean canBack() {
    return false;
  }

  @NonNull
  @Override
  public TiPresenter providePresenter() {
    return new BasePresenter();
  }

  @Override
  public void onBackPressed() {
    // here handle eg NavDrawer close before closing activity
    super.onBackPressed();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final String date = DateHelper.dateString(new GregorianCalendar());
    getSupportActionBar().setTitle(getString(R.string.schedule) + ": " + date + " (" + COUNTRY_US + ")");

    Fragment toAdd = ScheduleFragment.newInstance(COUNTRY_US, date);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, toAdd, toAdd.getClass().getSimpleName())
        .commit();
  }

}
