package com.ak.builder;

public interface Anthropomorphic {
  int height();

  int weight();

  static AnthropomorphicRecord.AnthropomorphicBuilder builder() {
    return new AnthropomorphicRecord.AnthropomorphicBuilder();
  }

  interface Step1 {
    Step2 height(int height);
  }

  interface Step2 {
    void weight(int weight);
  }

  record AnthropomorphicRecord(int height, int weight) implements Anthropomorphic {
    public static class AnthropomorphicBuilder implements Step1, Step2, Builder<Anthropomorphic> {
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
      public void weight(int weight) {
        this.weight = weight;
      }

      @Override
      public Anthropomorphic build() {
        return new AnthropomorphicRecord(height, weight);
      }
    }
  }
}


