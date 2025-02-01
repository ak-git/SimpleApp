package com.ak.app;

import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;

public class MainApp {
  private MainApp() {
  }

  public static void main(String[] args) {
    LoggerFactory.getLogger(MainApp.class).info("Hello World {}!", ZonedDateTime.now());
  }
}
