package com.ak.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainAppTest {
  @Test
  void testMain() {
    try {
      MainApp.main(new String[] {});
    }
    catch (Exception ex) {
      Assertions.fail(ex);
    }
  }
}