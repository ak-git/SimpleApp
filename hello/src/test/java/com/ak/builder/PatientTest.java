package com.ak.builder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PatientTest {
  @ParameterizedTest
  @CsvSource(value = {"18; 184; 62; 80; 120"}, delimiter = ';')
  void build(int age, int height, int weight, int systolic, int diastolic) {
    Patient patient = Patient.builder().age(age)
        .anthropomorphic(a -> a.height(height).weight(weight))
        .bloodPressure(b -> b.systolic(systolic).diastolic(diastolic))
        .build();
    assertAll(patient.toString(),
        () -> assertThat(patient.age()).isEqualTo(age),
        () -> assertThat(patient.anthropomorphic().height()).isEqualTo(height),
        () -> assertThat(patient.anthropomorphic().weight()).isEqualTo(weight),
        () -> assertThat(patient.bloodPressure().systolic()).isEqualTo(Math.max(systolic, diastolic)),
        () -> assertThat(patient.bloodPressure().diastolic()).isEqualTo(Math.min(systolic, diastolic))
    );
  }

  @ParameterizedTest
  @CsvSource(value = {"1; 2; 3; 4; 5", "-1; -2; -3; -4; -5"}, delimiter = ';')
  void buildOverhead(int age, int height, int weight, int systolic, int diastolic) {
    Patient patient = Patient.builder().age(age)
        .anthropomorphic(a -> a.height(height).weight(weight))
        .bloodPressure(b -> b.systolic(systolic).diastolic(diastolic))
        .build();
    assertAll(patient.toString(),
        () -> assertThat(patient.age()).isBetween(12, 100),
        () -> assertThat(patient.anthropomorphic().height()).isBetween(140, 220),
        () -> assertThat(patient.anthropomorphic().weight()).isBetween(40, 130),
        () -> assertThat(patient.bloodPressure().systolic()).isBetween(50, 220),
        () -> assertThat(patient.bloodPressure().diastolic()).isBetween(10, 130)
    );
  }
}