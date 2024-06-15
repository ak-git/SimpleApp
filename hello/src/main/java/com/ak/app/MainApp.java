package com.ak.app;

import com.ak.base.BaseUtils;

import java.util.logging.Logger;

public class MainApp {
  private MainApp() {
  }

  public static void main(String[] args) {
    Logger.getLogger(MainApp.class.getName()).info(() -> BaseUtils.calc("Hello word 2024.06.12!"));
  }
}
