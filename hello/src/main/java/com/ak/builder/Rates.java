package com.ak.builder;

public sealed interface Rates {
  int heart();

  int respiratory();

  static Step1 builder() {
    return new RatesBuilder();
  }

  sealed interface Step1 permits RatesBuilder {
    Step2 heart(int heart);
  }

  sealed interface Step2 permits RatesBuilder {
    Builder<Rates> respiratory(int respiratory);
  }

  final class RatesBuilder implements Step1, Step2, Builder<Rates> {
    private int heart;
    private int respiratory;

    private RatesBuilder() {
    }

    @Override
    public Step2 heart(int heart) {
      this.heart = heart;
      return this;
    }

    @Override
    public Builder<Rates> respiratory(int respiratory) {
      this.respiratory = respiratory;
      return this;
    }

    @Override
    public Rates build() {
      return new RatesRecord(heart, respiratory);
    }
  }

  record RatesRecord(int heart, int respiratory) implements Rates {
    public RatesRecord(int heart, int respiratory) {
      this.heart = Math.clamp(heart, 40, 140);
      int rr = Math.clamp(respiratory, 4, 900);
      if (rr > 99) {
        this.respiratory = Math.clamp(respiratory, 100, 900);
      }
      else {
        this.respiratory = Math.clamp(respiratory, 4, 28);
      }
    }
  }
}


