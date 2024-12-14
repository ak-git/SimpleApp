package com.ak.builder;

public interface BloodPressure {
  int systolic();

  int diastolic();

  static BloodPressureBuilder builder() {
    return new BloodPressureBuilder();
  }

  interface Step1 {
    Step2 systolic(int systolic);
  }

  interface Step2 {
    Builder<BloodPressure> diastolic(int diastolic);
  }

  class BloodPressureBuilder implements Step1, Step2, Builder<BloodPressure> {
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


