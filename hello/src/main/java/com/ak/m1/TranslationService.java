package com.ak.m1;

public interface TranslationService {
  default String translate(String text, String sourceLanguage, String targetLanguage) {
    return text;
  }
}