package com.ak.m1;

import java.util.Optional;

public interface PersonRepository {
  Optional<Person> findById(int id);
}