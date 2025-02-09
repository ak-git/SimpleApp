package test.ak.app;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class CollectionsAndCollectorsTest {
  record Employee(String firstName, String lastName, String position, int salary) {
  }

  private final List<Employee> employees = List.of(
      new Employee("AAA", "BBB", "developer", 10_000),
      new Employee("AAB", "BBC", "architect", 15_000),
      new Employee("AAC", "BBD", "developer", 13_000),
      new Employee("AAD", "BBE", "tester", 7_000),
      new Employee("AAE", "BBF", "tester", 9_000)
  );

  /**
   * <a href='https://piotrminkowski.com/2024/04/25/interesting-facts-about-java-streams-and-collections/'>
   * Interesting Facts About Java Streams and Collections
   * </a>
   */
  @Test
  void groupingAndAggregations() {
    var m = employees.stream().collect(
        Collectors.groupingBy(
            Employee::position,
            Collectors.summingInt(Employee::salary)
        )
    );

    assertThat(m).hasSize(3)
        .contains(entry("developer", 23000))
        .contains(entry("architect", 15000))
        .contains(entry("tester", 16000));

    var p = employees.stream().collect(Collectors.partitioningBy(e -> e.salary > 10_000));
    assertThat(p).hasSize(2)
        .hasEntrySatisfying(Boolean.TRUE, es -> assertThat(es).hasSize(2))
        .hasEntrySatisfying(Boolean.FALSE, es -> assertThat(es).hasSize(3));
  }

  /**
   * <p>
   * <a href="https://stackabuse.com/guide-to-java-8-collectors-groupingbyconcurrent/">Guide to Java 8 Collectors: groupingByConcurrent()</a>
   * </p>
   *
   * <p>
   * <a href="https://habr.com/ru/articles/684912//">Практические примеры использования Stream API</a>
   * </p>
   */
  @Test
  void groupingByConcurrent() {
    record Book(String title, String author, int releaseYear) {
    }

    String georgeOrwell = "George Orwell";
    String tolkien = "J.R.R. Tolkien";
    String williamGolding = "William Golding";
    List<Book> books = Arrays.asList(
        new Book("The Lord of the Rings", tolkien, 1954),
        new Book("The Hobbit", tolkien, 1937),
        new Book("Animal Farm", georgeOrwell, 1945),
        new Book("Nineteen Eighty-Four", georgeOrwell, 1949),
        new Book("The Road to Wigan Pier", georgeOrwell, 1937),
        new Book("Lord of the Flies", williamGolding, 1954)
    );

    ConcurrentMap<String, List<String>> booksByAuthor = books.parallelStream()
        .collect(
            Collectors.groupingByConcurrent(Book::author, ConcurrentHashMap::new, Collectors.mapping(Book::title, Collectors.toList()))
        );
    assertThat(booksByAuthor)
        .hasEntrySatisfying(georgeOrwell, strings -> assertThat(strings).hasSize(3))
        .hasEntrySatisfying(tolkien, strings -> assertThat(strings).hasSize(2))
        .hasEntrySatisfying(williamGolding, strings -> assertThat(strings).hasSize(1));
  }

  /**
   * <a href="https://stackoverflow.com/questions/19031213/java-get-most-common-element-in-a-list">Java-get most common element in a list</a>
   */
  @Test
  void mostCommonElement() {
    var v = Stream.of(1, 3, 4, 3, 2, 3, 3, 3, 3, 4)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();
    assertThat(v.getKey()).isEqualTo(3);
    assertThat(v.getValue()).isEqualTo(6);
  }

  @Test
  void maxElementKey() {
    var map = Map.of("1", 1, "2", 2, "3", 3);
    String maxKey = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    assertThat(maxKey).isEqualTo("3");
  }

  /**
   * <a href="https://blogs.oracle.com/javamagazine/post/the-hidden-gems-in-java-16-and-java-17-from-streammapmulti-to-hexformat">Hidden gems in Java 16 and Java 17, from Stream.mapMulti to HexFormat</a>
   */
  @Test
  void mapMulti() {
    List<String> strings = Stream.of("Java", "Valhalla", "Panama", "Loom", "Amber")
        .mapMulti((BiConsumer<String, Consumer<String>>) (s, mapper) -> {
          if (s.length() >= 5) {
            mapper.accept(s);
          }
        })
        .toList();
    assertThat(strings).containsExactly("Valhalla", "Panama", "Amber");
  }

  /**
   * <a href='https://piotrminkowski.com/2024/04/25/interesting-facts-about-java-streams-and-collections/'>
   * Interesting Facts About Java Streams and Collections
   * </a>
   */
  @Test
  void mapMerge() {
    var map = new HashMap<Integer, Integer>();
    var nums = List.of(2, 3, 4, 2, 3, 5, 1, 3, 4, 4);
    nums.forEach(num -> map.merge(num, 1, Integer::sum));
    assertThat(map).hasSize(5).contains(entry(4, 3));
  }

  @Test
  void mapMerge2() {
    var map = new HashMap<String, Integer>();
    employees.forEach(emp -> map.merge(emp.position(), emp.salary(), Integer::sum));
    assertThat(map).hasSize(3).contains(entry("developer", 23000));
  }
}