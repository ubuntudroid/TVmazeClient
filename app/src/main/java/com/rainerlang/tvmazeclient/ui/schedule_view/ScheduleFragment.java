package com.rainerlang.tvmazeclient.ui.schedule_view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.GenericItemAdapter;
import com.rainerlang.tvmazeclient.App;
import com.rainerlang.tvmazeclient.R;
import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.data.model.ScheduleItem;
import com.rainerlang.tvmazeclient.data.model.ScheduleItemView;
import com.rainerlang.tvmazeclient.ui.base.BaseFragment;
import com.rainerlang.tvmazeclient.ui.episode_view.EpisodeActivity;
import com.rainerlang.tvmazeclient.ui.widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import icepick.State;

import static com.rainerlang.tvmazeclient.Constants.EXTRA_COUNTRY;
import static com.rainerlang.tvmazeclient.Constants.EXTRA_DATE;

public class ScheduleFragment extends BaseFragment<ScheduleMvp.View, SchedulePresenter> implements ScheduleMvp.View {

  @BindView(R.id.recycler) RecyclerView recycler;
  @BindView(R.id.refresh) SwipeRefreshLayout refresh;

  private FastAdapter<ScheduleItemView> fastAdapter;
  private GenericItemAdapter<ScheduleItem, ScheduleItemView> itemAdapter;

  @NonNull
  @State String country = "";
  @NonNull
  @State String date = "";

  public static ScheduleFragment newInstance(@NonNull String country, @NonNull String date) {
    ScheduleFragment view = new ScheduleFragment();
    Bundle bundle = new Bundle();
    bundle.putString(EXTRA_COUNTRY, country);
    bundle.putString(EXTRA_DATE, date);
    view.setArguments(bundle);
    return view;
  }

  @Override
  protected int fragmentLayout() {
    return R.layout.layout_refresh_list;
  }

  @NonNull
  @Override
  public SchedulePresenter providePresenter() {
    return new SchedulePresenter(App.get(getActivity().getApplicationContext()).appComponent().repository());
  }

  @Override
  public void onRefresh() {
    getPresenter().onCallApi(getCountry(), getDate());
  }

  @Override
  public void showProgress() {
    refresh.setRefreshing(true);
  }

  @Override
  public void hideProgress() {
    refresh.setRefreshing(false);
  }

  @Override
  public void onNotifyAdapter(@Nullable List<ScheduleItem> items) {
    if (items == null || items.isEmpty()) {
      itemAdapter.clear();
      return;
    }

    itemAdapter.setModel(items);
  }

  @Override
  protected void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    refresh.setOnRefreshListener(this);
    recycler.setHasFixedSize(true);
    recycler.addItemDecoration(new DividerItemDecoration(getContext()));
    recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    initFastAdapter();
    recycler.setAdapter(itemAdapter.wrap(fastAdapter));

    if (savedInstanceState == null || (getPresenter().getEpisodes().isEmpty() && !getPresenter().isApiCalled())) {
      onRefresh();
    }
  }

  @NonNull
  public String getCountry() {
    if (country.isEmpty()) {
      country = getArguments().getString(EXTRA_COUNTRY, "");
    }
    return country;
  }

  @NonNull
  public String getDate() {
    if (date.isEmpty()) {
      date = getArguments().getString(EXTRA_DATE, "");
    }
    return date;
  }

  private void initFastAdapter() {
    fastAdapter = new FastAdapter<>();

    fastAdapter.withOnClickListener((v, adapter, item, position) -> {
      getPresenter().onItemClicked(item, position);
      return true;
    });

    itemAdapter = new GenericItemAdapter<>(o -> {
      if (o != null) {
        return new ScheduleItemView((ScheduleItem) o);
      } else {
        throw new IllegalArgumentException("The passed model can't be created within this Factory");
      }
    });
  }

  public void startEpisodeActivity(@NonNull Episode episode) {
    EpisodeActivity.start(getContext(), episode);
  }

}

