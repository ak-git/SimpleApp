module test.ak.app {
  requires com.ak.app;
  requires org.jspecify;
  requires org.assertj.core;
  requires org.junit.jupiter.params;
  opens test.ak.app to org.junit.platform.commons;
  opens test.ak.builder to org.junit.platform.commons;
  exports test.ak.app;
}