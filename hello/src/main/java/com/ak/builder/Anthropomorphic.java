package com.ak.builder;

public sealed interface Anthropomorphic {
  int height();

  int weight();

  static Step1 builder() {
    return new AnthropomorphicBuilder();
  }

  sealed interface Step1 permits AnthropomorphicBuilder {
    Step2 height(int height);
  }

  sealed interface Step2 permits AnthropomorphicBuilder {
    Builder<Anthropomorphic> weight(int weight);
  }

  final class AnthropomorphicBuilder implements Anthropomorphic.Step1, Anthropomorphic.Step2, Builder<Anthropomorphic> {
    private int height;
    private int weight;

    private AnthropomorphicBuilder() {
    }

    @Override
    public Anthropomorphic.Step2 height(int height) {
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
      return new Anthropomorphic.AnthropomorphicRecord(height, weight);
    }
  }

  record AnthropomorphicRecord(int height, int weight) implements Anthropomorphic {
    public AnthropomorphicRecord(int height, int weight) {
      this.height = Math.clamp(height, 140, 220);
      this.weight = Math.clamp(weight, 40, 130);
    }
  }
}


