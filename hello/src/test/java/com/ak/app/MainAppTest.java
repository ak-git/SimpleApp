package com.ak.app;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class MainAppTest {
  @Test
  void main() {
    assertThatNoException().isThrownBy(MainApp::main);
  }
}