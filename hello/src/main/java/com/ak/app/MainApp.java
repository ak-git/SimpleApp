package com.ak.app;

import java.time.ZonedDateTime;
import java.util.logging.Logger;

public class MainApp {
  private MainApp() {
  }

  public static void main(String[] args) {
    Logger.getLogger(MainApp.class.getName()).info(() -> "Hello word! %s".formatted(ZonedDateTime.now()));
  }
}
