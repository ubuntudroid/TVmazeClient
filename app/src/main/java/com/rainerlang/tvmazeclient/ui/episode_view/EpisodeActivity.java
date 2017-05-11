package com.rainerlang.tvmazeclient.ui.episode_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.rainerlang.tvmazeclient.R;
import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.ui.base.BaseActivity;
import com.rainerlang.tvmazeclient.ui.base.mvp.BasePresenter;

import net.grandcentrix.thirtyinch.TiPresenter;

import static com.rainerlang.tvmazeclient.Constants.EXTRA_EPISODE;

public class EpisodeActivity extends BaseActivity {

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

  public static void start(@NonNull Context ctx, @NonNull Episode episode) {
    Intent intent = new Intent(ctx, EpisodeActivity.class);
    intent.putExtra(EXTRA_EPISODE, episode);
    ctx.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState == null) {
      @SuppressWarnings("ConstantConditions")
      Fragment toAdd = EpisodeFragment.newInstance(getIntent().getExtras().getParcelable(EXTRA_EPISODE));
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.container, toAdd, toAdd.getClass().getSimpleName())
          .commit();
    }
  }

}
