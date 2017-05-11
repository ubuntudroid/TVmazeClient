package com.rainerlang.tvmazeclient.di;

import com.rainerlang.tvmazeclient.App;
import com.rainerlang.tvmazeclient.data.repository.Repository;
import com.rainerlang.tvmazeclient.ui.schedule_view.ScheduleFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        ApiModule.class,
        AppModule.class,
        NetworkModule.class,
        RepositoryModule.class
    }
)
public interface AppComponent {

  Repository repository();

  void inject(App app);

  void inject(ScheduleFragment fragment);

}