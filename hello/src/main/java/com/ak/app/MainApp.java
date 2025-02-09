package com.ak.app;

import com.ak.builder.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {
  private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

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
    LOGGER.atInfo().setMessage(patient::toString).log();
  }
}
