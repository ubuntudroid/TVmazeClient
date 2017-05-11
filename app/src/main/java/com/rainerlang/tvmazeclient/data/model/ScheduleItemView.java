package com.rainerlang.tvmazeclient.data.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.GenericAbstractItem;
import com.rainerlang.tvmazeclient.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleItemView extends GenericAbstractItem<ScheduleItem, ScheduleItemView, ScheduleItemView.ViewHolder> {

  public ScheduleItemView(@NonNull ScheduleItem scheduleItem) {
    super(scheduleItem);
  }

  @Override
  public int getType() {
    return R.id.fastadapter_episode_item_id;
  }

  @Override
  public int getLayoutRes() {
    return R.layout.item_schedule_episode;
  }

  @Override
  public void bindView(ViewHolder viewHolder, List<Object> payloads) {
    super.bindView(viewHolder, payloads);

    final Context ctx = viewHolder.itemView.getContext();
    final ScheduleItem model = getModel();

    mIdentifier = model.id();

    viewHolder.showName.setText(model.showName().isEmpty() ? ctx.getString(R.string.n_a) : model.showName());
    viewHolder.episodeName.setText(model.episodeName());
    viewHolder.airtime.setText(model.airtime().isEmpty() ? ctx.getString(R.string.n_a) : model.airtime());
    viewHolder.runtime.setText(model.runtime() + " " + ctx.getString(R.string.unit_min));
    viewHolder.seasonNr.setText(model.seasonNr());
    viewHolder.episodeNr.setText(model.episodeNr());
  }

  @Override
  public void unbindView(ViewHolder holder) {
    super.unbindView(holder);
  }

  public ViewHolder getViewHolder(View v) {
    return new ViewHolder(v);
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    protected RelativeLayout view;

    @BindView(R.id.schedule_episode_airtime) TextView airtime;
    @BindView(R.id.schedule_episode_runtime) TextView runtime;
    @BindView(R.id.schedule_episode_show_name) TextView showName;
    @BindView(R.id.schedule_episode_episode_name) TextView episodeName;
    @BindView(R.id.schedule_episode_season_nr) TextView seasonNr;
    @BindView(R.id.schedule_episode_episode_nr) TextView episodeNr;

    ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      this.view = (RelativeLayout) view;
    }
  }

}