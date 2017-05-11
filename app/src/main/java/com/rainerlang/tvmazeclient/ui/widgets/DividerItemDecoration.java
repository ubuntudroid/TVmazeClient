package com.rainerlang.tvmazeclient.ui.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rainerlang.tvmazeclient.R;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
  private Drawable mDivider;

  public DividerItemDecoration(Context ctx) {
    mDivider = ContextCompat.getDrawable(ctx, R.drawable.rv_divider_line);
  }

  @Override
  public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();

    for (int i = 0, childCount = parent.getChildCount(); i < childCount; i++) {
      View child = parent.getChildAt(i);

      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      int top = child.getBottom() + params.bottomMargin;
      int bottom = top + mDivider.getIntrinsicHeight();

      mDivider.setBounds(left, top, right, bottom);
      mDivider.draw(c);
    }
  }
}