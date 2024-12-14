package com.ak.builder;

import java.util.function.Function;

public interface Patient {
  int age();

  Anthropomorphic anthropomorphic();

  BloodPressure bloodPressure();

  static Step1 builder() {
    return new PatientRecord.PatientBuilder();
  }

  interface Step1 {
    Step2 age(int age);
  }

  interface Step2 {
    Step3 anthropomorphic(Function<Anthropomorphic.Step1, Builder<Anthropomorphic>> builderFunction);
  }

  interface Step3 {
    Builder<Patient> bloodPressure(Function<BloodPressure.Step1, Builder<BloodPressure>> builderFunction);
  }

  class PatientBuilder implements Step1, Step2, Step3, Builder<Patient> {
    private int age;
    private Anthropomorphic anthropomorphic;
    private BloodPressure bloodPressure;

    private PatientBuilder() {
    }

    @Override
    public Step2 age(int age) {
      this.age = age;
      return this;
    }

    @Override
    public Step3 anthropomorphic(Function<Anthropomorphic.Step1, Builder<Anthropomorphic>> builderFunction) {
      anthropomorphic = builderFunction.apply(Anthropomorphic.builder()).build();
      return this;
    }

    @Override
    public Builder<Patient> bloodPressure(Function<BloodPressure.Step1, Builder<BloodPressure>> builderFunction) {
      bloodPressure = builderFunction.apply(BloodPressure.builder()).build();
      return this;
    }

    @Override
    public Patient build() {
      return new PatientRecord(age, anthropomorphic, bloodPressure);
    }
  }
}

record PatientRecord(int age, Anthropomorphic anthropomorphic, BloodPressure bloodPressure) implements Patient {
  PatientRecord(int age, Anthropomorphic anthropomorphic, BloodPressure bloodPressure) {
    this.age = Math.clamp(age, 12, 100);
    this.anthropomorphic = anthropomorphic;
    this.bloodPressure = bloodPressure;
  }
}


