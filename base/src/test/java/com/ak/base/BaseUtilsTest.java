package com.ak.base;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseUtilsTest {
  @Test
  void testCalc() {
    Assertions.assertThat(BaseUtils.calc("aBc")).isEqualTo("ABC");
  }
}