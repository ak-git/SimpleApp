package com.ak.builder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnthropomorphicTest {
  @ParameterizedTest
  @CsvSource(value = {"1,2", "-1,-2", "184,62"}, delimiter = ',')
  void build(int height, int weight) {
    Anthropomorphic.AnthropomorphicRecord.AnthropomorphicBuilder builder = Anthropomorphic.builder();
    builder.height(height).weight(weight);
    Anthropomorphic anthropomorphic = builder.build();
    assertAll(anthropomorphic.toString(),
        () -> assertThat(anthropomorphic.height()).isEqualTo(height),
        () -> assertThat(anthropomorphic.weight()).isEqualTo(weight)
    );
  }
}