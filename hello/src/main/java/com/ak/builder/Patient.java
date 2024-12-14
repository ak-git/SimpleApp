package com.ak.builder;

import java.util.function.Consumer;

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
    Builder<Patient> anthropomorphic(Consumer<Anthropomorphic.Step1> anthropomorphic);
  }

  record PatientRecord(int age, Anthropomorphic anthropomorphic) implements Patient {
    public static class PatientBuilder implements Step1, Step2, Builder<Patient> {
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
      public Builder<Patient> anthropomorphic(Consumer<Anthropomorphic.Step1> step1) {
        Anthropomorphic.AnthropomorphicRecord.AnthropomorphicBuilder builder = Anthropomorphic.builder();
        step1.accept(builder);
        anthropomorphic = builder.build();
        return this;
      }

      @Override
      public Patient build() {
        return new PatientRecord(age, anthropomorphic);
      }
    }
  }
}

