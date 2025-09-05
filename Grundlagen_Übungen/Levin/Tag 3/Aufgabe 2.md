# JUnit 5 - Feature Übersicht

## Core Annotations

### @Test
Markiert eine Testmethode.
```java
@Test
void shouldCalculateSum() {
    assertEquals(5, calculator.add(2, 3));
}
```

### @DisplayName
Aussagekräftige Testnamen.
```java
@Test
@DisplayName("Division durch Null wirft Exception")
void testDivisionByZero() {
    assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
}
```

### @BeforeEach / @AfterEach
Setup vor/nach jedem Test.
```java
@BeforeEach
void setUp() {
    calculator = new Calculator();
}
```

### @BeforeAll / @AfterAll
Einmalige Initialisierung für die Testklasse.
```java
@BeforeAll
static void init() {
    // Teure Setup-Operationen
}
```

## Assertions

```java
// Grundlegende Vergleiche
assertEquals(expected, actual);
assertTrue(condition);
assertNull(object);

// Mit Delta für Floating-Point
assertEquals(3.14, result, 0.01);

// Exception Testing
assertThrows(IllegalArgumentException.class, () -> method());

// Mehrere Assertions
assertAll("validation",
    () -> assertEquals("John", person.getName()),
    () -> assertTrue(person.getAge() > 0)
);
```

## Parametrisierte Tests

```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3})
void testPositiveNumbers(int number) {
    assertTrue(number > 0);
}

@ParameterizedTest
@CsvSource({"1,1,2", "2,3,5", "5,7,12"})
void testAddition(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
```

## Test-Organisation

### @Nested
Gruppierung verwandter Tests.
```java
class CalculatorTest {
    @Nested
    class AdditionTests {
        @Test
        void testPositiveNumbers() { /* ... */ }
    }
    
    @Nested
    class DivisionTests {
        @Test
        void testDivisionByZero() { /* ... */ }
    }
}
```

### @Tag
Kategorisierung für selektive Ausführung.
```java
@Test
@Tag("fast")
void quickTest() { /* ... */ }

@Test
@Tag("integration")
void slowTest() { /* ... */ }
```

## Conditional Tests

```java
@Test
@EnabledOnOs(OS.WINDOWS)
void testOnWindows() { /* ... */ }

@Test
@DisabledIf("java.version.startsWith('1.8')")
void testNotOnJava8() { /* ... */ }
```

## Maven Setup

```xml

    org.junit.jupiter
    junit-jupiter
    5.9.2
    test



    org.apache.maven.plugins
    maven-surefire-plugin
    3.0.0-M9

```

## Ausführung

```bash
# Alle Tests
mvn test

# Spezifische Tags
mvn test -Dgroups="fast"

# Einzelne Testklasse  
mvn test -Dtest=CalculatorTest
```

## Referenz

**[JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)** - Offizielle Dokumentation mit allen Features und Details