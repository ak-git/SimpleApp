package com.ak.builder;

import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

public sealed interface Patient {
  int age();

  Anthropomorphic anthropomorphic();

  BloodPressure bloodPressure();

  Rates rates();

  static Step1 builder() {
    return new PatientRecord.PatientBuilder();
  }

  sealed interface Step1 permits PatientBuilder {
    Step2 age(int age);
  }

  sealed interface Step2 permits PatientBuilder {
    Step3 anthropomorphic(Function<Anthropomorphic.Step1, Builder<Anthropomorphic>> builderFunction);
  }

  sealed interface Step3 permits PatientBuilder {
    Step4 bloodPressure(Function<BloodPressure.Step1, Builder<BloodPressure>> builderFunction);
  }

  sealed interface Step4 permits PatientBuilder {
    Builder<Patient> rates(Function<Rates.Step1, Builder<Rates>> builderFunction);
  }

  final class PatientBuilder implements Step1, Step2, Step3, Step4, Builder<Patient> {
    private int age;
    private @Nullable Anthropomorphic anthropomorphic;
    private @Nullable BloodPressure bloodPressure;
    private @Nullable Rates rates;

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
    public Step4 bloodPressure(Function<BloodPressure.Step1, Builder<BloodPressure>> builderFunction) {
      bloodPressure = builderFunction.apply(BloodPressure.builder()).build();
      return this;
    }

    @Override
    public Builder<Patient> rates(Function<Rates.Step1, Builder<Rates>> builderFunction) {
      rates = builderFunction.apply(Rates.builder()).build();
      return this;
    }

    @Override
    public Patient build() {
      return new PatientRecord(age,
          Objects.requireNonNull(anthropomorphic), Objects.requireNonNull(bloodPressure), Objects.requireNonNull(rates)
      );
    }
  }

  record PatientRecord(int age, Anthropomorphic anthropomorphic, BloodPressure bloodPressure,
                       Rates rates) implements Patient {
    public PatientRecord(int age, Anthropomorphic anthropomorphic, BloodPressure bloodPressure, Rates rates) {
      this.age = Math.clamp(age, 12, 100);
      this.anthropomorphic = anthropomorphic;
      this.bloodPressure = bloodPressure;
      this.rates = rates;
    }
  }
}

