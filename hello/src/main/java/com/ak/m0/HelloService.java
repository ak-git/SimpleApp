package com.ak.m0;

public class HelloService {
  private final TranslateService translateService;

  public HelloService(TranslateService translateService) {
    this.translateService = translateService;
  }

  public String greet(String name, String language) {
    String greeting = "Hello, %s!".formatted(name);
    return translateService.translate(greeting, language);
  }
}