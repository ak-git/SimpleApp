package com.ak.builder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BloodPressureTest {
  @ParameterizedTest
  @CsvSource(value = {"120; 80", "70; 110"}, delimiter = ';')
  void build(int systolic, int diastolic) {
    BloodPressure bloodPressure = BloodPressure.builder().systolic(systolic).diastolic(diastolic).build();
    assertAll(bloodPressure.toString(),
        () -> assertThat(bloodPressure.systolic()).isEqualTo(Math.max(systolic, diastolic)),
        () -> assertThat(bloodPressure.diastolic()).isEqualTo(Math.min(systolic, diastolic))
    );
  }

  @ParameterizedTest
  @CsvSource(value = {"230; 131", "-1; -2"}, delimiter = ';')
  void buildOverhead(int systolic, int diastolic) {
    BloodPressure bloodPressure = BloodPressure.builder().systolic(systolic).diastolic(diastolic).build();
    assertAll(bloodPressure.toString(),
        () -> assertThat(bloodPressure.systolic()).isBetween(50, 220),
        () -> assertThat(bloodPressure.diastolic()).isBetween(10, 130)
    );
  }
}