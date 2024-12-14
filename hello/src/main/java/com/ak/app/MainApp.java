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
        .bloodPressure(b ->
            b.systolic(120).diastolic(80)
        )
        .rates(r ->
            r.heart(68).respiratory(22)
        )
        .build();
    LOGGER.info(patient::toString);
  }
}
