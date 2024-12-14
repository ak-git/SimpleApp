package com.ak.builder;

public sealed interface BloodPressure permits BloodPressureRecord {
  int systolic();

  int diastolic();

  static BloodPressureBuilder builder() {
    return new BloodPressureBuilder();
  }

  sealed interface Step1 permits BloodPressureBuilder {
    Step2 systolic(int systolic);
  }

  sealed interface Step2 permits BloodPressureBuilder {
    Builder<BloodPressure> diastolic(int diastolic);
  }

  final class BloodPressureBuilder implements Step1, Step2, Builder<BloodPressure> {
    private int systolic;
    private int diastolic;

    private BloodPressureBuilder() {
    }

    @Override
    public Step2 systolic(int systolic) {
      this.systolic = systolic;
      return this;
    }

    @Override
    public Builder<BloodPressure> diastolic(int diastolic) {
      this.diastolic = diastolic;
      return this;
    }

    @Override
    public BloodPressure build() {
      return new BloodPressureRecord(systolic, diastolic);
    }
  }
}

record BloodPressureRecord(int systolic, int diastolic) implements BloodPressure {
  BloodPressureRecord(int systolic, int diastolic) {
    int s = Math.clamp(systolic, 50, 220);
    int d = Math.clamp(diastolic, 10, 130);
    this.systolic = Math.max(s, d);
    this.diastolic = Math.min(s, d);
  }
}


