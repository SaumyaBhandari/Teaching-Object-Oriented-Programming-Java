### Java Practice Problems: Abstraction, Exception Handling, and Streams

These practice problems are designed to help you master Java concepts including **Abstraction (Abstract Classes & Interfaces)**, **Exception Handling**, **Functional Interfaces & Lambdas**, and **I/O Streams**. Each question now includes clear implementation guidelines, expected methods, field usage, and file/class names for better structure and understanding, emphasizing their interrelations.

### Abstraction & Exception Handling Interrelations

1.  **Abstract Data Source with Error Handling**

    **Instructions:**
    * Create a custom checked exception: `DataSourceReadException`.
    * Create an `abstract` class `AbstractDataSource` with:
        * An `abstract` method `List<String> readData() throws DataSourceReadException;`.
        * A non-abstract method `void closeDataSource() throws IOException;` that prints a closing message (e.g., "Data source closed.").
    * **Interrelation:** `readData()` explicitly declares the custom exception, forcing subclasses to handle it. `closeDataSource()` shows general resource cleanup with `IOException`.

    **File:** `DataSource.java`

2.  **File Data Source Implementation**

    **Instructions:**
    * Create a concrete class `FileDataService` that extends `AbstractDataSource`.
    * Override `readData()` to:
        * Take a `String filePath` as a constructor argument.
        * Read all lines from the specified file using `BufferedReader` and `FileReader`.
        * Utilize a `try-with-resources` statement to ensure the stream is closed automatically.
        * If the file is not found or an I/O error occurs, catch `IOException` and rethrow it as a `DataSourceReadException` (wrapping the original `IOException`).
    * Override `closeDataSource()` to ensure the `FileReader` is properly closed if it was opened outside `try-with-resources`, otherwise just call `super.closeDataSource()`.
    * In a `main` method, instantiate `FileDataService`, call `readData()`, and demonstrate catching `DataSourceReadException`.

    **File:** `FileDataService.java`

3.  **Network Data Source Simulation**

    **Instructions:**
    * Create a custom checked exception: `NetworkConnectionException`.
    * Create a concrete class `NetworkDataService` that extends `AbstractDataSource`.
    * Override `readData()` to:
        * Simulate reading data (e.g., return a fixed `List<String>`).
        * **Interrelation:** Introduce a condition (e.g., based on a constructor parameter like `boolean simulateError`) where it `throw new NetworkConnectionException("Simulated network error during read.");`.
    * Override `closeDataSource()` to simulate closing a network connection.
    * In a `main` method, test `NetworkDataService` both with and without the simulated error, demonstrating catching `DataSourceReadException` (which could wrap `NetworkConnectionException`).

    **File:** `NetworkDataService.java`

### Interfaces & Stream Processing

4.  **Data Transformer Interface**

    **Instructions:**
    * Create an `interface` `DataTransformer` with an `abstract` method:
        * `OutputStream transform(InputStream input) throws IOException;`
        * The method should process data from `input` and return an `OutputStream` containing the transformed data.

    **File:** `DataTransformer.java`

5.  **Uppercase Transformer**

    **Instructions:**
    * Create a class `UppercaseTransformer` that `implements DataTransformer`.
    * Override `transform(InputStream input)`:
        * Read byte by byte from the `input` stream.
        * Convert each byte representing an ASCII character to its uppercase equivalent.
        * Write the transformed bytes to a `ByteArrayOutputStream`.
        * Return the `ByteArrayOutputStream`.
        * **Interrelation:** Catch `IOException` during stream operations and print an error message.

    **File:** `UppercaseTransformer.java`

6.  **File Copy Utility (with Transformation)**

    **Instructions:**
    * Create a class `FileUtil`.
    * Add a `static` method `void copyFileWithTransformation(String sourcePath, String destPath, DataTransformer transformer) throws IOException;`.
    * Inside this method:
        * Use `FileInputStream` for `sourcePath` and `FileOutputStream` for `destPath`.
        * Use `try-with-resources` for both `FileInputStream` and `FileOutputStream`.
        * Pass the `FileInputStream` to the `transformer.transform()` method.
        * Read the transformed data from the `OutputStream` returned by `transform()` and write it to the `FileOutputStream`.
        * **Interrelation:** This problem ties together `FileInputStream`, `FileOutputStream`, `DataTransformer` (interface), and `IOException`.
    * In a `main` method, create a test file, then use `FileUtil.copyFileWithTransformation` with an instance of `UppercaseTransformer` to copy and uppercase the content.

    **File:** `FileUtil.java`

### Functional Interfaces & Lambdas with Streams/Exceptions

7.  **Custom Data Filter Functional Interface**

    **Instructions:**
    * Create a `@FunctionalInterface` named `LineFilter` with a single abstract method:
        * `boolean test(String line) throws FilterException;`
    * Create a custom checked exception: `FilterException`.
    * **Interrelation:** The functional interface declares a checked exception, showing how lambdas might need to handle or rethrow.

    **File:** `LineFilter.java`

8.  **Filtered File Reader with Lambda**

    **Instructions:**
    * Create a class `LogFileReader`.
    * Add a method `List<String> readFilteredLines(String filePath, LineFilter filter) throws IOException, FilterException;`.
    * Inside `readFilteredLines`:
        * Read lines from `filePath` using `BufferedReader` and `FileReader` (with `try-with-resources`).
        * For each line, apply the `filter.test(line)`.
        * If `filter.test()` returns `true`, add the line to a `List<String>`.
        * **Interrelation:** Demonstrates passing a `LineFilter` lambda, which can throw `FilterException`. Handle `IOException` from file reading.
    * In a `main` method:
        * Create a sample log file with various lines (e.g., "INFO: ...", "ERROR: ...", "WARN: ...").
        * Call `readFilteredLines` using a lambda expression for `LineFilter` that filters for "ERROR" lines.
        * Demonstrate catching both `IOException` and `FilterException`.

    **File:** `LogFileReader.java`

9.  **Error Processor with Functional Interface**

    **Instructions:**
    * Create a `@FunctionalInterface` `ExceptionHandler` with a single abstract method:
        * `void handle(Exception e);`
    * Create a class `ProcessManager`.
    * Add a method `void executeProcess(Runnable process, ExceptionHandler handler);`.
    * Inside `executeProcess`, execute the `process.run()`. Wrap it in a `try-catch` block. If any `Exception` occurs during `process.run()`, pass it to `handler.handle(e)`.
    * **Interrelation:** `ExceptionHandler` shows a simple functional interface for error callbacks.
    * In a `main` method:
        * Define a `Runnable` lambda that simulates a task that might throw an `IOException` (e.g., attempting to write to a read-only file).
        * Define an `ExceptionHandler` lambda that prints the exception message.
        * Call `ProcessManager.executeProcess` with these lambdas.

    **File:** `ProcessManager.java`

### Serialization & Deserialization with Exception Handling

10. **Serializable Configuration Object**

    **Instructions:**
    * Create a class `ApplicationConfig` that `implements Serializable`.
    * Include fields: `appName` (String), `version` (double), `maxUsers` (int).
    * Add a `transient` field: `private transient String adminPassword;`.
    * Include a `private static final long serialVersionUID = 1L;`.
    * Implement a constructor and getter methods.

    **File:** `ApplicationConfig.java`

11. **Configuration Manager Interface**

    **Instructions:**
    * Create an `interface` `ConfigurationManager` with the following methods:
        * `void saveConfig(ApplicationConfig config, String filePath) throws ConfigurationException;`
        * `ApplicationConfig loadConfig(String filePath) throws ConfigurationException;`
    * Create a custom checked exception: `ConfigurationException`.
    * **Interrelation:** This interface forces concrete classes to implement serialization/deserialization logic and handle related exceptions.

    **File:** `ConfigurationManager.java`

12. **Binary Configuration Manager**

    **Instructions:**
    * Create a class `BinaryConfigManager` that `implements ConfigurationManager`.
    * Override `saveConfig()`:
        * Use `FileOutputStream` and `ObjectOutputStream` (with `try-with-resources`).
        * Write the `ApplicationConfig` object to the file.
        * **Interrelation:** Catch `IOException` and wrap it in a `ConfigurationException`.
    * Override `loadConfig()`:
        * Use `FileInputStream` and `ObjectInputStream` (with `try-with-resources`).
        * Read the `ApplicationConfig` object from the file.
        * **Interrelation:** Catch `IOException` and `ClassNotFoundException`, wrapping them in `ConfigurationException`.
    * In a `main` method:
        * Create an `ApplicationConfig` object, including a value for the `transient` password.
        * Use `BinaryConfigManager` to save it to a file.
        * Then load it back and print the details, observing the `transient` field's value.
        * Demonstrate catching `ConfigurationException`.

    **File:** `BinaryConfigManager.java`

### Mixed Stream & Exception Scenarios

13. **Data Logging Interface**

    **Instructions:**
    * Create an `interface` `DataLogger` with methods:
        * `void logEntry(String entry) throws LogException;`
        * `void closeLogger() throws IOException;`
    * Create a custom checked exception: `LogException`.

    **File:** `DataLogger.java`

14. **File Logger Implementation**

    **Instructions:**
    * Create a class `FileLogger` that `implements DataLogger`.
    * Include a `FileWriter` (and optionally `BufferedWriter`) as a field.
    * In the constructor, initialize the `FileWriter` with a given `filePath`, allowing append mode.
    * Override `logEntry()` to write the `entry` to the file, followed by a new line.
        * **Interrelation:** Catch `IOException` during writing and rethrow it as a `LogException`.
    * Override `closeLogger()` to close the `FileWriter`.
    * In a `main` method, create a `FileLogger` instance, `logEntry` several messages, and then `closeLogger()`. Demonstrate catching `LogException`.

    **File:** `FileLogger.java`

15. **System Activity Reporter (Abstract with Logging)**

    **Instructions:**
    * Create an `abstract` class `ActivityReporter` with:
        * A `protected` `DataLogger` field.
        * A constructor that accepts a `DataLogger`.
        * An `abstract` method `void generateReport() throws ReportGenerationException;`.
        * A `protected` non-abstract method `void log(String message) throws LogException;` that delegates to the internal `DataLogger`.
    * Create a custom checked exception: `ReportGenerationException`.
    * **Interrelation:** The abstract class integrates the `DataLogger` interface and `LogException`, demonstrating composition and error propagation.

    **File:** `ActivityReporter.java`

16. **User Activity Reporter**

    **Instructions:**
    * Create a concrete class `UserActivityReporter` that extends `ActivityReporter`.
    * Override `generateReport()`:
        * Simulate collecting user activities (e.g., create a `List<String>` of activity descriptions).
        * For each activity, call `log(activityDescription)` using the inherited method.
        * **Interrelation:** Introduce a condition (e.g., if the activity list is empty) to `throw new ReportGenerationException("No user activities to report.");`.
    * In a `main` method:
        * Instantiate `FileLogger`.
        * Instantiate `UserActivityReporter` with the `FileLogger`.
        * Call `generateReport()` and demonstrate catching `ReportGenerationException`.
        * Ensure `FileLogger.closeLogger()` is called in a `finally` block or using `try-with-resources` if you wrap it.

    **File:** `UserActivityReporter.java`

### Data Serialization & Deserialization of Multiple Objects

17. **Student Class (Serializable)**

    **Instructions:**
    * Create a class `Student` that `implements Serializable`.
    * Fields: `studentId` (int), `name` (String), `gpa` (double).
    * Include a `private static final long serialVersionUID = 2L;`.
    * Implement a constructor and `toString()` method.

    **File:** `Student.java`

18. **Student Record Manager**

    **Instructions:**
    * Create a class `StudentRecordManager` with two methods:
        * `void saveStudents(List<Student> students, String filePath) throws StudentRecordException;`
        * `List<Student> loadStudents(String filePath) throws StudentRecordException;`
    * Create a custom checked exception: `StudentRecordException`.
    * Inside `saveStudents()`:
        * Use `FileOutputStream` and `ObjectOutputStream` (with `try-with-resources`).
        * Iterate through the `List<Student>` and `writeObject()` each student.
        * **Interrelation:** Catch `IOException` and wrap it in `StudentRecordException`.
    * Inside `loadStudents()`:
        * Use `FileInputStream` and `ObjectInputStream` (with `try-with-resources`).
        * Use a `while(true)` loop and `ois.readObject()` to read students until an `EOFException` is caught (signaling end of file).
        * **Interrelation:** Catch `IOException`, `ClassNotFoundException`, and `EOFException`, wrapping them in `StudentRecordException`.
    * In a `main` method:
        * Create a `List<Student>`.
        * Use `StudentRecordManager` to save the list to a file.
        * Load the students back from the file and print them.
        * Demonstrate error handling for `StudentRecordException`.

    **File:** `StudentRecordManager.java`