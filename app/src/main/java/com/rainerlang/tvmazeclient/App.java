package com.rainerlang.tvmazeclient;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rainerlang.tvmazeclient.di.ApiModule;
import com.rainerlang.tvmazeclient.di.AppComponent;
import com.rainerlang.tvmazeclient.di.AppModule;
import com.rainerlang.tvmazeclient.di.DaggerAppComponent;
import com.rainerlang.tvmazeclient.di.NetworkModule;

public class App extends Application {

  @Nullable
  private volatile AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    App.this.appComponent().inject(this);
  }

  @NonNull
  private AppComponent createAppComponent() {
    return DaggerAppComponent.builder()
        .apiModule(new ApiModule())
        .appModule(new AppModule(this))
        .networkModule(new NetworkModule())
        .build();
  }

  @NonNull
  public AppComponent appComponent() {
    if (appComponent == null) {
      synchronized (App.class) {
        if (appComponent == null) {
          appComponent = createAppComponent();
        }
      }
    }
    //noinspection ConstantConditions
    return appComponent;
  }
  @NonNull
  public static App get(@NonNull Context ctx) {
    return (App) ctx.getApplicationContext();
  }

}
