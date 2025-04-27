### **1. Variables **
**Question:**
Create a Java program that declares and
initializes variables of different types
(integer, double, boolean) and prints them.

**Solution Code:**
```java
public class Variables {
    public static void main(String[] args)
{
        int num1 = 5;
        double num2 = 3.14;
        boolean isTrue = true;

        System.out.println("Integer variable: " + num1);
        System.out.println("Double variable: " + num2);
        System.out.println("Boolean variable: " + isTrue);
    }
}
```

---

### **2. Constants**
**Question:**
Write a Java program that declares
constants for the following values and uses
them in calculations:
- The value of π (pi) as 3.14
- The value of the gravitational constant g
as 9.8

**Solution Code:**
```java
public class Constants {
    public static void main(String[] args) {
        final double PI = 3.14;
        final double GRAVITY = 9.8;

        double circumference = 2 * PI * 5;// Radius of 5 units
        double force = GRAVITY * 2; // Mass of 2 kg

        System.out.println("Circumference:" + circumference);
        System.out.println("Force: " + force);
    }
}
```

---

### **3. Operators**
**Question:**
Create a Java program that uses different
operators to calculate the result of the
expression:
(5 + 3)*(10 - 2) / 4

**Solution Code:**
```java
public class Operators {
    public static void main(String[] args)
{
        int a = 5;
        int b = 3;
        int c = 10;
        int d = 2;

        int result = (a + b) * (c - d) / 4;
        System.out.println("Result: " + result);
    }
}
```

---

### **4. Data Types**
**Question:**
Write a Java program that declares
variables of different data types and
prints their values.

**Solution Code:**
```java
public class DataTypes {
    public static void main(String[] args)
{
        // Declare variables with default initial values
        int intValue = 0; // Default is 0
        double doubleValue = 0.0; // Default is 0.0
        boolean booleanValue = false;

        System.out.println("Integer: " + intValue);
        System.out.println("Double: " + doubleValue);
        System.out.println("Boolean: " + booleanValue);
    }
}
```

---

### **5. Control Statements (Conditionals)**
**Question:**
Write a Java program that uses an if-else
statement to check whether a number is
positive, negative, or zero.

**Solution Code:**
```java

import java.util.Scanner

public class ControlStatements {
    public static void main(String[] args)
{
        int num = 0;

        System.out.println("Enter a number:");
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();

        if (num > 0) {
            System.out.println("The number is positive.");
        } else if (num < 0) {
            System.out.println("The number is negative.");
        } else {
            System.out.println("The numberis zero.");
        }
    }
}
```

---

### **6. Control Statements (Loops)**
**Question:**
Write a Java program that uses a for loop
to print all even numbers from 1 to 10.

**Solution Code:**
```java
public class ControlStatementsLoops {
    public static void main(String[] args)
{
        System.out.println("Even numbers from 1 to 10:");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}
```

---
### **7. Basic IO Operations**
**Question:**
Write a Java program that:
- Asks the user to enter their name and age using the Scanner class.
- Stores the name as a String and the age as an int.
- Then prints out the name and age in a friendly format.

```java

import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.println("Your name is: " + name);
        System.out.println("Your age is: " + age);

        scanner.close();
    }
}
```
---

