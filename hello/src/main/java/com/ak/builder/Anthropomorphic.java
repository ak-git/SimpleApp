package com.ak.builder;

public sealed interface Anthropomorphic permits AnthropomorphicRecord {
  int height();

  int weight();

  static AnthropomorphicBuilder builder() {
    return new AnthropomorphicRecord.AnthropomorphicBuilder();
  }

  sealed interface Step1 permits AnthropomorphicBuilder {
    Step2 height(int height);
  }

  sealed interface Step2 permits AnthropomorphicBuilder {
    Builder<Anthropomorphic> weight(int weight);
  }

  final class AnthropomorphicBuilder implements Step1, Step2, Builder<Anthropomorphic> {
    private int height;
    private int weight;

    private AnthropomorphicBuilder() {
    }

    @Override
    public Step2 height(int height) {
      this.height = height;
      return this;
    }

    @Override
    public Builder<Anthropomorphic> weight(int weight) {
      this.weight = weight;
      return this;
    }

    @Override
    public Anthropomorphic build() {
      return new AnthropomorphicRecord(height, weight);
    }
  }
}

record AnthropomorphicRecord(int height, int weight) implements Anthropomorphic {
  AnthropomorphicRecord(int height, int weight) {
    this.height = Math.clamp(height, 140, 220);
    this.weight = Math.clamp(weight, 40, 130);
  }
}


