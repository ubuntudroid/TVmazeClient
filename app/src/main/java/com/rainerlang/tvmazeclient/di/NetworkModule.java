package com.rainerlang.tvmazeclient.di;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.rainerlang.tvmazeclient.App;
import com.rainerlang.tvmazeclient.BuildConfig;
import com.rainerlang.tvmazeclient.data.AutoValueGson_MyTypeAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class NetworkModule {

  @Provides
  @NonNull
  @Singleton
  Cache provideCache(@NonNull App app) {

    File httpCacheDirectory = new File(app.getCacheDir(), "responses");
    int cacheSize = 10 * 1024 * 1024;

    return new Cache(httpCacheDirectory, cacheSize);
  }

  @Provides
  @NonNull
  @Singleton
  HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    return new HttpLoggingInterceptor()
        .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.HEADERS : HttpLoggingInterceptor.Level.NONE);
  }

  @Provides
  @NonNull
  @Singleton
  Gson provideGson() {
    return new GsonBuilder()
        .serializeNulls()
        .registerTypeAdapterFactory(new AutoValueGson_MyTypeAdapterFactory())
        .create();
  }

  @Provides
  @NonNull
  @Singleton
  OkHttpClient provideOkHttpClient
      (
          @NonNull Cache cache,
          @NonNull HttpLoggingInterceptor httpLoggingInterceptor
      )
  {
    OkHttpClient.Builder okHttpClientBuilder =
        new OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor);

    okHttpClientBuilder.addInterceptor(new LoggingInterceptor.Builder()
        .loggable(BuildConfig.DEBUG)
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .tag("LoggingI")
        .request("Request")
        .response("Response")
        .addHeader("version", BuildConfig.VERSION_NAME)
        .build());

    OkHttpClient okHttpClient = okHttpClientBuilder.build();
    return okHttpClient;
  }

}

