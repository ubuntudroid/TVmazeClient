package com.rainerlang.tvmazeclient.ui.episode_view;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rainerlang.tvmazeclient.R;
import com.rainerlang.tvmazeclient.data.model.Episode;
import com.rainerlang.tvmazeclient.data.model.Show;
import com.rainerlang.tvmazeclient.ui.base.BaseFragment;
import com.rainerlang.tvmazeclient.ui.show_view.ShowActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static android.os.Build.VERSION_CODES.N;
import static android.text.Html.FROM_HTML_MODE_LEGACY;
import static com.rainerlang.tvmazeclient.Constants.EXTRA_EPISODE;
import static com.rainerlang.tvmazeclient.helper.StringHelper.nullToEmpty;

public class EpisodeFragment extends BaseFragment<EpisodeMvp.View, EpisodePresenter> implements EpisodeMvp.View {

  @BindView(R.id.episode_name) TextView name;
  @BindView(R.id.episode_season_nr) TextView seasonNr;
  @BindView(R.id.episode_episode_nr) TextView episodeNr;
  @BindView(R.id.episode_airtime) TextView airtime;
  @BindView(R.id.episode_summary) TextView summary;
  @BindView(R.id.episode_runtime) TextView runtime;
  @BindView(R.id.episode_show_image) ImageView image;
  @BindView(R.id.episode_goto_show) Button gotoShowDetails;

  public static EpisodeFragment newInstance(@NonNull Episode episode) {
    EpisodeFragment view = new EpisodeFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(EXTRA_EPISODE, episode);
    view.setArguments(bundle);
    return view;
  }

  @Override
  protected int fragmentLayout() {
    return R.layout.fragment_episode;
  }

  @Override
  public void onDataToView(@NonNull Episode episode) {
    name.setText(episode.name());
    seasonNr.setText(String.valueOf(episode.season()));
    episodeNr.setText(String.valueOf(episode.number()));
    airtime.setText(episode.airtime());
    runtime.setText(String.valueOf(episode.runtime()) + " " + getString(R.string.unit_min));

    if (Build.VERSION.SDK_INT <= N) {
      summary.setText(Html.fromHtml(episode.summary()));
    } else {
      summary.setText(Html.fromHtml(episode.summary(), FROM_HTML_MODE_LEGACY));
    }

    final String imageUrl = episode.image()==null ? "" : nullToEmpty(episode.image().medium());

    Glide.with(getContext())
        .load(Uri.parse(imageUrl))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)
        .into(image);
  }

  @Override
  protected void onFragmentCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    if (getArguments() == null) {
      throw new NullPointerException("Bundle is null.");
    }

    getPresenter().onFragmentCreated(getArguments());
  }

  @OnClick(R.id.episode_goto_show)
  public void onGotoShowDetailsClicked() {
    getPresenter().onGotoShowDetailsClicked();
  }

  @NonNull
  @Override
  public EpisodePresenter providePresenter() {
    return new EpisodePresenter();
  }

  public void startShowActivity(@NonNull Show show) {
    ShowActivity.start(getContext(), show);
  }

}

