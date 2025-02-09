module test.ak.app {
  requires com.ak.app;
  requires org.jspecify;
  requires org.assertj.core;
  requires org.junit.jupiter.api;
  opens test.ak.app to org.junit.platform.commons;
  exports test.ak.app;
}