package com.ak.m1;

import java.time.LocalDate;

public record Person(int id, String firstName, String lastName, LocalDate dateOfBirth) {
}