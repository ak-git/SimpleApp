package com.ak.app;

import javax.annotation.Nonnull;
import java.util.logging.Logger;

public class MainApp {
  private MainApp() {
  }

  public static void main(@Nonnull String[] args) {
    Logger.getLogger(MainApp.class.getName()).info(() -> "Hello word!");
  }
}
