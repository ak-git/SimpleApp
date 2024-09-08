package com.ak.app;

import java.util.logging.Logger;

public class MainApp {
  private MainApp() {
  }

  public static void main(String[] args) {
    Logger.getLogger(MainApp.class.getName()).info(() -> "Hello word 2024.6.7-27!");
  }
}
