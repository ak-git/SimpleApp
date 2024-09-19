package com.ak.m0;

/**
 * Translate from English to whatever locale is specified
 */
public class TranslateService {
  public String translate(String text, String language) {
    return language + " translation: " + text;
  }
}