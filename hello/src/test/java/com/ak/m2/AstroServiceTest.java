package com.ak.m2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
class AstroServiceTest {
  @Mock
  private Gateway<AstroResponse> gateway;

  @InjectMocks
  private AstroService service;

  @Test
  void testAstroData_usingRealGateway_withHttpClient() {
    service = new AstroService(new AstroGatewayHttpClient());

    Map<String, Long> astroData = service.getAstroData();

    astroData.forEach((craft, number) -> {
      System.out.println(number + " astronauts aboard " + craft);
      assertAll(
          () -> assertThat(number).isPositive(),
          () -> assertThat(craft).isNotBlank()
      );
    });
  }
}