package com.ak.m2;

import java.util.Collections;
import java.util.List;

public record AstroResponse(int number, String message, List<Assignment> people) {
  public AstroResponse(int number, String message, List<Assignment> people) {
    this.number = number;
    this.message = message;
    this.people = Collections.unmodifiableList(people);
  }
}
