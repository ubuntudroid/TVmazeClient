package com.rainerlang.tvmazeclient.ui.show_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.rainerlang.tvmazeclient.R;
import com.rainerlang.tvmazeclient.data.model.Show;
import com.rainerlang.tvmazeclient.ui.base.BaseActivity;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import net.grandcentrix.thirtyinch.TiPresenter;

import static com.rainerlang.tvmazeclient.Constants.EXTRA_SHOW;

public class ShowActivity extends BaseActivity {

  @Override
  protected int layout() {
    return R.layout.activity_base;
  }

  @Override
  protected boolean canBack() {
    return true;
  }

  @NonNull
  @Override
  public TiPresenter providePresenter() {
    return new BasePresenter();
  }

  public static void start(@NonNull Context ctx, @NonNull Show show) {
    Intent intent = new Intent(ctx, ShowActivity.class);
    intent.putExtra(EXTRA_SHOW, show);
    ctx.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      @SuppressWarnings("ConstantConditions")
      Fragment toAdd = ShowFragment.newInstance(getIntent().getExtras().getParcelable(EXTRA_SHOW));
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.container, toAdd, toAdd.getClass().getSimpleName())
          .commit();
    }
  }

}
