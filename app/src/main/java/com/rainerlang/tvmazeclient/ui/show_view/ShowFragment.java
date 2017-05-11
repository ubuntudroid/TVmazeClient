package com.rainerlang.tvmazeclient.ui.show_view;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rainerlang.tvmazeclient.R;
import com.rainerlang.tvmazeclient.data.model.Show;
import com.rainerlang.tvmazeclient.ui.base.BaseFragment;

import butterknife.BindView;
import rx.Observable;

import static android.os.Build.VERSION_CODES.N;
import static android.text.Html.FROM_HTML_MODE_LEGACY;
import static com.rainerlang.tvmazeclient.Constants.EXTRA_SHOW;
import static com.rainerlang.tvmazeclient.helper.StringHelper.nullToEmpty;

public class ShowFragment extends BaseFragment<ShowMvp.View, ShowPresenter> implements ShowMvp.View {

  @BindView(R.id.show_name) TextView name;
  @BindView(R.id.show_type) TextView type;
  @BindView(R.id.show_genres) TextView genres;
  @BindView(R.id.show_summary) TextView summary;
  @BindView(R.id.show_image) ImageView image;

  public static ShowFragment newInstance(@NonNull Show show) {
    ShowFragment view = new ShowFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(EXTRA_SHOW, show);
    view.setArguments(bundle);
    return view;
  }

  @Override
  protected int fragmentLayout() {
    return R.layout.fragment_show;
  }

  @Override
  public void onDataToView(@NonNull Show show) {
    name.setText(show.name());
    type.setText(String.valueOf(show.type()));

    if (show.genres() != null) {
      genres.setText(
          Observable
              .from(show.genres())
              .defaultIfEmpty("")
              .reduce((s, s1) -> s + ", " + s1)
              .toBlocking()
              .firstOrDefault(""));
    }

    if (Build.VERSION.SDK_INT <= N) {
      summary.setText(Html.fromHtml(show.summary()));
    } else {
      summary.setText(Html.fromHtml(show.summary(), FROM_HTML_MODE_LEGACY));
    }

    final String imageUrl = show.image()==null ? "" : nullToEmpty(show.image().medium());

    Glide.with(getContext())
        .load(Uri.parse(imageUrl))
        .centerCrop()
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

  @NonNull
  @Override
  public ShowPresenter providePresenter() {
    return new ShowPresenter();
  }

}

