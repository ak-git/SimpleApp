package com.ak.m0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class HelloServiceTest {
  @Mock
  private TranslateService translateService;

  @InjectMocks
  private HelloService helloService;

  @Test
  void testGreet() {
    HelloService hello = new HelloService(new TranslateService());
    String greeting = hello.greet("Dolly", "en");
    Assertions.assertThat(greeting).isEqualTo("en translation: Hello, Dolly!");
  }

  @Test
  void testGreetWithFixedValues() {
    Mockito.when(translateService.translate("Hello, Dolly!", "en"))
        .thenReturn("en translation: Hello, Dolly!");
    String greeting = helloService.greet("Dolly", "en");
    Assertions.assertThat(greeting).isEqualTo("en translation: Hello, Dolly!");
  }

  @Test
  @DisplayName("Mock the TranslateService for any string, but works only for 'en'")
  void testGreetWithMock() {
    Mockito.when(translateService.translate(anyString(), anyString()))
        .thenReturn("en translation: Hello, Dolly!");
    String greeting = helloService.greet("Dolly", "en");
    Assertions.assertThat(greeting).isEqualTo("en translation: Hello, Dolly!");
  }

  @Test
  @DisplayName("Mock the TranslateService for 'en' only")
  void testGreetWithMockForEnglish() {
    Mockito.when(translateService.translate(anyString(), eq("en")))
        .thenReturn("en translation: Hello, Dolly!");
    String greeting = helloService.greet("Dolly", "en");
    Assertions.assertThat(greeting).isEqualTo("en translation: Hello, Dolly!");
  }

  @Test
  @DisplayName("Mock the TranslateService for any language, using answer")
  void testGreetWithMockForEnglishWithAnswer() {
    Mockito.when(translateService.translate(anyString(), anyString()))
        .thenAnswer(invocation -> {
          String argument = invocation.getArgument(1, String.class);
          return "%s translation: Hello, Dolly!".formatted(argument);
        });
    Assertions.assertThat(helloService.greet("Dolly", "en")).isEqualTo("en translation: Hello, Dolly!");
    Assertions.assertThat(helloService.greet("Dolly", "fr")).isEqualTo("fr translation: Hello, Dolly!");
  }
}