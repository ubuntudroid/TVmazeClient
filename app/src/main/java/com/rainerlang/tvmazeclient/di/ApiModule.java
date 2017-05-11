package com.rainerlang.tvmazeclient.di;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.rainerlang.tvmazeclient.data.TVmazeApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.rainerlang.tvmazeclient.Constants.TVMAZE_API_ENDPOINT;

@Module
public class ApiModule {

  @Provides
  @NonNull
  @Singleton
  String provideBaseUrl() {
    return TVMAZE_API_ENDPOINT;
  }

  @Provides
  @NonNull
  @Singleton
  Retrofit provideRetrofit
      (
          @NonNull Gson gson,
          @NonNull OkHttpClient okHttpClient,
          @NonNull String baseUrl
      )
  {
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build();
  }

  @Provides
  @NonNull
  @Singleton
  public TVmazeApi provideTVmazeApi(@NonNull Retrofit retrofit) {
    return retrofit.create(TVmazeApi.class);
  }

}
