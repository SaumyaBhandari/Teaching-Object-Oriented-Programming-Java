# 1. Introduction to Lambdas (Java 8+)
Prior to Java 8, implementing functional interfaces (interfaces with a single
abstract method) often involved creating anonymous inner classes, which could
be verbose and cumbersome. **Lambda expressions**, introduced in Java 8, provide
a concise way to represent an instance of a functional interface. They are
essentially anonymous functions that can be passed around as arguments or
assigned to variables.

The primary goal of lambdas was to enable functional programming paradigms in Java,
allowing developers to treat functions as first-class citizens. This makes code
more readable, concise, and easier to maintain, especially when dealing with
collections and event handling.

# 2. Functional Interfaces
Before we dive into lambda syntax, it's crucial to understand **Functional Interfaces**.

A functional interface is an interface that contains exactly one abstract method.

* It can have any number of default methods.
* It can have any number of static methods.
* It can have any number of methods inherited from `Object` (like `equals`,
    `hashCode`, `toString`).

The `@FunctionalInterface` annotation is optional but highly recommended. It tells
the compiler to enforce the "single abstract method" rule. If you try to add a
second abstract method, the compiler will throw an error. This annotation helps
prevent accidental violations of the functional interface contract.

## Examples of Functional Interfaces:

```Java

// Custom Functional Interface
@FunctionalInterface
interface MyFunctionalInterface {
    void doSomething(); // Single abstract method

    default void doSomethingElse() {
        System.out.println("Doing something else from default method.");
    }

    static void someStaticMethod() {
        System.out.println("A static method in a functional interface.");
    }
}

// Another Custom Functional Interface with parameters and return type
@FunctionalInterface
interface Calculator {
    int operate(int a, int b);
}
```

## Java's Built-in Functional Interfaces (`java.util.function` package)

* **`Consumer<T>`**: Takes one argument, returns no result.
    * `void accept(T t);`
    * Example: `Consumer<String> printConsumer = s -> System.out.println(s);`

* **`Predicate<T>`**: Takes one argument, returns a `boolean` result.
    * `boolean test(T t);`
    * Example: `Predicate<Integer> isEven = n -> n % 2 == 0;`

* **`Function<T, R>`**: Takes one argument (type `T`), produces one result (type `R`).
    * `R apply(T t);`
    * Example: `Function<String, Integer> stringLength = s -> s.length();`

* **`Supplier<T>`**: Takes no arguments, produces one result (type `T`).
    * `T get();`
    * Example: `Supplier<Double> randomValue = () -> Math.random();`

* **`Runnable`**: Takes no arguments, returns no result.
    * `void run();` (Used for threads, pre-Java 8)
    * Example: `Runnable task = () -> System.out.println("Running task.");`


# 3. Lambda Expression Syntax
A lambda expression consists of three main parts:

1.  **Parameters**: A comma-separated list of formal parameters enclosed in parentheses.
2.  **Arrow Token**: The `->` (arrow token).
3.  **Body**: A single expression or a block of statements.

## General Syntax:
```java
(parameters) -> expression
```
**OR**
```java
(parameters) -> { statements; }
```

### Variations in Syntax (with Examples):

* **1. No parameters:**
    ```java
    () -> System.out.println("Hello, Lambda!")
    // (Equivalent to MyFunctionalInterface's doSomething() method if it has no parameters)
    ```

* **1.1. One parameter (parentheses optional):**
    ```java
    name -> System.out.println("Hello, " + name)
    // (Equivalent to Consumer<String>'s accept(String s) method)
    ```

* **2. Multiple parameters:**
    ```java
    (a, b) -> a + b
    // (Equivalent to Calculator's operate(int a, int b) method)
    ```

* **2.1. Explicit parameter types:** (Generally optional due to type inference)
    ```java
    (int a, int b) -> a * b
    ```

* **Expression body (single expression):**
    * The result of the expression is returned automatically.
    * No `return` keyword needed.
    ```java
    (x, y) -> x + y // Returns the sum of x and y
    ```

* **Block body (multiple statements):**
    * Enclosed in curly braces `{}`.
    * Explicit `return` statement is required if the abstract method expects a return value.
    ```java
    (a, b) -> {
        int sum = a + b;
        System.out.println("Calculating sum...");
        return sum;
    }
    ```


# 4. How Lambdas Work: Type Inference
Java's compiler uses **type inference** to determine the type of the lambda
expression. It infers the parameter types and the return type from the context
in which the lambda is used, specifically from the abstract method signature of
the functional interface it's assigned to or passed as an argument.

## Example:

```java
// The Calculator interface: int operate(int a, int b);
Calculator adder = (a, b) -> a + b;
// Compiler knows 'a' and 'b' are ints because operate expects two ints.
// Compiler knows 'a + b' returns an int because operate expects an int return.
```

# 5. Lambda Expressions vs. Anonymous Inner Classes
---
Lambdas are often seen as a more concise replacement for anonymous inner classes,
especially for functional interfaces. Here's a comparison:

| Feature                   | Anonymous Inner Class                         | Lambda Expression                                      |
| :------------------------ | :-------------------------------------------- | :----------------------------------------------------- |
| Syntax                    | Verbose, requires boilerplate (`new Interface() { ... }`) | Concise, `(params) -> body`                            |
| `this` Keyword            | Refers to the anonymous inner class instance  | Refers to the enclosing class instance                 |
| Shadowing                 | Can shadow variables from the enclosing scope | Cannot shadow variables from the enclosing scope       |
| State                     | Can have its own state (fields, constructors) | Stateless (captures `final`/effectively final vars)    |
| Implementation            | Creates a new `.class` file at runtime (more overhead) | Optimized for performance; often translated directly into bytecode instructions |
| Functional Interface      | Can implement any interface (not just functional) | Can only implement functional interfaces               |

## Example of Anonymous Inner Class vs. Lambda:

```java
// Anonymous Inner Class (Pre-Java 8 style for Runnable)
Runnable oldWay = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running with anonymous inner class.");
    }
};
new Thread(oldWay).start();

// Lambda Expression (Java 8+ style for Runnable)
Runnable newWay = () -> System.out.println("Running with lambda expression.");
new Thread(newWay).start();
```

# 6. Variable Capture (Closures)
Lambdas can access variables from their enclosing scope. However, there's a rule:

Local variables from the enclosing scope accessed by a lambda must be
**effectively final**. This means they are either explicitly declared `final` or
their value is never changed after initialization.
Instance variables and static variables can be accessed freely without any
`final` restriction.

## Why "effectively final"?
This rule ensures that the lambda operates on a consistent value. When a lambda
is executed, the local variable might have gone out of scope. By ensuring it's
effectively final, Java can safely capture its value.

## Example of Variable Capture:

```java
public class LambdaCaptureDemo {
    private int instanceVar = 10; // Instance variable
    private static int staticVar = 20; // Static variable

    public void demoCapture() {
        int localVar = 30; // Effectively final
        // localVar = 31; // Uncommenting this line would make localVar NOT effectively final,
                           // leading to a compile-time error if used in a lambda.
        final int finalVar = 40; // Explicitly final

        MyFunctionalInterface lambda1 = () -> {
            System.out.println("Accessing instanceVar: " + instanceVar);
            System.out.println("Accessing staticVar: " + staticVar);
            System.out.println("Accessing localVar: " + localVar); // OK, localVar is effectively final
            System.out.println("Accessing finalVar: " + finalVar); // OK, finalVar is final
        };

        lambda1.doSomething();

        // Example of a compile-time error if 'mutableVar' were changed:
        // int mutableVar = 50;
        // MyFunctionalInterface lambda2 = () -> System.out.println(mutableVar);
        // mutableVar = 51; // Compile-time error here because mutableVar is used in lambda2.
    }

    public static void main(String[] args) {
        new LambdaCaptureDemo().demoCapture();
    }
}
```

# 7. Method References

Method references are a special type of lambda expression that are even more
concise for specific scenarios. They are used when a lambda expression just calls
an existing method. They make code even more readable by directly referring to
the method by its name.

Syntax: `ClassName::methodName` or `objectName::methodName`

## Types of Method References:

* **Reference to a static method:**
    * Lambda: `(arg) -> Class.staticMethod(arg)`
    * Method Reference: `Class::staticMethod`
    * Example: `Consumer<String> printer = System.out::println;`
        (Equivalent to `s -> System.out.println(s)`)

* **Reference to an instance method of a particular object:**
    * Lambda: `(arg) -> obj.instanceMethod(arg)`
    * Method Reference: `obj::instanceMethod`
    * Example:
        ```java
        StringBuilder sb = new StringBuilder();
        Consumer<String> appender = sb::append; // Equivalent to s -> sb.append(s)
        appender.accept("Hello");
        appender.accept(" World");
        System.out.println(sb.toString()); // Output: Hello World
        ```

* **Reference to an instance method of an arbitrary object of a particular type:**
    * Lambda: `(Type obj, args) -> obj.instanceMethod(args)`
    * Method Reference: `Type::instanceMethod`
    * Example:
        ```java
        Function<String, Integer> stringToLength = String::length; // Equivalent to s -> s.length()
        System.out.println(stringToLength.apply("Java")); // Output: 4
        ```

* **Reference to a constructor:**
    * Lambda: `(args) -> new ClassName(args)`
    * Method Reference: `ClassName::new`
    * Example:
        ```java
        Supplier<ArrayList> listSupplier = ArrayList::new; // Equivalent to () -> new ArrayList()
        ArrayList<String> myList = listSupplier.get();
        myList.add("Item 1");

        Function<String, Thread> threadCreator = Thread::new; // Equivalent to s -> new Thread(s)
        Thread t = threadCreator.apply("MyThread");
        t.start();
        ```

## Method Reference Examples in Context:

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MethodReferenceDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // 1. Reference to a static method (System.out::println)
        System.out.println("--- Static Method Reference ---");
        names.forEach(System.out::println);

        // 2. Reference to an instance method of a particular object (myPrinter::print)
        System.out.println("\n--- Instance Method Reference (Specific Object) ---");
        MyPrinter myPrinter = new MyPrinter();
        names.forEach(myPrinter::print);

        // 3. Reference to an instance method of an arbitrary object of a particular type (String::length)
        System.out.println("\n--- Instance Method Reference (Arbitrary Object of Type) ---");
        List<Integer> lengths = names.stream()
                                     .map(String::length) // Equivalent to s -> s.length()
                                     .collect(Collectors.toList());
        System.out.println("Lengths: " + lengths);

        // 4. Reference to a constructor (StringBuilder::new)
        System.out.println("\n--- Constructor Reference ---");
        // Supplier for a new StringBuilder
        java.util.function.Supplier<StringBuilder> sbSupplier = StringBuilder::new;
        StringBuilder newSb = sbSupplier.get();
        newSb.append("Constructed with method reference.");
        System.out.println(newSb.toString());

        // Example with Predicate
        Predicate<Integer> isPositive = MethodReferenceDemo::checkPositive;
        System.out.println("Is 5 positive? " + isPositive.test(5));
        System.out.println("Is -3 positive? " + isPositive.test(-3));
    }

    public static boolean checkPositive(Integer number) {
        return number > 0;
    }
}

class MyPrinter {
    public void print(String s) {
        System.out.println("MyPrinter says: " + s.toUpperCase());
    }
}
```

# 8. Common Built-in Functional Interfaces (`java.util.function`)

Java 8 introduced a new package `java.util.function` that contains a rich set of
predefined functional interfaces. These cover most common use cases and help
standardize code.

| Interface Name    | Abstract Method Signature | Description                                                              | Example Usage                                                                  |
| :---------------- | :------------------------ | :----------------------------------------------------------------------- | :----------------------------------------------------------------------------- |
| `Consumer<T>`     | `void accept(T t)`        | Represents an operation that accepts a single input argument and returns no result. | `Consumer<String> printer = s -> System.out.println(s);`                             |
| `Predicate<T>`    | `boolean test(T t)`       | Represents a predicate (boolean-valued function) of one argument.        | `Predicate<Integer> isEven = n -> n % 2 == 0;`                                 |
| `Function<T, R>`  | `R apply(T t)`            | Represents a function that accepts one argument and produces a result.   | `Function<String, Integer> toLength = s -> s.length();`                          |
| `Supplier<T>`     | `T get()`                 | Represents a supplier of results. Takes no arguments.                    | `Supplier<Double> randomValue = () -> Math.random();`                          |
| `Runnable`        | `void run()`              | Represents a runnable task. (Already existed, now marked `@FunctionalInterface`) | `Runnable task = () -> System.out.println("Task running.");`                  |
| `Comparator<T>`   | `int compare(T o1, T o2)` | Represents a comparison function, which imposes a total ordering on some collection of objects. (Already existed, now marked `@FunctionalInterface`) | `Comparator<String> lenComparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());` |
| `BiConsumer<T, U>`| `void accept(T t, U u)`   | Accepts two input arguments and returns no result.                       | `BiConsumer<String, Integer> printInfo = (name, age) -> System.out.println(name + " is " + age + " years old.");` |
| `BiPredicate<T, U>`| `boolean test(T t, U u)`  | Represents a predicate of two arguments.                                 | `BiPredicate<String, String> areEqual = (s1, s2) -> s1.equals(s2);`          |
| `BiFunction<T, U, R>`| `R apply(T t, U u)`     | Accepts two arguments and produces a result.                             | `BiFunction<Integer, Integer, Integer> adder = (a, b) -> a + b;`               |
| `UnaryOperator<T>`| `T apply(T t)`            | Represents an operation on a single operand that produces a result of the same type as its operand. (Extends `Function<T, T>`) | `UnaryOperator<Integer> negate = n -> -n;`                                   |
| `BinaryOperator<T>`| `T apply(T t1, T t2)`     | Represents an operation upon two operands of the same type, producing a result of the same type as the operands. (Extends `BiFunction<T, T, T>`) | `BinaryOperator<Integer> multiplier = (a, b) -> a * b;`                        |

There are also primitive specializations (`IntConsumer`, `DoublePredicate`, `LongFunction`, etc.)
to avoid autoboxing/unboxing overhead.

# 9. Lambdas with Collections (Stream API)

Lambdas truly shine when used with Java 8's **Stream API**. The Stream API provides
a powerful and declarative way to process collections of data. Operations on
streams often accept lambda expressions or method references as arguments.

## Common Stream Operations Using Lambdas:

* `forEach()`: Iterates over elements. (`Consumer`)
* `filter()`: Selects elements based on a condition. (`Predicate`)
* `map()`: Transforms elements. (`Function`)
* `reduce()`: Combines elements into a single result. (`BinaryOperator`)
* `sorted()`: Sorts elements. (`Comparator`)
* `collect()`: Gathers elements into a collection.
* `anyMatch()`, `allMatch()`, `noneMatch()`: Check if elements match a condition. (`Predicate`)

## A very very practical Example with Streams:

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaStreamExamples {

    static class Person {
        String name;
        int age;
        String city;

        public Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getCity() { return city; }

        @Override
        public String toString() {
            return "Person{" + "name='" + name + '\'' + ", age=" + age + ", city='" + city + '\'' + '}';
        }
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve", "Frank");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "New York"),
            new Person("Bob", 25, "London"),
            new Person("Charlie", 35, "New York"),
            new Person("David", 22, "Paris"),
            new Person("Eve", 40, "London"),
            new Person("Frank", 28, "New York")
        );

        System.out.println("--- 1. Basic ForEach ---");
        names.forEach(name -> System.out.println("Hello, " + name));
        // Using method reference
        names.forEach(System.out::println);

        System.out.println("\n--- 2. Filter (Predicate) ---");
        List<Integer> evenNumbers = numbers.stream()
                                           .filter(n -> n % 2 == 0) // Lambda as Predicate
                                           .collect(Collectors.toList());
        System.out.println("Even Numbers: " + evenNumbers);

        List<Person> adults = people.stream()
                                    .filter(p -> p.getAge() >= 18) // Lambda as Predicate
                                    .collect(Collectors.toList());
        System.out.println("Adults: " + adults);

        System.out.println("\n--- 3. Map (Function) ---");
        List<String> upperCaseNames = names.stream()
                                           .map(String::toUpperCase) // Method Reference as Function
                                           .collect(Collectors.toList());
        System.out.println("Uppercase Names: " + upperCaseNames);

        List<Integer> nameLengths = names.stream()
                                         .map(s -> s.length()) // Lambda as Function
                                         .collect(Collectors.toList());
        System.out.println("Name Lengths: " + nameLengths);

        List<String> personNames = people.stream()
                                         .map(Person::getName) // Method Reference as Function
                                         .collect(Collectors.toList());
        System.out.println("Person Names: " + personNames);

        System.out.println("\n--- 4. Sorted (Comparator) ---");
        List<String> sortedNames = names.stream()
                                        .sorted((s1, s2) -> s1.compareTo(s2)) // Lambda as Comparator
                                        .collect(Collectors.toList());
        System.out.println("Sorted Names (alphabetical): " + sortedNames);

        List<String> sortedNamesByLength = names.stream()
                                                .sorted(Comparator.comparingInt(String::length)) // Method reference with Comparator.comparingInt
                                                .collect(Collectors.toList());
        System.out.println("Sorted Names (by length): " + sortedNamesByLength);

        List<Person> sortedPeopleByAge = people.stream()
                                              .sorted(Comparator.comparingInt(Person::getAge)) // Method reference
                                              .collect(Collectors.toList());
        System.out.println("People sorted by age: " + sortedPeopleByAge);

        System.out.println("\n--- 5. Reduce (BinaryOperator) ---");
        Optional<Integer> sum = numbers.stream()
                                       .reduce((a, b) -> a + b); // Lambda as BinaryOperator
        sum.ifPresent(s -> System.out.println("Sum of numbers: " + s));

        String combinedNames = names.stream()
                                    .reduce("", (acc, name) -> acc + name.toUpperCase() + " "); // Lambda as BinaryOperator
        System.out.println("Combined Names: " + combinedNames);

        System.out.println("\n--- 6. Count, Min, Max, Average ---");
        long countEven = numbers.stream().filter(n -> n % 2 == 0).count();
        System.out.println("Count of even numbers: " + countEven);

        Optional<Integer> maxNum = numbers.stream().max(Integer::compare); // Method Reference as Comparator
        maxNum.ifPresent(n -> System.out.println("Max number: " + n));

        Optional<Person> oldestPerson = people.stream().max(Comparator.comparingInt(Person::getAge));
        oldestPerson.ifPresent(p -> System.out.println("Oldest person: " + p.getName() + " (" + p.getAge() + ")"));

        double averageAge = people.stream()
                                  .mapToInt(Person::getAge) // Using specialized primitive stream for efficiency
                                  .average()
                                  .orElse(0.0);
        System.out.println("Average age of people: " + averageAge);

        System.out.println("\n--- 7. Distinct ---");
        List<Integer> duplicateNumbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> uniqueNumbers = duplicateNumbers.stream()
                                                     .distinct()
                                                     .collect(Collectors.toList());
        System.out.println("Unique numbers: " + uniqueNumbers);

        System.out.println("\n--- 8. AnyMatch, AllMatch, NoneMatch (Predicate) ---");
        boolean anyAdult = people.stream().anyMatch(p -> p.getAge() >= 18);
        System.out.println("Are there any adults? " + anyAdult);

        boolean allAdults = people.stream().allMatch(p -> p.getAge() >= 18);
        System.out.println("Are all people adults? " + allAdults);

        boolean noKids = people.stream().noneMatch(p -> p.getAge() < 12);
        System.out.println("Are there no kids? " + noKids);

        System.out.println("\n--- 9. Grouping (Collectors.groupingBy) ---");
        Map<String, List<Person>> peopleByCity = people.stream()
                                                      .collect(Collectors.groupingBy(Person::getCity));
        System.out.println("People by city: " + peopleByCity);

        Map<String, Long> cityCounts = people.stream()
                                            .collect(Collectors.groupingBy(Person::getCity, Collectors.counting()));
        System.out.println("City counts: " + cityCounts);

        Map<String, List<String>> namesByCity = people.stream()
                                                     .collect(Collectors.groupingBy(Person::getCity,
                                                               Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println("Names by city: " + namesByCity);

        System.out.println("\n--- 10. Partitioning (Collectors.partitioningBy) ---");
        Map<Boolean, List<Person>> partitionedByAge = people.stream()
                                                          .collect(Collectors.partitioningBy(p -> p.getAge() >= 30));
        System.out.println("Partitioned by age (>=30): " + partitionedByAge);

        System.out.println("\n--- 11. Custom Lambda (Calculator) ---");
        // Define a simple functional interface for demonstration
        interface Calculator {
            int operate(int a, int b);
        }

        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        Calculator subtract = (a, b) -> a - b;

        System.out.println("10 + 5 = " + add.operate(10, 5));
        System.out.println("10 * 5 = " + multiply.operate(10, 5));
        System.out.println("10 - 5 = " + subtract.operate(10, 5));

        System.out.println("\n--- 12. Using Lambdas for Callbacks/Event Handling ---");
        // Imagine a simple event listener setup (simplified)
        interface ClickListener {
            void onClick(String buttonId);
        }

        class Button {
            private String id;
            private ClickListener listener;

            public Button(String id) { this.id = id; }
            public void setClickListener(ClickListener listener) { this.listener = listener; }
            public void simulateClick() {
                if (listener != null) {
                    listener.onClick(id);
                }
            }
        }

        Button submitButton = new Button("Submit");
        submitButton.setClickListener(buttonId -> { // Lambda as event handler
            System.out.println("Button '" + buttonId + "' was clicked! Data submitted.");
        });
        submitButton.simulateClick();

        Button cancelButton = new Button("Cancel");
        cancelButton.setClickListener(buttonId -> System.out.println("User cancelled operation from button '" + buttonId + "'."));
        cancelButton.simulateClick();
    }
}
```

# 10. Why Use Lambdas? Benefits
* **Conciseness**: Reduce boilerplate code, especially with anonymous inner classes.
* **Readability**: Make code cleaner and easier to understand, especially for simple operations.
* **Functional Programming**: Enable a more functional style of programming in Java, treating functions as first-class citizens.
* **Parallel Processing**: Facilitate parallel operations on collections via the Stream API's `parallelStream()` method, making it easier to leverage multi-core processors.
* **Improved APIs**: Many existing Java APIs (like `Collections.sort`, `ExecutorService`) can now be used more elegantly with lambdas. New APIs (like Stream API) are built around lambdas.

# 11. Best Practices and Considerations
* **Keep Lambdas Small**: While powerful, large lambda bodies can be hard to read and debug. If a lambda's logic becomes complex, consider extracting it into a private helper method and using a method reference.
* **Clarity over Brevity**: Don't sacrifice readability for extreme conciseness. Sometimes, a slightly more verbose lambda or even an anonymous inner class might be clearer if the context is complex.
* **Avoid Side Effects**: In functional programming, functions should ideally be "pure" (produce the same output for the same input and have no side effects). While Java doesn't strictly enforce this, striving for side-effect-free lambdas (especially in stream operations) leads to more predictable and testable code.
* **Debugging**: Debugging lambdas can sometimes be tricky. Stack traces for exceptions thrown inside lambdas might look a bit different. However, modern IDEs provide good support for stepping through lambda code.
* **Performance**: For very small lists or simple operations, the overhead of creating streams might sometimes outweigh the benefits. However, for larger collections and complex chained operations, streams and lambdas are often more performant due to potential optimizations (like parallel processing).
* `this` **vs. `this`**: Remember that `this` inside a lambda refers to the enclosing instance, unlike in an anonymous inner class where `this` refers to the anonymous class itself. This can sometimes simplify code.

# Conclusion
Lambda expressions are a transformative feature in Java 8, bringing functional
programming capabilities to the language. They, along with functional interfaces
and the Stream API, provide a powerful and expressive way to write concise,
readable, and efficient code, particularly for data processing and concurrent
programming. Mastering lambdas is essential for modern Java development.