package com.ak.m1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class HelloMockitoTest {
  @Mock
  private PersonRepository repository;

  @Mock
  private TranslationService translationService;

  @InjectMocks
  private HelloMockito helloMockito;

  @Test
  @DisplayName("Greet Admiral Hopper")
  void greetForPersonThatExists() {
    Mockito.when(repository.findById(anyInt()))
        .thenReturn(Optional.of(new Person(1, "Grace", "Hopper", LocalDate.now())));
    Mockito.when(translationService.translate(
            "Hello, Grace, from Mockito!", "en", "en"))
        .thenReturn("Hello, Grace, from Mockito!");

    String greeting = helloMockito.greet(1, "en", "en");
    Assertions.assertThat(greeting).isEqualTo("Hello, Grace, from Mockito!");

    InOrder inOrder = Mockito.inOrder(repository, translationService);
    inOrder.verify(repository).findById(anyInt());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }

  @Test
  @DisplayName("Greet a person not in the database")
  void greetForPersonThatDoesNotExist() {
    Mockito.when(repository.findById(anyInt())).thenReturn(Optional.empty());
    Mockito.when(translationService.translate("Hello, World, from Mockito!", "en", "en"))
        .thenReturn("Hello, World, from Mockito!");
    String greeting = helloMockito.greet(100, "en", "en");
    Assertions.assertThat(greeting).isEqualTo("Hello, World, from Mockito!");
    InOrder inOrder = Mockito.inOrder(repository, translationService);
    inOrder.verify(repository).findById(anyInt());
    inOrder.verify(translationService).translate(anyString(), eq("en"), eq("en"));
  }
}