package com.ak.builder;

import java.util.function.IntUnaryOperator;

public sealed interface BloodPressure {
  int systolic();

  int diastolic();

  static Step1 builder() {
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

  record BloodPressureRecord(int systolic, int diastolic) implements BloodPressure {
    public BloodPressureRecord(int systolic, int diastolic) {
      IntUnaryOperator pressureLimits = bp -> Math.clamp(bp, 10, 220);
      int s = pressureLimits.applyAsInt(systolic);
      int d = pressureLimits.applyAsInt(diastolic);
      this.systolic = Math.clamp(Math.max(s, d), 50, 220);
      this.diastolic = Math.clamp(Math.min(s, d), 10, 130);
    }
  }
}


