package com.ak.app;

import com.ak.builder.Patient;

import java.util.logging.Logger;

public class MainApp {
  private static final Logger LOGGER = Logger.getLogger(MainApp.class.getName());

  private MainApp() {
  }

  public static void main(String[] args) {
    Patient patient = Patient.builder()
        .age(18)
        .anthropomorphic(a ->
            a.height(184).weight(62)
        )
        .build();
    LOGGER.info(patient::toString);
  }
}
