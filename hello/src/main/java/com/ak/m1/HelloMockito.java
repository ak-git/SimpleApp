package com.ak.m1;

import java.util.Optional;

public final class HelloMockito {
  private static final String GREETING = "Hello, %s, from Mockito!";
  private final PersonRepository personRepository;
  private final TranslationService translationService;

  public HelloMockito(PersonRepository personRepository, TranslationService translationService) {
    this.personRepository = personRepository;
    this.translationService = translationService;
  }

  public String greet(int id, String sourceLanguage, String targetLanguage) {
    Optional<Person> person = personRepository.findById(id);
    String name = person.map(Person::firstName).orElse("World");
    return translationService.translate(String.format(GREETING, name), sourceLanguage, targetLanguage);
  }
}