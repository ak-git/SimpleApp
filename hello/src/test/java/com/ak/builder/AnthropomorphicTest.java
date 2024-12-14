package com.ak.builder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnthropomorphicTest {
  @ParameterizedTest
  @CsvSource(value = {"184; 62"}, delimiter = ';')
  void build(int height, int weight) {
    Anthropomorphic anthropomorphic = Anthropomorphic.builder().height(height).weight(weight).build();
    assertAll(anthropomorphic.toString(),
        () -> assertThat(anthropomorphic.height()).isEqualTo(height),
        () -> assertThat(anthropomorphic.weight()).isEqualTo(weight)
    );
  }

  @ParameterizedTest
  @CsvSource(value = {"230; 131", "-1; -2"}, delimiter = ';')
  void buildOverhead(int height, int weight) {
    Anthropomorphic anthropomorphic = Anthropomorphic.builder().height(height).weight(weight).build();
    assertAll(anthropomorphic.toString(),
        () -> assertThat(anthropomorphic.height()).isBetween(140, 220),
        () -> assertThat(anthropomorphic.weight()).isBetween(40, 130)
    );
  }
}