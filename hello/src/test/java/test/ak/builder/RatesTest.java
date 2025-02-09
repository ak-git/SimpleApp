package test.ak.builder;

import com.ak.builder.Rates;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RatesTest {
  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      68 |  22
      68 | 800
      """)
  void build(int heart, int respiratory) {
    Rates rates = Rates.builder().heart(heart).respiratory(respiratory).build();
    assertAll(rates.toString(),
        () -> assertThat(rates.heart()).isEqualTo(heart),
        () -> assertThat(rates.respiratory()).isEqualTo(respiratory)
    );
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      150 | 30
      -40 | 99
        0 | -1
      """)
  void buildOverheadRespiratoryRate(int heart, int respiratory) {
    Rates rates = Rates.builder().heart(heart).respiratory(respiratory).build();
    assertAll(rates.toString(),
        () -> assertThat(rates.heart()).isBetween(40, 140),
        () -> assertThat(rates.respiratory()).isBetween(4, 28)
    );
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      150 | 901
        0 | 100
      """)
  void buildOverheadPeakExpiratoryFlowRate(int heart, int respiratory) {
    Rates rates = Rates.builder().heart(heart).respiratory(respiratory).build();
    assertAll(rates.toString(),
        () -> assertThat(rates.heart()).isBetween(40, 140),
        () -> assertThat(rates.respiratory()).isBetween(100, 900)
    );
  }
}