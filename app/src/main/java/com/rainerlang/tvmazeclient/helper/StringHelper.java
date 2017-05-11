package com.rainerlang.tvmazeclient.helper;

import android.support.annotation.Nullable;

public class StringHelper {

  public static String nullToEmpty(@Nullable final String string) {
    return (string == null) ? "" : string;
  }

}
