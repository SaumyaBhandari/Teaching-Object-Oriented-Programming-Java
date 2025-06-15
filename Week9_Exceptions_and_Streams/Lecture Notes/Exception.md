# Unit V: Exception Handling

## 5.1 Introduction to Exception

### What is an Exception?
In the world of programming, an **exception** is an event that disrupts the normal
flow of a program's instructions. Think of it as an unexpected problem or error
that occurs during the execution of a program. When an exception occurs, the
program's normal execution path is interrupted, and it needs a way to handle
this unusual situation gracefully.

Imagine you're following a recipe. Normally, you go from step 1 to step 2 to step 3.
But what if one of the ingredients isn't available, or the oven suddenly stops
working? These unexpected events are like exceptions. If you don't have a plan for
them, the whole cooking process might just crash.

In Java, exceptions are powerful mechanisms that allow your programs to:

* **Report errors**: Inform you and the user that something went wrong.
* **Handle errors gracefully**: Prevent the program from crashing abruptly.
* **Recover from errors**: Attempt to fix the problem and continue execution, if possible.
* **Improve robustness**: Make your software more reliable and less prone to unexpected failures.

### Why is Exception Handling Important?
Consider a scenario where your program is reading data from a file. What happens
if the file doesn't exist? Without exception handling, your program might simply
crash, leaving the user confused and potentially losing unsaved work. With
exception handling, you can detect the "file not found" situation, inform the user,
and perhaps prompt them to enter a correct file path.

**Scene: Driving a Car**
Think about driving a car. Normally, you drive straight, follow traffic lights,
and reach your destination.

* **Normal Flow**: Driving on a clear road, following traffic rules.
* **Exception**: A sudden flat tire. If you don't have a spare and know how to
    change it (exception handling), your journey ends abruptly. If you do, you can
    recover and continue.
* **Another Exception**: Running out of fuel. If you don't notice it (no error
    detection) and don't pull over to refuel (no handling), the car stops.

## 5.2 Errors in Java Program
Before we dive deeper into exceptions, it's crucial to understand the different
types of problems that can occur in a Java program. In Java, these problems
broadly fall into two categories: **Errors** and **Exceptions**.

### Errors (`java.lang.Error`)
Errors represent serious problems that typically indicate an unrecoverable situation.
These are often issues with the Java Virtual Machine (JVM) itself or a system-level
problem. Programmers usually cannot anticipate or recover from these kinds of errors.
They are typically beyond the scope of your application's control.

Think of an "Error" as a catastrophic failure.

### Examples of Errors:

* **`OutOfMemoryError`**:
    * **Description**: This error occurs when the Java Virtual Machine (JVM)
        runs out of memory and cannot allocate new objects. This often happens if
        your program tries to create too many objects, has a memory leak, or is
        processing extremely large datasets without efficient memory management.
    * **Scenario 1: Infinite Loop Creating Objects**
        ```java
        public class OutOfMemoryErrorExample1 {
            public static void main(String[] args) {
                // This ArrayList will keep growing, consuming more and more memory
                java.util.List<Object> list = new java.util.ArrayList<>();
                while (true) {
                    list.add(new Object()); // Keep adding new objects
                }
            }
        }
        ```
        * **Explanation**: Running this code will eventually cause the JVM to
            exhaust its available heap memory, leading to an `OutOfMemoryError`.
    * **Scenario 2: Loading Huge Data without Paging**
        Imagine an application that attempts to load an entire database table
        containing billions of rows into memory at once. This would quickly lead
        to an `OutOfMemoryError`.

* **`StackOverflowError`**:
    * **Description**: This error occurs when the recursion depth is too large,
        meaning a method calls itself too many times without returning, causing
        the call stack to overflow. Each method call consumes a small amount of
        memory on the stack, and if this stack grows beyond its limit, this error
        occurs.
    * **Scenario 1: Infinite Recursion**
        ```java
        public class StackOverflowErrorExample1 {
            public static void main(String[] args) {
                recursiveMethod();
            }

            public static void recursiveMethod() {
                recursiveMethod(); // Calls itself endlessly
            }
        }
        ```
        * **Explanation**: This program will continuously call `recursiveMethod()`,
            leading to an ever-growing call stack. Eventually, the stack will
            overflow, resulting in a `StackOverflowError`.
    * **Scenario 2: Deeply Nested Method Calls (uncommon in well-designed code)**
        While less common, extremely deep legitimate recursion or highly nested
        method calls could theoretically lead to this error, although it usually
        points to an algorithmic flaw (like an infinite recursion).

* **`NoClassDefFoundError`**:
    * **Description**: This error occurs when the Java Virtual Machine (JVM)
        tries to load a class at runtime and cannot find its definition. This is
        different from a `ClassNotFoundException` (which is an exception and occurs
        at compile time or when a class is dynamically loaded). `NoClassDefFoundError`
        indicates that the class was present during compilation but missing at runtime.
    * **Scenario**:
        1. Compile `MyClassThatUsesMissingClass.java` which depends on
           `MissingClass.java`.
        2. Delete `MissingClass.class` (or move it out of the classpath).
        3. Run `MyClassThatUsesMissingClass`.
        ```java
        // File: MissingClass.java
        // (Assume this file exists during compilation, then gets deleted or moved)
        public class MissingClass {
            public void doSomething() {
                System.out.println("Doing something...");
            }
        }
        // File: MyClassThatUsesMissingClass.java
        public class MyClassThatUsesMissingClass {
            public static void main(String[] args) {
                MissingClass mc = new MissingClass();
                mc.doSomething();
            }
        }
        ```
        * **Explanation**: If you compile both, then delete `MissingClass.class`
            from the directory, and then run `MyClassThatUsesMissingClass`, you
            will get a `NoClassDefFoundError`. The JVM finds `MyClassThatUsesMissingClass`,
            but when it tries to load `MissingClass` which it references, it can't find
            the `.class` file.

### Key Takeaway about Errors:
Errors are generally beyond the scope of what your application should try to catch
and handle. They usually indicate a severe problem that requires intervention
outside the program's normal execution (e.g., fixing memory leaks, adjusting JVM
settings, or fixing deployment issues).

## 5.2 **Exceptions** in Java Program

In Java, exceptions are part of the `java.lang.Throwable` class hierarchy. `Throwable`
has two direct subclasses: `Error` and `Exception`. We've just discussed `Error`.
Now let's focus on `Exception`.

**Exceptions**, unlike errors, represent conditions that a reasonable application
might want to catch. They are problems that occur during the normal operation
of a program but are still unexpected and need to be handled to maintain program
stability.

Java exceptions are broadly categorized into two main types:

1.  **Checked Exceptions**
2.  **Unchecked Exceptions** (which include `RuntimeException` and its subclasses)

Let's break them down in detail.

### 1. Checked Exceptions
* **Definition**: These are exceptions that the Java compiler checks at compile time.
    This means that if a method can throw a checked exception, and you call that
    method, you must either handle the exception (using a `try-catch` block) or
    declare that your method also throws that exception (using the `throws` keyword).
    If you don't do one of these, the compiler will report a compilation error.
* **Purpose**: Checked exceptions are used for predictable but unrecoverable
    problems that your program should anticipate and handle. They force the programmer
    to think about and deal with potential issues.
* **Inheritance**: All checked exceptions are direct or indirect subclasses of
    `java.lang.Exception` but **not** `java.lang.RuntimeException`.

### Examples/Types for Checked Exceptions:

* **`IOException`**:
    * **Description**: This is a broad exception that indicates problems with
        input/output operations. It's often thrown by methods dealing with files,
        network streams, etc.
    * **Scenario 1: `FileNotFoundException` (a subclass of `IOException`)**
        ```java
        import java.io.File;
        import java.io.FileReader;
        import java.io.IOException;

        public class CheckedExceptionExample1 {
            public static void main(String[] args) {
                File file = new File("nonexistent_file.txt"); // This file does not exist
                FileReader fr = null;
                try {
                    fr = new FileReader(file);
                    System.out.println("File opened successfully.");
                } catch (IOException e) {
                    System.err.println("Error reading or opening file: " + e.getMessage());
                    if (e instanceof java.io.FileNotFoundException) {
                        System.err.println("The file '" + file.getName() + "' was not found. Please check the path.");
                    }
                } finally {
                    if (fr != null) {
                        try {
                            fr.close(); // Closing the reader can also throw IOException
                        } catch (IOException e) {
                            System.err.println("Error closing file reader: " + e.getMessage());
                        }
                    }
                }
            }
        }
        ```
        * **Explanation**: The `FileReader` constructor can throw `FileNotFoundException`
            (which is an `IOException`). The compiler forces us to either handle it
            with `try-catch` or declare `throws IOException` in the `main` method signature.
            Here, we've handled it.
    * **Scenario 2: Writing to a Read-Only File (less common for direct `IOException`)**
        ```java
        import java.io.FileWriter;
        import java.io.IOException;

        public class CheckedExceptionExample2 {
            public static void main(String[] args) {
                String filePath = "read_only_file.txt"; // Imagine this file exists and is read-only
                FileWriter writer = null;
                try {
                    writer = new FileWriter(filePath);
                    writer.write("Attempting to write to a potentially read-only file.");
                    System.out.println("Successfully attempted to write.");
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                    System.err.println("Possible reasons: file is read-only, disk full, etc.");
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException e) {
                            System.err.println("Error closing writer: " + e.getMessage());
                        }
                    }
                }
            }
        }
        ```
        * **Explanation**: While creating a `FileWriter` on a read-only file might not
            always immediately throw an `IOException` (it depends on the OS and permissions),
            the `write()` method or `close()` method almost certainly will if the operation
            is denied. The compiler still requires handling `IOException`.

* **`SQLException`**:
    * **Description**: This exception indicates a problem with database access or
        a database error. It's part of the Java Database Connectivity (JDBC) API.
    * **Scenario: Database Connection Failure**
        ```java
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

        public class CheckedExceptionExample3 {
            public static void main(String[] args) {
                Connection conn = null;
                try {
                    // This URL is intentionally incorrect to simulate a connection error
                    String url = "jdbc:mysql://localhost:3306/nonexistent_database";
                    String user = "root";
                    String password = "wrongpassword"; // Incorrect password
                    conn = DriverManager.getConnection(url, user, password); // Can throw SQLException
                    System.out.println("Database connection successful!");
                } catch (SQLException e) {
                    System.err.println("Database error occurred: " + e.getMessage());
                    System.err.println("SQL State: " + e.getSQLState());
                    System.err.println("Vendor Error Code: " + e.getErrorCode());
                    System.err.println("Possible reasons: incorrect URL, wrong credentials, database not running.");
                } finally {
                    if (conn != null) {
                        try {
                            conn.close(); // Can also throw SQLException
                        } catch (SQLException e) {
                            System.err.println("Error closing database connection: " + e.getMessage());
                        }
                    }
                }
            }
        }
        ```
        * **Explanation**: The `DriverManager.getConnection()` method is declared to throw
            `SQLException`. The compiler forces us to handle it. Here, we catch it and print
            detailed information useful for debugging database issues.

* **`ClassNotFoundException`**:
    * **Description**: This exception occurs when an application tries to load
        a class dynamically using methods like `Class.forName()` but the specified
        class cannot be found by the class loader.
    * **Scenario: Dynamically Loading a Non-Existent Driver**
        ```java
        public class CheckedExceptionExample4 {
            public static void main(String[] args) {
                try {
                    // Attempt to load a JDBC driver that does not exist
                    Class.forName("com.mysql.jdbc.NonExistentDriver"); // Can throw ClassNotFoundException
                    System.out.println("Driver loaded successfully.");
                } catch (ClassNotFoundException e) {
                    System.err.println("Driver not found: " + e.getMessage());
                    System.err.println("Please ensure the JDBC driver JAR is in your classpath.");
                }
            }
        }
        ```
        * **Explanation**: `Class.forName()` is used to load a class dynamically.
            If the class (in this case, a JDBC driver) is not found in the classpath,
            a `ClassNotFoundException` is thrown. The compiler requires this to be handled.

* **`InterruptedException`**:
    * **Description**: This exception is thrown when a thread that is sleeping,
        waiting, or otherwise occupied is interrupted. It's a common way to signal
        a thread to stop its current activity.
    * **Scenario: Interrupting a Sleeping Thread**
        ```java
        public class CheckedExceptionExample5 {
            public static void main(String[] args) {
                System.out.println("Main thread is about to sleep...");
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds (can throw InterruptedException)
                    System.out.println("Main thread woke up normally.");
                } catch (InterruptedException e) {
                    System.err.println("Main thread was interrupted while sleeping: " + e.getMessage());
                    // This typically means another thread called interrupt() on this thread.
                    // When interrupted, the interrupt status of the thread is cleared.
                    // It's good practice to re-interrupt the current thread if you don't handle it fully
                    // Thread.currentThread().interrupt();
                }
                System.out.println("Main thread finished.");
            }
        }
        ```
        * **Explanation**: The `Thread.sleep()` method can be interrupted by
            another thread, causing an `InterruptedException`. The compiler forces
            you to handle this, as it's a way for your program to respond to external signals.

### 2. Unchecked Exceptions (Runtime Exceptions)
* **Definition**: These are exceptions that the Java compiler does not check
    at compile time. This means you are not required to handle them with `try-catch`
    or declare them with `throws`. However, if they occur and are not handled,
    they will cause your program to terminate abruptly.
* **Purpose**: Unchecked exceptions typically represent programming errors,
    logical flaws, or situations that are generally considered unrecoverable
    during normal program execution and should ideally be prevented by proper
    coding practices rather than caught.
* **Inheritance**: All unchecked exceptions are subclasses of
    `java.lang.RuntimeException`. This means `RuntimeException` itself and all
    its subclasses are unchecked.

### Examples for Unchecked Exceptions:

* **`NullPointerException`**:
    * **Description**: This is perhaps the most common unchecked exception. It
        occurs when you try to access a member of an object (like a method or a field)
        when the object reference itself is `null`. It means you're trying to use
        something that doesn't exist.
    * **Scenario 1: Calling a Method on a Null Object**
        ```java
        public class UncheckedExceptionExample1 {
            public static void main(String[] args) {
                String str = null; // str is null
                System.out.println(str.length()); // This will throw NullPointerException
                System.out.println("This line will not be executed.");
            }
        }
        ```
        * **Explanation**: `str` points to nothing (`null`). When you try to
            call `length()` on `null`, Java doesn't know what to do and throws a
            `NullPointerException`.
    * **Scenario 2: Accessing an Element in an Uninitialized Array**
        ```java
        public class UncheckedExceptionExample2 {
            public static void main(String[] args) {
                String[] names = new String[3]; // Array created, but elements are null by default
                System.out.println(names[0].toUpperCase()); // names[0] is null
            }
        }
        ```
        * **Explanation**: The array `names` is initialized, but its elements
            (`names[0]`, `names[1]`, `names[2]`) are `null` by default. Attempting
            to call `toUpperCase()` on `names[0]` results in `NullPointerException`.
    * **Scenario 3: Returning Null from a Method and Not Checking**
        ```java
        public class UncheckedExceptionExample3 {
            public static String getUserName(int userId) {
                if (userId == 1) {
                    return "Alice";
                } else {
                    return null; // For any other user ID, we return null
                }
            }

            public static void main(String[] args) {
                String name = getUserName(2); // This returns null
                System.out.println("User name length: " + name.length()); // NPE here
            }
        }
        ```
        * **Explanation**: The `getUserName` method explicitly returns `null`
            for `userId` not equal to 1. If the caller doesn't check for `null`
            before using the returned `String`, a `NullPointerException` occurs.

* **`ArrayIndexOutOfBoundsException`**:
    * **Description**: This exception occurs when you try to access an array
        element using an index that is outside the valid range of indices for that
        array (i.e., less than 0 or greater than or equal to the array's length).
    * **Scenario 1: Accessing an Index Beyond Length**
        ```java
        public class UncheckedExceptionExample4 {
            public static void main(String[] args) {
                int[] numbers = {10, 20, 30}; // Length is 3, valid indices are 0, 1, 2
                System.out.println(numbers[3]); // Index 3 is out of bounds
            }
        }
        ```
        * **Explanation**: The `numbers` array has elements at indices 0, 1, and 2.
            Trying to access `numbers[3]` goes beyond the array's boundaries, causing an
            `ArrayIndexOutOfBoundsException`.
    * **Scenario 2: Accessing a Negative Index**
        ```java
        public class UncheckedExceptionExample5 {
            public static void main(String[] args) {
                String[] colors = {"Red", "Green", "Blue"};
                System.out.println(colors[-1]); // Index -1 is out of bounds
            }
        }
        ```
        * **Explanation**: Array indices cannot be negative. Accessing `colors[-1]`
            results in an `ArrayIndexOutOfBoundsException`.

* **`ArithmeticException`**:
    * **Description**: This exception is thrown when an exceptional arithmetic
        condition occurs, such as integer division by zero.
    * **Scenario 1: Integer Division by Zero**
        ```java
        public class UncheckedExceptionExample6 {
            public static void main(String[] args) {
                int numerator = 10;
                int denominator = 0;
                int result = numerator / denominator; // Division by zero
                System.out.println("Result: " + result);
            }
        }
        ```
        * **Explanation**: Integer division by zero is mathematically undefined
            and throws an `ArithmeticException`. Note that floating-point division
            by zero (e.g., `10.0 / 0.0`) results in `Infinity` or `NaN` (Not a Number),
            not an exception.
    * **Scenario 2: Modulo by Zero**
        ```java
        public class UncheckedExceptionExample7 {
            public static void main(String[] args) {
                int num = 15;
                int divisor = 0;
                int remainder = num % divisor; // Modulo by zero
                System.out.println("Remainder: " + remainder);
            }
        }
        ```
        * **Explanation**: Similar to division, the modulo operation with zero as
            the divisor also throws an `ArithmeticException`.

* **`IllegalArgumentException`**:
    * **Description**: This exception is thrown to indicate that a method has been
        passed an illegal or inappropriate argument. This usually means the argument
        is outside the valid range or doesn't meet specific criteria defined by the method.
    * **Scenario 1: Invalid Age Input**
        ```java
        public class UncheckedExceptionExample8 {
            public static void setAge(int age) {
                if (age < 0 || age > 120) {
                    throw new IllegalArgumentException("Age must be between 0 and 120.");
                }
                System.out.println("Age set to: " + age);
            }

            public static void main(String[] args) {
                setAge(30);
                setAge(-5); // Invalid age
            }
        }
        ```
        * **Explanation**: The `setAge` method explicitly checks for invalid age values.
            If an invalid age is passed, it throws an `IllegalArgumentException` to signal
            that the argument is inappropriate.
    * **Scenario 2: Invalid String Format for Number Parsing**
        ```java
        public class UncheckedExceptionExample9 {
            public static void main(String[] args) {
                String invalidNumber = "abc";
                int value = Integer.parseInt(invalidNumber); // Can throw NumberFormatException
                System.out.println("Parsed value: " + value);
            }
        }
        ```
        * **Explanation**: `NumberFormatException` is a subclass of `IllegalArgumentException`.
            When `Integer.parseInt()` receives a string that cannot be parsed into an integer,
            it throws this exception.

* **`ClassCastException`**:
    * **Description**: This exception is thrown when an attempt is made to cast
        an object to a type that it is not compatible with at runtime.
    * **Scenario 1: Incorrect Downcasting**
        ```java
        class Animal { }
        class Dog extends Animal { }
        class Cat extends Animal { }

        public class UncheckedExceptionExample10 {
            public static void main(String[] args) {
                Animal myAnimal = new Dog(); // myAnimal actually holds a Dog object
                // myAnimal is a Dog, so casting to Dog is fine
                Dog myDog = (Dog) myAnimal;
                System.out.println("Successfully cast to Dog.");

                Animal anotherAnimal = new Cat(); // anotherAnimal actually holds a Cat object
                // Attempting to cast a Cat object to Dog
                Dog anotherDog = (Dog) anotherAnimal; // ClassCastException here
                System.out.println("Successfully cast to Dog (again)."); // This won't be reached
            }
        }
        ```
        * **Explanation**: `anotherAnimal` is actually a `Cat` object. While `Cat`
            is an `Animal`, it's not a `Dog`. Attempting to cast `anotherAnimal` to
            `Dog` at runtime causes a `ClassCastException`.
    * **Scenario 2: Casting an Object to an Incompatible Interface**
        Suppose you have `Object obj = "hello";` and you try to cast `obj` to a
        custom interface `MyInterface` if `String` does not implement `MyInterface`.

* **`IllegalStateException`**:
    * **Description**: This exception is thrown when a method is invoked at an
        illegal or inappropriate time, meaning the object is not in an appropriate
        state for the requested operation.
    * **Scenario: Trying to Stop an Already Stopped Thread**
        ```java
        public class UncheckedExceptionExample11 {
            public static void main(String[] args) {
                Thread thread = new Thread(() -> System.out.println("Thread running"));
                thread.start();
                try {
                    thread.join(); // Wait for the thread to finish
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                // Now the thread is dead/terminated.
                // Attempting to start it again throws IllegalThreadStateException (a subclass)
                thread.start(); // This will throw IllegalThreadStateException
            }
        }
        ```
        * **Explanation**: A `Thread` can only be started once. Attempting to
            call `start()` on a thread that has already terminated (or is not in a
            `NEW` state) throws an `IllegalThreadStateException`, which is a subclass
            of `IllegalStateException`.
    * **Scenario 2: Iterator without next element**
        ```java
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;

        public class UncheckedExceptionExample12 {
            public static void main(String[] args) {
                List<String> fruits = new ArrayList<>();
                fruits.add("Apple");
                Iterator<String> iterator = fruits.iterator();

                System.out.println(iterator.next()); // "Apple"
                // Now the iterator has no more elements.
                System.out.println(iterator.next()); // IllegalStateException (more specifically NoSuchElementException, but illustrates state)
            }
        }
        ```
        * **Explanation**: After consuming the last element, calling `next()` on an
            `Iterator` again (when `hasNext()` would be false) throws a `NoSuchElementException`,
            which is a subclass of `RuntimeException`, indicating an invalid state for the iterator.

### Summary of Exception Types:

| Type                                   | Checked/Unchecked | Compiler Check? | Recovery Expected? | Represents                                     | How to Handle                     |
| :------------------------------------- | :---------------- | :-------------- | :----------------- | :--------------------------------------------- | :-------------------------------- |
| Checked Exception                      | Checked           | Yes             | Yes                | Anticipated external issues (I/O, DB)          | `try-catch` or `throws`           |
| Unchecked Exception (`RuntimeException` & subclasses) | Unchecked         | No              | No (usually)       | Programming errors, logical flaws              | Prevent (fix code) or `try-catch` (for robustness) |
| Error (`java.lang.Error` & subclasses) | Unchecked         | No              | No                 | JVM-related, catastrophic issues               | Do not catch, fix environment/design |

## 5.3 Exception Handling Mechanism
Java provides a robust mechanism to handle exceptions, ensuring that your programs
can gracefully recover from errors or at least terminate in a controlled manner.
The core components of this mechanism are:

* `try`
* `catch`
* `finally`
* `throw`
* `throws`

Let's explore each one with extensive examples.

### 1. `try` Block
---
* **Purpose**: The `try` block encloses the code segment that might throw an
    exception. It tells the JVM, "Hey, run this code, but be aware that something
    might go wrong here."
* **Syntax**:
    ```java
    try {
        // Code that might throw an exception
    }
    // ... followed by one or more catch blocks or a finally block
    ```
* **Key Point**: A `try` block must be followed by at least one `catch` block
    or a `finally` block (or both). It cannot stand alone.

## Examples:

```java
// Example 1: Basic try block for potential division by zero
public class TryExample1 {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // This line will throw an ArithmeticException
            System.out.println("Result: " + result); // This line will not be executed
        } catch (ArithmeticException e) {
            System.err.println("Cannot divide by zero!");
        }
        System.out.println("Program continues after exception handling.");
    }
}
```

```java
// Example 2: Try block with potential NullPointerException
public class TryExample2 {
    public static void main(String[] args) {
        String data = null;
        try {
            System.out.println("Attempting to get length of data...");
            int length = data.length(); // This will throw NullPointerException
            System.out.println("Length: " + length); // This line will not be executed
        } catch (NullPointerException e) {
            System.err.println("Error: Cannot get length of a null string.");
        }
        System.out.println("Program continues normally.");
    }
}
```

### 2. `catch` Block
---
* **Purpose**: The `catch` block is used to handle a specific type of exception
    that was thrown within the preceding `try` block. If an exception of the
    specified type (or a subclass of that type) occurs in the `try` block, the
    control is transferred to the corresponding `catch` block.
* **Syntax**:
    ```java
    try {
        // Code that might throw an exception
    } catch (ExceptionType variableName) {
        // Code to handle the exception
        // e.g., log the error, display an error message, attempt recovery
    }
    ```
* **Key Points**:
    * You can have multiple `catch` blocks for a single `try` block, each handling
        a different type of exception.
    * When multiple `catch` blocks are present, they are evaluated in order from
        top to bottom. It's crucial to place more specific exception types before
        more general ones (e.g., `FileNotFoundException` before `IOException`).
    * The `variableName` in the `catch` block (e.g., `e`) is an instance of the
        `ExceptionType` and provides information about the exception (like its message,
        stack trace, etc.).

### Examples for `catch` Block:

```java
// Example 1: Catching a specific exception (ArithmeticException)
public class CatchExample1 {
    public static void main(String[] args) {
        try {
            int num1 = 10;
            int num2 = 0;
            int result = num1 / num2; // Throws ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Error: Division by zero is not allowed.");
            System.err.println("Exception message: " + e.getMessage()); // Get the specific error message
            e.printStackTrace(); // Print the stack trace for debugging
        }
        System.out.println("After the try-catch block.");
    }
}
```

```java
// Example 2: Catching multiple specific exceptions (ArrayIndexOutOfBoundsException, NullPointerException)
public class CatchExample2 {
    public static void main(String[] args) {
        String[] names = {"Alice", "Bob"};
        String greeting = null;

        try {
            System.out.println(names[2]); // Throws ArrayIndexOutOfBoundsException
            System.out.println(greeting.length()); // Throws NullPointerException (if first line didn't)
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Array index is out of bounds.");
            System.err.println("Details: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error: Attempted to use a null reference.");
            System.err.println("Details: " + e.getMessage());
        }
        System.out.println("Program finished.");
    }
}
```

```java
// Example 3: Catching a more general exception (Exception)
// This should generally be avoided unless you re-throw or have a specific reason.
public class CatchExample3 {
    public static void main(String[] args) {
        try {
            // Can throw multiple types of exceptions
            String numberStr = "abc";
            int value = Integer.parseInt(numberStr); // NumberFormatException (subclass of IllegalArgumentException)
            System.out.println("Value: " + value);

            int[] arr = new int[5];
            System.out.println(arr[10]); // ArrayIndexOutOfBoundsException
        } catch (Exception e) { // Catches any type of exception (highly general)
            System.err.println("An unexpected error occurred!");
            System.err.println("Error Type: " + e.getClass().getName());
            System.err.println("Error Message: " + e.getMessage());
            e.printStackTrace(); // Always good for debugging when catching general Exception
        }
        System.out.println("Program completed.");
    }
}
```

```java
// Example 4: Order of catch blocks - Most specific to least specific
import java.io.FileNotFoundException;
import java.io.IOException;

public class CatchExample4 {
    public static void main(String[] args) {
        try {
            java.io.FileReader fr = new java.io.FileReader("nonexistent.txt"); // FileNotFoundException
            fr.close(); // Can throw IOException
        } catch (FileNotFoundException e) { // More specific, must come first
            System.err.println("Error: File was not found! " + e.getMessage());
        } catch (IOException e) { // More general, comes after FileNotFoundException
            System.err.println("Error: An I/O error occurred! " + e.getMessage());
        } catch (Exception e) { // Very general, always comes last
            System.err.println("An unknown error occurred! " + e.getMessage());
        }
        System.out.println("File operation attempted.");
    }
}
```

```java
// Example 5: Multi-catch block (Java 7 and later) - catching multiple exceptions in one catch block
public class CatchExample5 {
    public static void main(String[] args) {
        try {
            String input = "test";
            if (input.equals("test")) {
                int result = 10 / 0; // ArithmeticException
            } else if (input.equals("null")) {
                String s = null;
                System.out.println(s.length()); // NullPointerException
            }
        } catch (ArithmeticException | NullPointerException e) { // Multi-catch
            System.err.println("Caught an arithmetic or null pointer error: " + e.getMessage());
            System.err.println("Type of exception: " + e.getClass().getName());
        } catch (Exception e) {
            System.err.println("Caught another type of exception: " + e.getMessage());
        }
    }
}
```

### 3. `finally` Block
**Purpose:** The `finally` block is optional, but if present, its code is **always** executed, regardless of whether an exception occurred in the `try` block or not. It's typically used for **cleanup operations**.

**Use Cases:**
* Closing open files.
* Closing database connections.
* Releasing network resources.
* Any code that **must** execute to ensure resources are properly managed.

**Syntax:**
```java
try {
    // Code that might throw an exception
} catch (ExceptionType variableName) {
    // Handle the exception
} finally {
    // Code that will always execute (cleanup)
}
```

**Key Points:**
* The finally block executes even if return statements are present in the try or catch blocks.
* The only time a finally block might not execute is if the JVM exits (e.g., via `System.exit()`) or if a fatal error occurs (like `OutOfMemoryError`).

### Examples for `finally` Block:

**Java**

```java
// Example 1: finally block executing after successful try
public class FinallyExample1 {
    public static void main(String[] args) {
        System.out.println("Entering try-finally block...");
        try {
            int result = 10 / 2;
            System.out.println("Result: " + result);
        } finally {
            System.out.println("Finally block executed (no exception).");
        }
        System.out.println("Exited try-finally block.");
    }
}
```

```java
// Example 2: finally block executing after exception caught
public class FinallyExample2 {
    public static void main(String[] args) {
        System.out.println("Entering try-catch-finally block...");
        try {
            int result = 10 / 0; // ArithmeticException
            System.out.println("Result: " + result); // Not executed
        } catch (ArithmeticException e) {
            System.err.println("Caught exception: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed (exception caught).");
        }
        System.out.println("Exited try-catch-finally block.");
    }
}
```

```java
// Example 3: finally block with resource cleanup (File handling)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FinallyExample3 {
    public static void main(String[] args) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("example.txt")); // Create example.txt first
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        } finally {
            // This ensures the reader is closed even if an exception occurs
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Reader closed in finally block.");
                } catch (IOException e) {
                    System.err.println("Error closing reader: " + e.getMessage());
                }
            }
        }
        System.out.println("File reading process finished.");
    }
}
```

```java
// Example 4: finally block with return statement in try/catch
public class FinallyExample4 {
    public static int divide(int a, int b) {
        try {
            System.out.println("Inside try block.");
            return a / b; // If b is 0, this throws exception, finally still runs
        } catch (ArithmeticException e) {
            System.err.println("Caught exception: " + e.getMessage());
            return -1; // If caught, this returns, but finally still runs
        } finally {
            System.out.println("Inside finally block. This always executes!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Result 1: " + divide(10, 2));
        System.out.println("Result 2: " + divide(10, 0));
    }
}
```

```java
// Example 5: Try-with-Resources (Java 7+ feature) - A better way for cleanup
// This implicitly uses a finally block for AutoCloseable resources.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        // Resources declared in the try-with-resources statement are automatically closed.
        // The resource must implement java.lang.AutoCloseable interface.
        try (BufferedReader reader = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("File read successfully.");
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
        System.out.println("File reading process finished (using try-with-resources).");
    }
}
```

**Explanation of Try-with-Resources:** This is a syntactic sugar introduced in Java 7 to simplify resource management. Any resource (like `BufferedReader`, `Connection`, `FileOutputStream`) that implements the `AutoCloseable` interface can be declared within the parentheses of a try statement. The resource will be automatically closed when the try block exits, whether normally or due to an exception. This greatly reduces the boilerplate code of finally blocks for cleanup.


### 4. `throw` Keyword
**Purpose:** The `throw` keyword is used to explicitly throw an instance of an exception. You use `throw` when you want to signal that an exceptional condition has occurred at a particular point in your code.

**Syntax:**
```java
throw new ExceptionType("Error message");
```

**Key Points:**
* You can throw both checked and unchecked exceptions.
* If you throw a checked exception within a method, that method must either catch it or declare that it throws that exception (or a superclass of it).
* If you throw an unchecked exception, you are not required to catch it or declare it, but you can.

### Examples for `throw` Keyword:

```java
// Example 1: Throwing an Unchecked Exception (IllegalArgumentException)
public class ThrowExample1 {
    public static void setScore(int score) {
        if (score < 0 || score > 100) {
            // Explicitly throwing an IllegalArgumentException
            throw new IllegalArgumentException("Score must be between 0 and 100. Provided: " + score);
        }
        System.out.println("Score set to: " + score);
    }

    public static void main(String[] args) {
        try {
            setScore(85);
            setScore(105); // This will cause an exception
        } catch (IllegalArgumentException e) {
            System.err.println("Caught an error: " + e.getMessage());
        }
        System.out.println("Program continues after handling.");
    }
}
```

```java
// Example 2: Throwing a Checked Exception (IOException)
// Note: This method must declare 'throws IOException'
import java.io.IOException;

public class ThrowExample2 {
    public static void processFile(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            // Throwing a checked exception. The caller MUST handle it.
            throw new IOException("File name cannot be null or empty.");
        }
        // Imagine complex file processing here
        System.out.println("Processing file: " + fileName);
        // Simulate another IO error
        if (fileName.equals("corrupt.txt")) {
            throw new IOException("File " + fileName + " is corrupt!");
        }
    }

    public static void main(String[] args) {
        try {
            processFile("data.txt");
            processFile(null); // This will throw IOException
            processFile("corrupt.txt"); // This will throw IOException
        } catch (IOException e) {
            System.err.println("File processing error: " + e.getMessage());
        }
        System.out.println("Application finished.");
    }
}
```

```java
// Example 3: Chaining Exceptions (wrapping an exception in another)
// This is useful when a low-level exception (e.g., SQLException) needs to be
// re-thrown as a higher-level application-specific exception.
import java.sql.SQLException;

public class ThrowExample3 {
    public static void fetchDataFromDatabase() throws DataAccessException { // Custom unchecked exception, see 5.5
        try {
            // Simulate a database operation that fails
            if (true) { // Always throws for demonstration
                throw new SQLException("Database connection failed!");
            }
            System.out.println("Data fetched successfully.");
        } catch (SQLException e) {
            // Wrap the SQLException inside a custom DataAccessException
            throw new DataAccessException("Failed to fetch data from DB.", e);
        }
    }

    public static void main(String[] args) {
        try {
            fetchDataFromDatabase();
        } catch (DataAccessException e) {
            System.err.println("Application data access error: " + e.getMessage());
            System.err.println("Root cause: " + e.getCause().getMessage()); // Access the original exception
        }
    }
}

// (For ThrowExample3) Custom unchecked exception for demonstration (see 5.5 for details)
class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

### 5. `throws` Keyword
**Purpose:** The `throws` keyword is used in a method signature to declare that the method might throw one or more specified checked exceptions. It essentially tells the compiler, "I know this method could throw this exception, but I'm not handling it here. The calling method should handle it."

**Syntax:**
```java
public void myMethod() throws IOException, SQLException {
    // ... code that might throw IOException or SQLException
}
```

**Key Points:**
* It's used primarily for checked exceptions. You can declare unchecked exceptions with throws, but it's generally not required or recommended as they indicate programming errors.
* If a method calls another method that declares a checked exception with throws, the calling method must either catch that exception or also declare it with throws. This propagates the responsibility up the call stack.

### Examples for `throws` Keyword:

```java
// Example 1: Declaring a single checked exception
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ThrowsExample1 {
    // This method declares that it might throw FileNotFoundException
    public static void readFile(String filePath) throws FileNotFoundException {
        System.out.println("Attempting to read file: " + filePath);
        FileReader reader = new FileReader(filePath); // This line can throw FileNotFoundException
        // ... imagine reading operations
        System.out.println("File " + filePath + " opened successfully.");
        // In a real scenario, you'd close the reader here or in a finally/try-with-resources
    }

    public static void main(String[] args) {
        try {
            readFile("existing_file.txt"); // This file should exist for success
            readFile("non_existing_file.txt"); // This will throw FileNotFoundException
        } catch (FileNotFoundException e) {
            System.err.println("Error: The specified file was not found. " + e.getMessage());
            System.err.println("Please check the file path.");
        }
        System.out.println("Program finished attempting file reads.");
    }
}
```

```java
// Example 2: Declaring multiple checked exceptions
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ThrowsExample2 {
    // This method declares that it might throw IOException or SQLException
    public static void processData(String filePath, String dbUrl) throws IOException, SQLException {
        // Simulate file reading
        if (filePath.isEmpty()) {
            throw new IOException("File path cannot be empty.");
        }
        System.out.println("File " + filePath + " processed.");

        // Simulate database connection
        if (dbUrl.isEmpty()) {
            throw new SQLException("DB URL cannot be empty.");
        }
        // In a real app, you'd use proper DB credentials
        Connection conn = DriverManager.getConnection(dbUrl, "user", "pass");
        System.out.println("Connected to database: " + dbUrl);
        conn.close();
    }

    public static void main(String[] args) {
        try {
            processData("input.txt", "jdbc:mysql://localhost/mydb");
            processData("", "jdbc:postgresql://localhost/myotherdb"); // Throws IOException
            processData("input2.txt", ""); // Throws SQLException
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("A database error occurred: " + e.getMessage());
        }
        System.out.println("Data processing attempts finished.");
    }
}
```

```java
// Example 3: Propagating exception up the call stack
public class ThrowsExample3 {

    public static void methodC() throws ArithmeticException { // No need to declare here as it's RuntimeException
        System.out.println("Inside methodC.");
        int result = 10 / 0; // Throws ArithmeticException
        System.out.println("Result in C: " + result);
    }

    public static void methodB() throws ArithmeticException { // Can declare, but not strictly required for RuntimeException
        System.out.println("Inside methodB, calling methodC.");
        methodC(); // Calls a method that throws ArithmeticException
        System.out.println("MethodC finished (this won't be printed if exception occurs).");
    }

    public static void methodA() { // This method will catch the exception
        System.out.println("Inside methodA, calling methodB.");
        try {
            methodB(); // Calls a method that might throw ArithmeticException
            System.out.println("MethodB finished (this won't be printed if exception occurs).");
        } catch (ArithmeticException e) {
            System.err.println("Caught ArithmeticException in methodA: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("Starting program...");
        methodA();
        System.out.println("Program finished.");
    }
}
```

#### When to use throws vs. try-catch?
Use throws when:
* 1. You don't know how to handle the exception at the current level.
* 2. You want to force the caller to handle the exception.
* 3. The exception is a normal (though exceptional) outcome of the method's operation.

Use try-catch when:
* 1. You know how to recover from the exception.
* 2. You want to log the error and continue program execution.
* 3. You are at the top level of the application where you want to prevent crashes.

```java

public class ThrowsVsTryCatch {

    // Method that declares it throws IOException
    // It's the caller's responsibility to handle it.
    public static void readFileData(String filename) throws IOException {
        System.out.println("Attempting to read data from: " + filename);
        if (!filename.endsWith(".txt")) {
            throw new IOException("Only .txt files are supported.");
        }
        // Simulate reading
        if (filename.equals("missing.txt")) {
            throw new IOException("File not found: " + filename);
        }
        System.out.println("Data read successfully from " + filename);
    }

    public static void main(String[] args) {
        // Here, we choose to handle the IOException
        try {
            readFileData("my_data.txt"); // Valid
            readFileData("image.jpg");   // Throws IOException
            readFileData("missing.txt"); // Throws IOException
        } catch (IOException e) {
            System.err.println("Error during file data reading: " + e.getMessage());
            // Here, we decide to inform the user and continue
            System.out.println("Please provide a valid .txt file.");
        }

        System.out.println("\n--- Program continues after file operations ---");

        // Another scenario: a utility method that re-throws a checked exception as an unchecked one.
        // This is a common pattern in frameworks where you want to convert checked exceptions
        // into unchecked ones to avoid boilerplate 'throws' declarations everywhere,
        // especially when the underlying problem is considered a fatal configuration error.
        try {
            dangerousOperation();
        } catch (RuntimeException e) {
            System.err.println("Caught application-level runtime error: " + e.getMessage());
            System.err.println("Original cause: " + e.getCause().getMessage());
        }
    }

    // A method that might throw a checked exception (e.g., from a lower-level library)
    public static void lowLevelOperation() throws InterruptedException {
        System.out.println("Performing low-level operation...");
        Thread.sleep(100); // Checked exception
        System.out.println("Low-level operation finished.");
    }

    // A method that converts a checked exception into an unchecked exception (RuntimeException)
    // This is often done when the checked exception indicates a configuration problem
    // that the application cannot recover from, and you don't want every caller
    // to deal with it.
    public static void dangerousOperation() {
        try {
            lowLevelOperation();
        } catch (InterruptedException e) {
            // Wrap the checked exception in an unchecked RuntimeException
            // and re-throw it. This is called "exception wrapping" or "exception tunneling".
            throw new RuntimeException("Error during critical operation.", e);
        }
    }
}
```

### 5.4 Built-in Exceptions in Java
Java provides a rich set of built-in exception classes, which are organized in a hierarchy. These cover a wide range of common error conditions. We've already seen many of them while discussing checked and unchecked exceptions, but let's summarize and provide a few more unique examples.

All built-in exceptions ultimately inherit from `java.lang.Throwable`.

#### Common `RuntimeException` (Unchecked) Subclasses:

These exceptions usually indicate programming errors or logical flaws. You are not required to catch them, but good practice dictates preventing them.

* `NullPointerException`: Trying to use a null reference as if it were an object.
    **Example:**
    ```java
    String name = null;
    System.out.println(name.length()); // NPE
    ```
* `ArrayIndexOutOfBoundsException`: Accessing an array with an illegal index.
    **Example:**
    ```java
        int[] numbers = new int[5];
        System.out.println(numbers[5]); // AIOOBE
    ```

* `StringIndexOutOfBoundsException`: Accessing a string with an illegal index.
    **Example:**
    ```java
    String s = "hello";
    System.out.println(s.charAt(5)); // SIOOBE
    ```
* `ArithmeticException`: Exceptional arithmetic condition, like division by zero.
    **Example:**
    ```java
    int result = 10 / 0; // ArithmeticException
    ```
* `ClassCastException`: Attempting to cast an object to a type that it is not compatible with.
    **Example:**
    ```java
    Object obj = "Hello";
    Integer num = (Integer) obj; // ClassCastException
    ```
* `IllegalArgumentException`: A method has been passed an illegal or inappropriate argument.
    **Example:**
    ```java
    Integer.parseInt("abc"); // NumberFormatException (subclass of IllegalArgumentException)
    ```
* `IllegalStateException`: A method is invoked at an illegal or inappropriate time.
    **Example:**
    ```java
    java.util.Iterator<String> iter = new java.util.ArrayList<String>().iterator();
    iter.next(); // NoSuchElementException (subclass, often considered a form of IllegalStateException)
    ```
* `ConcurrentModificationException`: Detected when an object is concurrently modified by another thread or during iteration by the same thread using an unsupported operation.
    **Example:**
    ```java
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;

    public class ConcurrentModificationExample {
        public static void main(String[] args) {
            List<String> list = new ArrayList<>();
            list.add("A");
            list.add("B");
            list.add("C");

            // Incorrect way to remove elements during iteration
            for (String item : list) {
                if (item.equals("B")) {
                    list.remove(item); // Throws ConcurrentModificationException
                }
            }

            // Correct way using Iterator's remove() method
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (item.equals("B")) {
                    iterator.remove(); // This is safe
                }
            }
        }
    }
    ```

#### Common `Exception` (Checked) Subclasses:

These exceptions represent conditions that a well-written application should anticipate and handle. The compiler forces you to deal with them.

* `IOException`: General input/output operation failure.
* `FileNotFoundException` (subclass of `IOException`): A file specified by a pathname does not exist.
    **Example:**
    ```java
    import java.io.FileReader;
    import java.io.IOException;

    public class BuiltInCheckedExceptionExample {
        public static void main(String[] args) {
            try {
                FileReader file = new FileReader("non_existent_file.txt");
                // ... read from file
                file.close();
            } catch (IOException e) { // Catches FileNotFoundException too
                System.err.println("File error: " + e.getMessage());
            }
        }
    }
    ```
* `SQLException`: Problems with database access.
    **Example:**
    ```java
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class BuiltInCheckedExceptionExample2 {
        public static void main(String[] args) {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:9999/mydb", "user", "pass");
            } catch (SQLException e) {
                System.err.println("Database connection failed: " + e.getMessage());
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        System.err.println("Failed to close connection: " + e.getMessage());
                    }
                }
            }
        }
    }
    ```
* `ClassNotFoundException`: A class specified by name could not be found. (Typically when using `Class.forName()`)
    **Example:**
    ```java
    public class BuiltInCheckedExceptionExample3 {
        public static void main(String[] args) {
            try {
                Class.forName("com.example.NonExistentClass");
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + e.getMessage());
            }
        }
    }
    ```
* `InterruptedException`: A thread is interrupted while waiting, sleeping, or otherwise occupied.
    **Example:**
    ```java
    public class BuiltInCheckedExceptionExample4 {
        public static void main(String[] args) {
            try {
                Thread.sleep(2000); // Sleep for 2 seconds
            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted!");
                // Restore the interrupted status
                Thread.currentThread().interrupt();
            }
        }
    }
    ```
* `CloneNotSupportedException`: An object's class does not support the `Cloneable` interface.
    **Example:**
    ```java
    class MyClass implements Cloneable {
        int value;
        public MyClass(int value) { this.value = value; }

        // If you don't override clone(), or don't implement Cloneable
        // and try to call super.clone() in a child, this can occur.
        // A more common scenario is if you don't implement Cloneable.
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    class MyNonCloneableClass {
        int value;
        public MyNonCloneableClass(int value) { this.value = value; }
    }

    public class BuiltInCheckedExceptionExample5 {
        public static void main(String[] args) {
            MyNonCloneableClass obj = new MyNonCloneableClass(10);
            try {
                // This will cause a CloneNotSupportedException because MyNonCloneableClass
                // does not implement the Cloneable interface.
                // obj.clone(); // This line won't even compile unless you cast to Cloneable,
                            // demonstrating compiler checking for cloneability.
                            // Let's use a reflective approach to force it at runtime for demonstration:
                java.lang.reflect.Method cloneMethod = Object.class.getDeclaredMethod("clone");
                cloneMethod.setAccessible(true);
                Object clonedObj = cloneMethod.invoke(obj);
                System.out.println("Cloned object value: " + ((MyNonCloneableClass)clonedObj).value);
            } catch (java.lang.reflect.InvocationTargetException e) {
                if (e.getTargetException() instanceof CloneNotSupportedException) {
                    System.err.println("Cloning not supported for MyNonCloneableClass: " + e.getTargetException().getMessage());
                } else {
                    System.err.println("Other reflection error: " + e.getMessage());
                }
            } catch (Exception e) {
                System.err.println("An unexpected error: " + e.getMessage());
            }
        }
    }
    ```
**Explanation:** The `Object.clone()` method is protected and throws `CloneNotSupportedException`. To use `clone()` on your objects, 
your class must implement the `Cloneable` marker interface and typically override the `clone()` method. 
If you don't implement `Cloneable` and try to clone, this exception is thrown. The example uses reflection to 
bypass compile-time checks for demonstration.

### 5.5 User Defined Exception in JAVA
While Java provides a comprehensive set of built-in exception classes, there are many situations 
where these might not perfectly describe the specific error conditions that can arise in your application. 
This is where **user-defined (or custom) exceptions** come into play.

#### Why Create Custom Exceptions?

* **Meaningful Error Reporting:** Custom exceptions allow you to create exception types that 
are highly relevant to your application's domain. Instead of throwing a generic `RuntimeException` 
or `IOException`, you can throw something like `InvalidAccountException` or `InsufficientFundsException`, 
which immediately tells the developer what went wrong in terms of the business logic.
* **Granular Error Handling:** By having specific exception types, you can catch and handle errors with 
more precision. Different error conditions can lead to different recovery strategies.
* **Encapsulation:** Custom exceptions help encapsulate your application's error conditions, 
making your API clearer and easier to use for other developers.
* **Layered Architecture:** In multi-layered applications, you might want to wrap low-level exceptions 
(like `SQLException`) into higher-level, application-specific exceptions (like `DataAccessException`) 
to hide implementation details from the upper layers.

#### How to Create a User-Defined Exception:

To create a custom exception, you simply need to extend one of the existing Exception classes. The choice depends on whether you want your custom exception to be checked or unchecked.

* **For a Checked Custom Exception:**
    * Extend `java.lang.Exception`.
    * This forces the caller to either catch or declare the exception.
    * Use this for anticipated, recoverable, or business-logic-related errors.
* **For an Unchecked Custom Exception:**
    * Extend `java.lang.RuntimeException`.
    * This does not force the caller to catch or declare the exception.
    * Use this for programming errors, logical flaws, or unrecoverable situations where you want the program to terminate if not caught (e.g., in a framework that converts low-level checked exceptions into higher-level unchecked ones for convenience).

#### Standard Constructors for Custom Exceptions:

It's good practice to provide at least these four constructors for your custom exception classes:

* `MyCustomException()`: Default constructor.
* `MyCustomException(String message)`: Constructor with a detail message.
* `MyCustomException(String message, Throwable cause)`: Constructor with a detail message and a cause (for exception chaining).
* `MyCustomException(Throwable cause)`: Constructor with a cause.

### Example for User Defined Exceptions:

#### Example 1: Creating a Checked Custom Exception (`InsufficientFundsException`)
Imagine a banking application. When a user tries to withdraw more money than they have, this is an exceptional but anticipated scenario.

    ```java
    // 1. Define the custom checked exception
    class InsufficientFundsException extends Exception {
        private double currentBalance;
        private double withdrawalAmount;

        public InsufficientFundsException(String message, double currentBalance, double withdrawalAmount) {
            super(message); // Call the parent Exception constructor
            this.currentBalance = currentBalance;
            this.withdrawalAmount = withdrawalAmount;
        }

        public InsufficientFundsException(String message) {
            super(message);
        }

        public InsufficientFundsException(Throwable cause) {
            super(cause);
        }

        public InsufficientFundsException(String message, Throwable cause) {
            super(message, cause);
        }

        // Optional: Add getters to provide more context about the exception
        public double getCurrentBalance() {
            return currentBalance;
        }

        public double getWithdrawalAmount() {
            return withdrawalAmount;
        }
    }
    ```
