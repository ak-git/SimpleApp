package com.ak.app;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MainAppTest {
  @Test
  void testMain() {
    Assertions.assertThatNoException().isThrownBy(MainApp::main);
  }
}