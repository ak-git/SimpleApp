package com.ak.builder;

import java.util.function.Function;

public interface Patient {
  int age();

  Anthropomorphic anthropomorphic();

  static Step1 builder() {
    return new PatientRecord.PatientBuilder();
  }

  interface Step1 {
    Step2 age(int age);
  }

  interface Step2 {
    Builder<Patient> anthropomorphic(Function<Anthropomorphic.Step1, Builder<Anthropomorphic>> builderFunction);
  }

  class PatientBuilder implements Step1, Step2, Builder<Patient> {
    private int age;
    private Anthropomorphic anthropomorphic;

    private PatientBuilder() {
    }

    @Override
    public Step2 age(int age) {
      this.age = age;
      return this;
    }

    @Override
    public Builder<Patient> anthropomorphic(Function<Anthropomorphic.Step1, Builder<Anthropomorphic>> builderFunction) {
      anthropomorphic = builderFunction.apply(Anthropomorphic.builder()).build();
      return this;
    }

    @Override
    public Patient build() {
      return new PatientRecord(age, anthropomorphic);
    }
  }
}

record PatientRecord(int age, Anthropomorphic anthropomorphic) implements Patient {
  PatientRecord(int age, Anthropomorphic anthropomorphic) {
    this.age = Math.clamp(age, 12, 100);
    this.anthropomorphic = anthropomorphic;
  }
}


