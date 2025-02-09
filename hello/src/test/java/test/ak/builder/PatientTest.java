package test.ak.builder;

import com.ak.builder.Patient;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PatientTest {
  @ParameterizedTest
  @CsvSource(value = "18 | 184 | 62 | 120 | 80 | 68 | 22", delimiter = '|')
  void build(int age, int height, int weight, int systolic, int diastolic, int heartRate, int respiratoryRate) {
    Patient patient = Patient.builder().age(age)
        .anthropomorphic(a -> a.height(height).weight(weight))
        .bloodPressure(b -> b.systolic(systolic).diastolic(diastolic))
        .rates(r -> r.heart(heartRate).respiratory(respiratoryRate))
        .build();
    assertAll(patient.toString(),
        () -> assertThat(patient.age()).isEqualTo(age),
        () -> assertThat(patient.anthropomorphic().height()).isEqualTo(height),
        () -> assertThat(patient.anthropomorphic().weight()).isEqualTo(weight),
        () -> assertThat(patient.bloodPressure().systolic()).isEqualTo(systolic),
        () -> assertThat(patient.bloodPressure().diastolic()).isEqualTo(diastolic),
        () -> assertThat(patient.rates().heart()).isEqualTo(heartRate),
        () -> assertThat(patient.rates().respiratory()).isEqualTo(respiratoryRate)
    );
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
       1 |  2 |  3 |  4 |  5 |  6 |    7
      -1 | -2 | -3 | -4 | -5 | -6 | 1000
      """)
  void buildOverhead(int age, int height, int weight, int systolic, int diastolic, int heartRate, int respiratoryRate) {
    Patient patient = Patient.builder().age(age)
        .anthropomorphic(a -> a.height(height).weight(weight))
        .bloodPressure(b -> b.systolic(systolic).diastolic(diastolic))
        .rates(r -> r.heart(heartRate).respiratory(respiratoryRate))
        .build();
    assertAll(patient.toString(),
        () -> assertThat(patient.age()).isBetween(12, 100),
        () -> assertThat(patient.anthropomorphic().height()).isBetween(140, 220),
        () -> assertThat(patient.anthropomorphic().weight()).isBetween(40, 130),
        () -> assertThat(patient.bloodPressure().systolic()).isBetween(50, 220),
        () -> assertThat(patient.bloodPressure().diastolic()).isBetween(10, 130),
        () -> assertThat(patient.rates().heart()).isBetween(40, 140),
        () -> assertThat(patient.rates().respiratory()).isBetween(4, 900)
    );
  }
}