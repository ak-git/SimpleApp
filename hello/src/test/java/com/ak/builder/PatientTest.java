package com.ak.builder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PatientTest {
  @ParameterizedTest
  @CsvSource(value = {"1,2,3", "-1,-2,-3", "18,184,62"}, delimiter = ',')
  void build(int age, int height, int weight) {
    Patient patient = Patient.builder().age(age).anthropomorphic(a -> a.height(height).weight(weight)).build();
    assertAll(patient.toString(),
        () -> assertThat(patient.age()).isEqualTo(age),
        () -> assertThat(patient.anthropomorphic().height()).isEqualTo(height),
        () -> assertThat(patient.anthropomorphic().weight()).isEqualTo(weight)
    );
  }
}