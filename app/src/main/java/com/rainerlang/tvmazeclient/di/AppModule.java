package com.rainerlang.tvmazeclient.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.rainerlang.tvmazeclient.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

  @NonNull
  private final App app;

  public AppModule(@NonNull App app) {
    this.app = app;
  }

  @Provides
  @NonNull
  @Singleton
  App provideApp() {
    return app;
  }

  @Provides
  @NonNull
  @Singleton
  Context provideContext() {
    return app;
  }

}
