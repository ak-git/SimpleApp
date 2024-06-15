package com.ak.base;

import java.util.logging.Logger;

public class BaseUtils {
  private BaseUtils() {
  }

  public static String calc(String s) {
    Logger.getLogger(BaseUtils.class.getName()).info(() -> s);
    return s.toUpperCase();
  }
}
