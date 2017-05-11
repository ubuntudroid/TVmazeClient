package com.rainerlang.tvmazeclient.di;

import com.rainerlang.tvmazeclient.data.repository.RemoteRepository;
import com.rainerlang.tvmazeclient.data.repository.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

  @Provides
  Repository provideRepository(RemoteRepository repository) {
    return repository;
  }

}
