### Java I/O Streams (6 hours)

### 6.1 Introduction to Streams, Byte Stream, and Character Stream

In computing, I/O refers to the communication between a computer system
and the outside world. This "outside world" can be anything from a user
typing on a keyboard, data coming from a network, or information being
read from or written to a file on your hard drive.

At the heart of Java's I/O mechanism are **streams**. Think of a stream
as a continuous flow of data. Just like a physical water stream flows
from one point to another, a data stream flows from a source to a
destination. In Java, streams provide a uniform way to handle various
types of I/O operations, whether you're dealing with files, network
connections, or even in-memory data.

**Key Concept: The Unidirectional Nature of Streams**
It's crucial to understand that a stream is inherently unidirectional.
This means data flows in only one direction.

* **Input Streams:** Data flows from a source (e.g., a file, a keyboard)
    into your Java program.
* **Output Streams:** Data flows from your Java program to a destination
    (e.g., a file, a screen, a network connection).

If you need to both read from and write to a source (like a file), you'll
typically use two separate streams: one input stream and one output stream.

Let's dive into the two fundamental types of streams in Java:
**byte streams** and **character streams**.

### 6.1.1 Byte Streams

**Purpose:** Byte streams are designed to handle raw binary data.
This means they read and write data one byte (8 bits) at a time.
They are suitable for any type of data, including text, images,
audio, video, or serialized Java objects. If you're not dealing
with human-readable text and need to preserve the exact binary
representation of data, byte streams are your go-to.

**When to Use Byte Streams:**
* Reading/writing image files (JPEG, PNG, GIF, BMP).
* Reading/writing audio files (MP3, WAV).
* Reading/writing video files (MP4, AVI).
* Handling compressed files (ZIP, GZ).
* Sending/receiving data over network sockets where the data
    format is binary.
* Serializing and deserializing Java objects (which we'll cover
    later with `ObjectInputStream` and `ObjectOutputStream`).

**Core Classes:**
* `InputStream`: This is the abstract superclass for all input
    byte streams. It defines the basic methods for reading bytes.
* `OutputStream`: This is the abstract superclass for all output
    byte streams. It defines the basic methods for writing bytes.

You'll rarely instantiate `InputStream` or `OutputStream` directly.
Instead, you'll use their concrete subclasses, which are specialized
for different data sources/destinations (e.g., `FileInputStream`,
`FileOutputStream`, `BufferedInputStream`, etc.).

**How they work (conceptual):** Imagine a pipeline where individual
bytes are pushed through. There's no interpretation of these bytes
as characters; they are simply treated as numerical values from 0 to 255.

**Examples of Byte Stream Operations (Conceptual - Concrete examples later):**
Let's consider scenarios where byte streams are essential:

* **Copying an image file:** When you copy a `.jpg` file from one
    location to another, you're essentially reading its binary content
    byte by byte from the source and writing it byte by byte to the
    destination. The program doesn't need to understand that these
    bytes form an image; it just copies the raw data.
    * **Analogy:** Think of moving a sealed box. You don't care
        what's inside (text, image, etc.); you just move the entire box.
* **Downloading a video:** When your web browser downloads an `.mp4` file,
    it receives a stream of bytes from the server and saves them to your
    local disk. It doesn't interpret the video frames during the download
    process; it just handles the raw binary data.
* **Encrypting/Decrypting data:** Cryptographic algorithms operate on
    raw bytes. So, when you encrypt or decrypt a file, you'd typically
    read its content as a byte stream, process the bytes, and write
    the transformed bytes out.

### 6.1.2 Character Streams

**Purpose:** Character streams are specifically designed to handle text data.
Unlike byte streams, they are aware of **character encodings** (like UTF-8,
UTF-16, ISO-8859-1). This means they automatically handle the conversion
between bytes and characters based on the specified or default character
encoding of the platform. This is crucial because a single character
in modern encodings (like Unicode) can be represented by multiple bytes.
Using character streams prevents issues like truncated characters or
incorrect character interpretation when dealing with text from different languages.

**When to Use Character Streams:**
* Reading/writing plain text files (`.txt`, `.csv`, `.log`).
* Reading/writing configuration files (`.properties`, `.xml`, `.json` - when treated as text).
* Working with string data.
* Reading user input from the console.
* Writing output to the console.
* Working with internationalized text.

**Core Classes:**
* `Reader`: This is the abstract superclass for all input character streams.
    It defines the basic methods for reading characters.
* `Writer`: This is the abstract superclass for all output character streams.
    It defines the basic methods for writing characters.

Similar to byte streams, you'll use concrete subclasses of `Reader` and `Writer`
(e.g., `FileReader`, `FileWriter`, `BufferedReader`, `PrintWriter`, etc.).

**How they work (conceptual):** Instead of `read()` returning an `int`
representing a byte, `read()` for a `Reader` returns an `int` representing
a Unicode character (0-65535). They manage the underlying byte-to-character
conversion for you.

**Examples of Character Stream Operations (Conceptual - Concrete examples later):**
Let's consider scenarios where character streams are essential:

* **Reading a configuration file:** If you have a `config.properties` file
    with settings like `username=admin` and `password=secret`, you'd use a
    character stream to read these lines, ensuring that characters like `=`
    are correctly interpreted.
    * **Analogy:** Think of reading a letter. You don't care about the
        ink patterns on the paper; you care about the words and sentences
        they form. The character stream handles the "ink-to-word" translation.
* **Displaying text on the console:** When your `System.out.println()`
    method displays text, it's ultimately using a character stream
    (`PrintStream` internally wraps a `Writer`) to convert your Java `String`
    characters into bytes that the console can understand and display.
* **Processing user input:** When a user types "Hello, World!" into the
    console, a character stream handles reading these keystrokes, converting
    the underlying byte representations into actual characters for your
    program to process as a `String`.
* **Saving a user's diary entry:** A diary application would use character
    streams to save and load text entries, ensuring all characters,
    including special symbols or characters from different languages,
    are correctly preserved.

### 6.1.3 Why Two Types of Streams? The Importance of Encoding

The existence of both byte and character streams might seem redundant at first,
but it's fundamentally about handling **character encodings**.

* **Byte streams are encoding-agnostic:** They simply move bytes
    without interpreting them. This is perfect for non-textual data.
    If you use a byte stream to read a text file, you'll get the raw bytes,
    and you'd be responsible for knowing and applying the correct character
    encoding to interpret those bytes as characters. If you guess wrong,
    you'll see "mojibake" (garbled characters).
* **Character streams are encoding-aware:** They are designed to deal
    with the complexities of converting bytes to characters and vice-versa.
    When you create a `FileReader` or `FileWriter`, you can optionally specify
    the character encoding. If you don't, it uses the platform's default
    encoding. This abstraction makes text processing much simpler and more robust.

**Illustrative Example (Conceptual - No Code Yet):**
Imagine you have a text file named `my_text.txt` containing the word "你好"
(Nǐ hǎo - "Hello" in Chinese).

* If you read it with a `FileInputStream` (byte stream): You would get a
    sequence of bytes, perhaps `E5 BD A0 E5 BD A0` if the file is UTF-8 encoded.
    Your program would see these as just numbers. To convert them to "你好",
    you'd need to explicitly use a `CharsetDecoder` or `InputStreamReader`
    with UTF-8 encoding.
* If you read it with a `FileReader` (character stream) with UTF-8 encoding specified:
    The `FileReader` would internally handle the byte-to-character conversion.
    When you call `read()` or `readLine()`, you would directly get the characters
    '你' and '好' (or the full string "你好"). The complexity of multi-byte
    characters is hidden from you.

This distinction is a cornerstone of robust Java I/O programming. Always choose
the appropriate stream type for the type of data you are handling.

### 6.2 I/O Class Hierarchy

Java's I/O classes are incredibly rich and organized into a clear hierarchy
within the `java.io` package. Understanding this hierarchy is key to effectively
using the I/O API. All stream classes ultimately descend from four abstract base classes:

* `InputStream` (Abstract Class): The superclass of all input byte streams.
    * **Key methods:**
        * `int read()`: Reads the next byte of data from the input stream.
            Returns -1 if the end of the stream is reached.
        * `int read(byte[] b)`: Reads up to `b.length` bytes of data into an array of bytes.
        * `int read(byte[] b, int off, int len)`: Reads up to `len` bytes of data into an array of bytes,
            starting at offset `off`.
        * `long skip(long n)`: Skips over and discards `n` bytes of data from the input stream.
        * `int available()`: Returns an estimate of the number of bytes that can be read
            (or skipped over) from this input stream without blocking.
        * `void close()`: Closes this input stream and releases any system resources
            associated with the stream.
    * **Common Subclasses (examples):**
        * `FileInputStream`: For reading bytes from a file.
        * `ByteArrayInputStream`: For reading bytes from a byte array in memory.
        * `BufferedInputStream`: Adds buffering functionality for efficiency to another `InputStream`.
        * `DataInputStream`: Reads primitive Java data types in a machine-independent way
            (wraps another `InputStream`).
        * `ObjectInputStream`: Deserializes primitive data and objects (wraps another `InputStream`).
        * `FilterInputStream` (abstract): Superclass for input streams that "filter" (process) data
            as it's read (e.g., `BufferedInputStream`, `DataInputStream`).

* `OutputStream` (Abstract Class): The superclass of all output byte streams.
    * **Key methods:**
        * `void write(int b)`: Writes the specified byte to the output stream.
        * `void write(byte[] b)`: Writes `b.length` bytes from the specified byte array to this output stream.
        * `void write(byte[] b, int off, int len)`: Writes `len` bytes from the specified byte array
            starting at offset `off` to this output stream.
        * `void flush()`: Flushes this output stream and forces any buffered output bytes
            to be written out to the underlying stream.
        * `void close()`: Closes this output stream and releases any system resources
            associated with the stream.
    * **Common Subclasses (examples):**
        * `FileOutputStream`: For writing bytes to a file.
        * `ByteArrayOutputStream`: For writing bytes to a byte array in memory.
        * `BufferedOutputStream`: Adds buffering functionality for efficiency to another `OutputStream`.
        * `DataOutputStream`: Writes primitive Java data types in a machine-independent way
            (wraps another `OutputStream`).
        * `ObjectOutputStream`: Serializes primitive data and objects (wraps another `OutputStream`).
        * `FilterOutputStream` (abstract): Superclass for output streams that "filter" (process) data
            as it's written (e.g., `BufferedOutputStream`, `DataOutputStream`).

* `Reader` (Abstract Class): The superclass of all input character streams.
    * **Key methods:**
        * `int read()`: Reads a single character. Returns -1 if the end of the stream is reached.
        * `int read(char[] cbuf)`: Reads characters into an array.
        * `int read(char[] cbuf, int off, int len)`: Reads characters into a portion of an array.
        * `long skip(long n)`: Skips characters.
        * `boolean ready()`: Tells whether this stream is ready to be read.
        * `void close()`: Closes the stream.
    * **Common Subclasses (examples):**
        * `FileReader`: For reading characters from a file.
        * `BufferedReader`: Adds buffering for efficient reading of characters, often line by line.
        * `InputStreamReader`: A bridge from byte streams to character streams. It decodes bytes
            into characters using a specified charset. (e.g., `new InputStreamReader(System.in)`)
        * `StringReader`: For reading characters from a `String`.

* `Writer` (Abstract Class): The superclass of all output character streams.
    * **Key methods:**
        * `void write(int c)`: Writes a single character.
        * `void write(char[] cbuf)`: Writes an array of characters.
        * `void write(char[] cbuf, int off, int len)`: Writes a portion of an array of characters.
        * `void write(String str)`: Writes a string.
        * `void write(String str, int off, int len)`: Writes a portion of a string.
        * `void flush()`: Flushes the stream.
        * `void close()`: Closes the stream.
    * **Common Subclasses (examples):**
        * `FileWriter`: For writing characters to a file.
        * `BufferedWriter`: Adds buffering for efficient writing of characters.
        * `OutputStreamWriter`: A bridge from character streams to byte streams. It encodes characters
            into bytes using a specified charset. (e.g., `new OutputStreamWriter(System.out)`)
        * `PrintWriter`: Provides convenience methods for writing formatted text (e.g., `println()`, `printf()`).
            Often wraps another `Writer`.
        * `StringWriter`: For writing characters to a `String` buffer.

**Key Design Pattern: Decorator Pattern**
You'll notice a common pattern in the I/O hierarchy: many stream classes
take another stream as a constructor argument. This is the **Decorator Pattern**
in action. It allows you to add functionality to a stream dynamically
without changing its core structure.

**Example:** `BufferedInputStream` takes an `InputStream`. You can combine them
like `new BufferedInputStream(new FileInputStream("mydata.txt"))`. Here,
`FileInputStream` provides the basic file reading, and `BufferedInputStream`
adds the performance benefit of buffering. This is a very powerful and flexible design.

**The `java.io.File` Class:**
While not a stream itself, the `java.io.File` class is fundamentally important
for file manipulation. It represents file and directory pathnames in a
machine-independent way. It does not provide methods for reading or writing
file content directly, but rather for managing the file system itself
(creating, deleting, renaming files/directories, checking existence,
permissions, etc.).

**Key Methods (examples):**
* `File(String pathname)`: Creates a new `File` instance from a pathname string.
* `File(String parent, String child)`: Creates a new `File` instance from a
    parent pathname string and a child pathname string.
* `boolean exists()`: Tests whether the file or directory denoted by this
    abstract pathname exists.
* `boolean isFile()`: Tests whether the file denoted by this abstract pathname
    is a normal file.
* `boolean isDirectory()`: Tests whether the file denoted by this abstract
    pathname is a directory.
* `String getName()`: Returns the name of the file or directory denoted by
    this abstract pathname.
* `String getAbsolutePath()`: Returns the absolute pathname string of this
    abstract pathname.
* `boolean delete()`: Deletes the file or directory denoted by this abstract pathname.
* `boolean mkdir()`: Creates the directory named by this abstract pathname.
* `boolean createNewFile()`: Atomically creates a new, empty file named by this
    abstract pathname if and only if a file with this name does not yet exist.

Understanding this hierarchy and the decorator pattern will empower you to combine
different stream types to achieve complex I/O operations efficiently.

### 6.3 Manipulating File: Using Specific I/O Classes

Now let's get into the practical application of these concepts by manipulating files
using specific I/O classes. Remember, proper resource management (closing streams)
is paramount to prevent resource leaks and potential data corruption. The
`try-with-resources` statement (introduced in Java 7) is the preferred way
to handle stream closing automatically.

### 6.3.1 `FileInputStream`

**Purpose:** `FileInputStream` is a **byte input stream** used for reading raw bytes from a file.
It's suitable for reading non-textual data like images, audio, or any binary
file where character encoding is not a concern.
**Constructors:**
* `FileInputStream(File file)`: Creates an `InputStream` from a `File` object.
* `FileInputStream(String name)`: Creates an `InputStream` from a file path `String`.

**Key Operations:**
* Reading bytes: Use the `read()` methods.
* Closing the stream: Always call `close()` when you're done, ideally in a `finally` block
    or using `try-with-resources`.

### Tons of Examples for `FileInputStream`:

**Example 1: Reading a single byte from a file**
Let's assume you have a file named `single_byte.bin` in your project root with some
binary content (e.g., a single byte with value 65, which is 'A' in ASCII).

```java
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample1 {
    public static void main(String[] args) {
        String fileName = "single_byte.bin"; // Make sure this file exists with some content

        // Using try-with-resources to ensure the stream is closed automatically
        try (FileInputStream fis = new FileInputStream(fileName)) {
            System.out.println("Attempting to read a single byte from: " + fileName);
            int byteRead = fis.read(); // Reads one byte, returns -1 at end of stream

            if (byteRead != -1) {
                System.out.println("Successfully read byte: " + byteRead);
                System.out.println("As character (ASCII): " + (char) byteRead);
            } else {
                System.out.println("End of file reached, no byte read.");
            }
        } catch (IOException e) {
            // Catch specific IOException for file operations
            System.err.println("An I/O error occurred: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- FileInputStreamExample1 Finished ---");
    }
}
```
**To run this:** Create a file named `single_byte.bin` in your project directory.
You can put any character in it, for example, just the letter `A`.
When you run the code, it will read the ASCII value of 'A' (which is 65).

**Example 2: Reading a small file byte by byte and printing its integer value**
This example demonstrates reading bytes one at a time until the end of the file.

```java
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample2 {
    public static void main(String[] args) {
        String fileName = "my_data.txt"; // Create this file with some text like "Hello World!"

        System.out.println("Reading bytes from: " + fileName);
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int byteRead;
            int count = 0;
            // Loop continues as long as read() returns a byte (not -1 for end of stream)
            while ((byteRead = fis.read()) != -1) {
                System.out.print(byteRead + " "); // Print the integer value of the byte
                count++;
            }
            System.out.println("\nTotal bytes read: " + count);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- FileInputStreamExample2 Finished ---");
    }
}
```
**To run this:** Create a file named `my_data.txt` with content like `Hello`.
Output might be `72 101 108 108 111`.

**Example 3: Reading a file into a byte array (efficient for small files)**
Reading byte by byte is slow. For better performance, you often read into a byte array.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File; // Import File class

public class FileInputStreamExample3 {
    public static void main(String[] args) {
        String fileName = "large_text.txt"; // Create a larger text file, e.g., a paragraph
        // Create a File object to get its length
        File file = new File(fileName);

        if (!file.exists()) {
            System.err.println("Error: File '" + fileName + "' does not exist. Please create it.");
            return;
        }

        System.out.println("Reading file into a byte array from: " + fileName);
        try (FileInputStream fis = new FileInputStream(file)) {
            // Create a byte array with the exact size of the file
            // Note: This is good for small to medium files. For very large files,
            // you'd read in chunks.
            byte[] buffer = new byte[(int) file.length()];

            // Read all bytes from the file into the buffer
            int bytesRead = fis.read(buffer);

            System.out.println("Bytes actually read: " + bytesRead);
            System.out.println("Content (as characters, assuming default encoding):");
            // Convert bytes to String (be aware of encoding issues if this is not plain ASCII/UTF-8)
            System.out.println(new String(buffer));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- FileInputStreamExample3 Finished ---");
    }
}
```
**To run this:** Create a file named `large_text.txt` and put some multi-line text in it.

**Example 4: Reading a file in chunks using a buffer (for large files)**
For very large files, loading the entire file into memory (as in Example 3)
might cause `OutOfMemoryError`. Instead, you read it in fixed-size chunks.

```java
import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamExample4 {
    public static void main(String[] args) {
        String fileName = "very_large_file.bin"; // Imagine this is a large binary file (e.g., a video)
        int bufferSize = 4096; // 4KB buffer

        System.out.println("Reading file in chunks from: " + fileName);
        try (FileInputStream fis = new FileInputStream(fileName)) {
            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            long totalBytesRead = 0;

            // Read bytes into the buffer until no more bytes are available
            while ((bytesRead = fis.read(buffer)) != -1) {
                // Process the 'bytesRead' number of bytes from the 'buffer' array
                // For demonstration, we'll just print a message
                System.out.println("Read " + bytesRead + " bytes. Total: " + (totalBytesRead += bytesRead));
                // In a real application, you'd process/write these bytes here
                // For instance, if copying, you'd write them to an OutputStream.
            }
            System.out.println("Finished reading file. Total bytes processed: " + totalBytesRead);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- FileInputStreamExample4 Finished ---");
    }
}
```
**To run this:** Create a file named `very_large_file.bin`. You can make it large
by copying content multiple times, or by programmatically writing a large amount
of random data to it (e.g., 10MB of random bytes).

### 6.3.2 `FileOutputStream`

**Purpose:** `FileOutputStream` is a **byte output stream** used for writing raw bytes to a file.
It's suitable for writing non-textual data, or when you explicitly want to
control the byte representation of text data.
**Constructors:**
* `FileOutputStream(File file)`: Creates an `OutputStream` to write to a `File` object.
    Overwrites existing file by default.
* `FileOutputStream(File file, boolean append)`: Creates an `OutputStream`.
    If `append` is true, bytes will be written to the end of the file rather
    than overwriting it.
* `FileOutputStream(String name)`: Creates an `OutputStream` to write to a
    file path `String`. Overwrites existing file by default.
* `FileOutputStream(String name, boolean append)`: Creates an `OutputStream`.
    If `append` is true, bytes will be written to the end of the file rather
    than overwriting it.

**Key Operations:**
* Writing bytes: Use the `write()` methods.
* Flushing the stream: Call `flush()` to ensure any buffered data is written
    to the underlying file system.
* Closing the stream: Always call `close()` when you're done, ideally in a `finally` block
    or using `try-with-resources`.

### Tons of Examples for `FileOutputStream`:

**Example 1: Writing a single byte to a file (overwriting)**
This demonstrates the simplest write operation.

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample1 {
    public static void main(String[] args) {
        String fileName = "output.bin";
        int byteToWrite = 65; // ASCII for 'A'

        System.out.println("Writing a single byte to: " + fileName);
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(byteToWrite); // Writes the byte (65) to the file
            System.out.println("Successfully wrote byte " + byteToWrite + " to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- FileOutputStreamExample1 Finished ---");
    }
}
```
**To run this:** After execution, check the `output.bin` file. It will contain a single
character 'A'. If the file already existed, its content will be replaced.

**Example 2: Writing a byte array to a file (overwriting)**
More practical for writing larger chunks of binary data.

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample2 {
    public static void main(String[] args) {
        String fileName = "byte_array_output.bin";
        // Convert a String to bytes using default platform encoding
        String text = "Hello from FileOutputStream!";
        byte[] data = text.getBytes(); // Important: choose appropriate encoding for real text

        System.out.println("Writing a byte array to: " + fileName);
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(data); // Writes the entire byte array to the file
            System.out.println("Successfully wrote " + data.length + " bytes to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- FileOutputStreamExample2 Finished ---");
    }
}
```
**To run this:** Check `byte_array_output.bin`. It will contain the text "Hello from FileOutputStream!".

**Example 3: Appending bytes to an existing file**
If you don't want to overwrite, use the append constructor.

```java
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamExample3 {
    public static void main(String[] args) {
        String fileName = "append_output.txt";
        String firstLine = "This is the first line.\n";
        String secondLine = "This is the appended line.\n";

        // First write (overwriting if exists)
        System.out.println("Writing initial content to: " + fileName);
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(firstLine.getBytes());
            System.out.println("Initial content written.");
        } catch (IOException e) {
            System.err.println("Error writing initial content: " + e.getMessage());
        }

        // Second write (appending)
        System.out.println("Appending content to: " + fileName);
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) { // 'true' for append mode
            fos.write(secondLine.getBytes());
            System.out.println("Appended content written.");
        } catch (IOException e) {
            System.err.println("Error appending content: " + e.getMessage());
        }
        System.out.println("\n--- FileOutputStreamExample3 Finished ---");
    }
}
```
**To run this:** After the first run, `append_output.txt` will contain "This is the first line.".
After the second run, it will contain both lines.

**Example 4: Copying a file (combining FileInputStream and FileOutputStream)**
A classic example demonstrating both input and output byte streams.

```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyExample {
    public static void main(String[] args) {
        String sourceFile = "input_image.jpg"; // Replace with an actual image file in your project
        String destinationFile = "copied_image.jpg";
        int bufferSize = 8192; // 8KB buffer for efficient copying

        System.out.println("Copying file from " + sourceFile + " to " + destinationFile);

        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            long totalBytesCopied = 0;

            // Read from source, write to destination until end of file
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead); // Write only the bytes actually read
                totalBytesCopied += bytesRead;
            }
            System.out.println("File copied successfully. Total bytes: " + totalBytesCopied);

        } catch (IOException e) {
            System.err.println("Error during file copy: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- FileCopyExample Finished ---");
    }
}
```
**To run this:** Place an image file (e.g., `test.jpg`) in your project directory
and rename `input_image.jpg` or update the `sourceFile` variable.
After running, a `copied_image.jpg` will be created.

### 6.3.3 `DataInputStream`

**Purpose:** `DataInputStream` is a **filter stream** that allows you to read primitive Java data types
(like `int`, `double`, `boolean`, `String` in a modified UTF-8 format) in a
machine-independent way. It wraps an underlying `InputStream` (e.g., `FileInputStream`).
This means that regardless of the architecture where the data was written,
`DataInputStream` will read it correctly on another architecture.
**Constructors:**
* `DataInputStream(InputStream in)`: Creates a `DataInputStream` that uses the
    specified underlying `InputStream`.

**Key Operations:**
* Reads data according to the Java standard for data representation.
* Each `readXxx()` method corresponds to a `writeXxx()` method in `DataOutputStream`.
* Crucially, the order of reading must exactly match the order of writing.
    If you write an `int` then a `double`, you must read an `int` then a `double`.

### Tons of Examples for `DataInputStream`:

**Example 1: Reading primitive data types from a file (must be written by DataOutputStream)**
This example reads data that was previously written by a `DataOutputStream`.

```java
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamExample1 {
    public static void main(String[] args) {
        String fileName = "data_types.bin";
        System.out.println("Attempting to read primitive data types from: " + fileName);

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            // The order of reading MUST match the order of writing from DataOutputStreamExample1
            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            boolean booleanValue = dis.readBoolean();
            String stringValue = dis.readUTF(); // Reads a String in modified UTF-8 format

            System.out.println("Read int: " + intValue);
            System.out.println("Read double: " + doubleValue);
            System.out.println("Read boolean: " + booleanValue);
            System.out.println("Read String: " + stringValue);

        } catch (IOException e) {
            System.err.println("Error reading data types from file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- DataInputStreamExample1 Finished ---");
    }
}
```

### Example 2: Reading multiple records of data
Demonstrates reading a structured set of data repeatedly.

```java
import java.io.DataInputStream;
import java.io.EOFException; // Important for checking end of file with DataInputStream
import java.io.FileInputStream;
import java.io.IOException;

public class DataInputStreamExample2 {
    public static void main(String[] args) {
        String fileName = "multiple_records.dat"; // This file should be created by DataOutputStreamExample2
        System.out.println("Reading multiple records from: " + fileName);

        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (true) { // Loop indefinitely until EOFException
                try {
                    String name = dis.readUTF();
                    int age = dis.readInt();
                    double height = dis.readDouble();

                    System.out.println("Record: Name=" + name + ", Age=" + age + ", Height=" + height);
                } catch (EOFException e) {
                    // This exception is thrown when attempting to read beyond the end of the stream
                    System.out.println("Reached end of file.");
                    break; // Exit the loop
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading records from file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- DataInputStreamExample2 Finished ---");
    }
}
```
**To run this:** This example depends on a file created by `DataOutputStreamExample2`.
Run that example first. `EOFException` is crucial for `DataInputStream` because
`readXxx()` methods don't return -1 for end of stream; they throw `EOFException`.

### 6.3.4 `DataOutputStream`

**Purpose:** `DataOutputStream` is a **filter stream** that allows you to write primitive
Java data types to an underlying output stream (e.g., `FileOutputStream`) in a
portable way. The data format is specified by Java and ensures that the data
can be read back correctly by a `DataInputStream` on any Java platform,
regardless of its underlying hardware or operating system.

**Constructors:**
* `DataOutputStream(OutputStream out)`: Creates a `DataOutputStream` that uses the
    specified underlying `OutputStream`.

**Key Operations:**
* Writes data in a format readable by `DataInputStream`.
* Each `writeXxx()` method writes the corresponding primitive type.
* `writeUTF(String s)`: Writes a string in modified UTF-8 format.
    This is specifically designed for interoperability between Java versions.

### Tons of Examples for `DataOutputStream`:

**Example 1: Writing primitive data types to a file**
This example demonstrates writing various primitive data types.

```java
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample1 {
    public static void main(String[] args) {
        String fileName = "data_types.bin";
        System.out.println("Attempting to write primitive data types to: " + fileName);

        int intValue = 12345;
        double doubleValue = 9876.54321;
        boolean booleanValue = true;
        String stringValue = "Hello, Data Stream!";
        String unicodeString = "你好 Java!"; // Contains Unicode characters

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(intValue);
            dos.writeDouble(doubleValue);
            dos.writeBoolean(booleanValue);
            dos.writeUTF(stringValue);
            dos.writeUTF(unicodeString); // UTF-8 handling for Strings

            System.out.println("Successfully wrote data types to " + fileName);
            System.out.println("Wrote int: " + intValue);
            System.out.println("Wrote double: " + doubleValue);
            System.out.println("Wrote boolean: " + booleanValue);
            System.out.println("Wrote String: " + stringValue);
            System.out.println("Wrote Unicode String: " + unicodeString);

        } catch (IOException e) {
            System.err.println("Error writing data types to file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- DataOutputStreamExample1 Finished ---");
    }
}
```
**To run this:** After execution, `data_types.bin` will contain the binary representation
of these values. You won't be able to read it directly as text.
This file is then ready to be read by `DataInputStreamExample1`.

**Example 2: Writing multiple records of data**
This is common for storing structured but non-object data.

```java
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamExample2 {
    public static void main(String[] args) {
        String fileName = "multiple_records.dat";
        System.out.println("Writing multiple records to: " + fileName);

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            // Record 1
            dos.writeUTF("Alice");
            dos.writeInt(30);
            dos.writeDouble(1.65);

            // Record 2
            dos.writeUTF("Bob");
            dos.writeInt(24);
            dos.writeDouble(1.80);

            // Record 3
            dos.writeUTF("Charlie");
            dos.writeInt(35);
            dos.writeDouble(1.72);

            System.out.println("Successfully wrote multiple records to " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing records to file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- DataOutputStreamExample2 Finished ---");
    }
}
```
**To run this:** After execution, `multiple_records.dat` will contain the binary data
for three records. This file is then ready to be read by `DataInputStreamExample2`.

### 6.3.5 `ObjectInputStream`

**Purpose:** `ObjectInputStream` is a specialized input stream that allows you to
read (deserialize) Java objects and primitive data that were previously written
to an `OutputStream` using `ObjectOutputStream`. This process is known as **deserialization**.
For an object to be deserialized, its class must implement the `java.io.Serializable` interface.

**Constructors:**
* `ObjectInputStream(InputStream in)`: Creates an `ObjectInputStream` that reads
    from the specified `InputStream`.

**Key Operations:**
* `Object readObject()`: Reads an object from the `ObjectInputStream`.
    This method can throw `ClassNotFoundException` if the class of a serialized object
    cannot be found.
* Reads primitive data types using methods like `readInt()`, `readDouble()`, etc.,
    similar to `DataInputStream`.

**Important Considerations for Serialization/Deserialization:**
* `Serializable` Interface: Only objects whose classes implement `java.io.Serializable`
    can be serialized/deserialized. This is a marker interface, meaning it has no
    methods to implement; it just "marks" a class as being eligible for serialization.
* `serialVersionUID`: It is highly recommended to declare a
    `private static final long serialVersionUID` in your `Serializable` classes.
    This UID is used during deserialization to ensure that the sender and receiver
    of a serialized object have loaded classes for that object that are compatible
    with respect to serialization. If a class changes its structure (e.g., adds a
    new field) and the `serialVersionUID` is not explicitly set, deserialization
    of old objects might fail with an `InvalidClassException`.
* `transient` Keyword: Fields marked `transient` are not serialized. This is useful
    for sensitive data (like passwords) or data that can be recomputed.
* `static` Fields: `static` fields are not part of an object's state and are
    therefore not serialized.
* Circular References: `ObjectInputStream` and `ObjectOutputStream` handle circular
    references correctly (e.g., Object A references Object B, which in turn references
    Object A). They serialize and deserialize the objects only once.

### Tons of Examples for `ObjectInputStream`:

**Example 1: Deserializing a simple object**
First, we need a `Serializable` class.

```java
import java.io.Serializable;

// Make sure to compile this class before running ObjectInputStreamExample1
class Person implements Serializable {
    // Recommended for version compatibility
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private transient String password; // Will NOT be serialized

    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    // Getters for demonstration
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPassword() { return password; } // Will be null after deserialization

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", password='" + password + "'}";
    }
}
```
Now, the deserialization example:

```java
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class ObjectInputStreamExample1 {
    public static void main(String[] args) {
        String fileName = "person_object.ser"; // This file should be created by ObjectOutputStreamExample1
        System.out.println("Attempting to deserialize a Person object from: " + fileName);

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Read the object. A cast is necessary as readObject returns Object.
            Person deserializedPerson = (Person) ois.readObject();

            System.out.println("Deserialized Person: " + deserializedPerson);
            System.out.println("Name: " + deserializedPerson.getName());
            System.out.println("Age: " + deserializedPerson.getAge());
            System.out.println("Password (transient, should be null/default): " + deserializedPerson.getPassword()); // Will be null

        } catch (IOException e) {
            System.err.println("I/O error during deserialization: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found during deserialization: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- ObjectInputStreamExample1 Finished ---");
    }
}
```
**To run this:** First, compile the `Person` class. Then, run `ObjectOutputStreamExample1`
to create `person_object.ser`. Finally, run `ObjectInputStreamExample1`.
Notice that the password field is `null` after deserialization because it was marked `transient`.

**Example 2: Deserializing multiple objects from a file**
You can serialize and deserialize multiple objects sequentially.

```java
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.EOFException; // For handling end of stream when reading multiple objects

public class ObjectInputStreamExample2 {
    public static void main(String[] args) {
        String fileName = "multiple_people.ser"; // This file should be created by ObjectOutputStreamExample2
        System.out.println("Attempting to deserialize multiple Person objects from: " + fileName);

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) { // Loop until EOFException
                try {
                    Person person = (Person) ois.readObject();
                    System.out.println("Deserialized: " + person);
                } catch (EOFException e) {
                    System.out.println("Reached end of file of objects.");
                    break; // Exit loop when no more objects
                }
            }
        } catch (IOException e) {
            System.err.println("I/O error during deserialization: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found during deserialization: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- ObjectInputStreamExample2 Finished ---");
    }
}
```
**To run this:** Run `ObjectOutputStreamExample2` first to create `multiple_people.ser`.
Then, run `ObjectInputStreamExample2`.

**Example 3: Deserializing primitive data along with objects**
`ObjectInputStream` also has methods to read primitive data, just like `DataInputStream`.

```java
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class ObjectInputStreamExample3 {
    public static void main(String[] args) {
        String fileName = "data_and_object.ser"; // Created by ObjectOutputStreamExample3
        System.out.println("Attempting to deserialize data and object from: " + fileName);

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // Read primitive data first (order matters!)
            String message = ois.readUTF();
            int count = ois.readInt();
            double version = ois.readDouble();

            // Then read the object
            Person person = (Person) ois.readObject();

            System.out.println("Deserialized Message: " + message);
            System.out.println("Deserialized Count: " + count);
            System.out.println("Deserialized Version: " + version);
            System.out.println("Deserialized Person: " + person);

        } catch (IOException e) {
            System.err.println("I/O error during deserialization: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found during deserialization: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- ObjectInputStreamExample3 Finished ---");
    }
}
```
**To run this:** Run `ObjectOutputStreamExample3` first to create `data_and_object.ser`.
Then, run `ObjectInputStreamExample3`.

### 6.3.6 `ObjectOutputStream`

**Purpose:** `ObjectOutputStream` is a specialized output stream that allows you to
write (serialize) Java objects and primitive data to an underlying `OutputStream`
(e.g., `FileOutputStream`). This process is known as **serialization**.
Serialization converts an object's state into a sequence of bytes, which can then
be stored in a file or transmitted over a network.

**Constructors:**
* `ObjectOutputStream(OutputStream out)`: Creates an `ObjectOutputStream` that writes
    to the specified `OutputStream`.

**Key Operations:**
* `void writeObject(Object obj)`: Writes an object to the `ObjectOutputStream`.
    The object must implement `java.io.Serializable`.
* Writes primitive data types using methods like `writeInt()`, `writeDouble()`, etc.,
    similar to `DataOutputStream`.
* `void flush()`: Flushes the stream.
* `void close()`: Closes the stream.

### Tons of Examples for `ObjectOutputStream`:

**Example 1: Serializing a simple object**
Ensure the `Person` class (from 6.3.5 Example 1) is compiled.

```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ObjectOutputStreamExample1 {
    public static void main(String[] args) {
        String fileName = "person_object.ser";
        System.out.println("Attempting to serialize a Person object to: " + fileName);

        // Create a Person object
        Person person1 = new Person("Alice Smith", 30, "securePass123");

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Write the object to the stream
            oos.writeObject(person1);

            System.out.println("Person object serialized successfully: " + person1);
            System.out.println("Check the file: " + fileName);

        } catch (IOException e) {
            System.err.println("I/O error during serialization: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- ObjectOutputStreamExample1 Finished ---");
    }
}
```
**To run this:** Run the example. It will create `person_object.ser`.
This file is then ready to be deserialized by `ObjectInputStreamExample1`.

**Example 2: Serializing multiple objects to a file**
You can write multiple objects to the same serialization stream.

```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ObjectOutputStreamExample2 {
    public static void main(String[] args) {
        String fileName = "multiple_people.ser";
        System.out.println("Attempting to serialize multiple Person objects to: " + fileName);

        Person personA = new Person("Alice", 25, "passA");
        Person personB = new Person("Bob", 40, "passB");
        Person personC = new Person("Carol", 35, "passC");

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(personA);
            oos.writeObject(personB);
            oos.writeObject(personC);

            System.out.println("Multiple Person objects serialized successfully.");
            System.out.println("Serialized: " + personA);
            System.out.println("Serialized: " + personB);
            System.out.println("Serialized: " + personC);

        } catch (IOException e) {
            System.err.println("I/O error during serialization: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- ObjectOutputStreamExample2 Finished ---");
    }
}
```
**To run this:** Run the example. It will create `multiple_people.ser`.
This file is then ready to be deserialized by `ObjectInputStreamExample2`.

**Example 3: Serializing primitive data along with objects**
You can mix primitive data and objects in the same stream.
Remember the order is crucial for deserialization.

```java
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class ObjectOutputStreamExample3 {
    public static void main(String[] args) {
        String fileName = "data_and_object.ser";
        System.out.println("Attempting to serialize primitive data and an object to: " + fileName);

        String message = "System Configuration Data";
        int count = 5;
        double version = 1.0;
        Person admin = new Person("System Admin", 99, "admin_secret");

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // Write primitive data first
            oos.writeUTF(message);
            oos.writeInt(count);
            oos.writeDouble(version);

            // Then write the object
            oos.writeObject(admin);

            System.out.println("Primitive data and object serialized successfully.");
            System.out.println("Wrote Message: " + message);
            System.out.println("Wrote Count: " + count);
            System.out.println("Wrote Version: " + version);
            System.out.println("Wrote Person: " + admin);

        } catch (IOException e) {
            System.err.println("I/O error during serialization: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("\n--- ObjectOutputStreamExample3 Finished ---");
    }
}
```
**To run this:** Run the example. It will create `data_and_object.ser`.
This file is then ready to be deserialized by `ObjectInputStreamExample3`.

**Example 4: Demonstrating transient keyword (in conjunction with Person class from 6.3.5)**
This example simply re-iterates how transient fields are handled.

```java
// This example mainly shows the effect of transient during serialization/deserialization.
// The Person class definition already includes 'transient String password;'.
// Just run ObjectOutputStreamExample1 and ObjectInputStreamExample1 and observe the output.

public class TransientKeywordDemo {
    public static void main(String[] args) {
        System.out.println("Refer to ObjectOutputStreamExample1 and ObjectInputStreamExample1.");
        System.out.println("The 'password' field in the 'Person' class is marked as transient.");
        System.out.println("When a 'Person' object is serialized, the 'password' field's value is not saved.");
        System.out.println("When the object is deserialized, the 'password' field will be null (its default value for objects).");
        System.out.println("\n--- TransientKeywordDemo Finished ---");
    }
}
```
This concludes our lecture on Java I/O Streams.
Remember to practice these concepts by writing your own programs
and experimenting with different scenarios. Understanding streams
is fundamental for any serious Java developer!