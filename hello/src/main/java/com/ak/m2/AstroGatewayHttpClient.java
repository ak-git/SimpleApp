package com.ak.m2;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;

public final class AstroGatewayHttpClient implements Gateway<AstroResponse> {
  private static final String DEFAULT_URL = "http://api.open-notify.org/";
  private final String url;

  public AstroGatewayHttpClient() {
    this(DEFAULT_URL);
  }

  public AstroGatewayHttpClient(String url) {
    this.url = url;
  }

  @Override
  public AstroResponse getResponse() {
    try (HttpClient client = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(url + "astros.json"))
          .timeout(Duration.ofSeconds(2))
          .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      return new Gson().fromJson(response.body(), AstroResponse.class);
    }
    catch (IOException | InterruptedException e) {
      Thread.currentThread().interrupt();
      return new AstroResponse(-1, "", Collections.emptyList());
    }
  }
}