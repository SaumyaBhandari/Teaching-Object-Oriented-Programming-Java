# Encapsulation

Encapsulation is one of the foundational concepts of Object-Oriented Programming (OOP). 

It is the process of wrapping the data (variables) and the code (methods) that manipulates the data into a single unit, typically - data and code is encapsulated inside a class.

Encapsulation is about two things:
1. **Data hiding:** Restricting access to some of the object’s components.
2. **Data access control:** Providing controlled access via public methods (getters and setters).


## 1. What is Encapsulation?

Encapsulation means:
- Binding variables and methods together.
- Preventing direct access to some class components.
- Making data "private" and accessing it via "public" methods.

### Example:

```java
class Student {
    private String name; // Private variable

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
```

Here, `name` is hidden from direct access. It is accessed via `setName()` and `getName()`.

---

## 2. Why is Encapsulation Important?

- **Security**: Sensitive data is hidden from unauthorized access.
- **Maintainability**: Internal code can be modified without affecting other parts.
- **Readability**: Clean structure; separates implementation and interface.
- **Validation**: Values can be validated before being assigned.

---

## 3. Real-World Analogy

Think of a **Bank ATM**:
- You insert a card and press buttons.
- Internally, there are many processes and data.
- You do not know (or need to know) how they work.
- You are only given **controlled access** to perform tasks.

---

## 4. Using Access Modifiers for Encapsulation

Java provides **access specifiers** to restrict access:
- `private` – accessible only within the class.
- `default` – accessible within the same package.
- `protected` – accessible within package and subclasses.
- `public` – accessible from everywhere.

### Table of Access Levels

| Modifier   | Class | Same Package | Subclass | Other Packages |
|------------|-------|--------------|----------|----------------|
| private    | Yes   | No           | No       | No             |
| default    | Yes   | Yes          | No       | No             |
| protected  | Yes   | Yes          | Yes      | No             |
| public     | Yes   | Yes          | Yes      | Yes            |

---

## 5. Example: Encapsulation with All Access Modifiers

```java
public class Demo {
    private int privateVar = 10;
    int defaultVar = 20;
    protected int protectedVar = 30;
    public int publicVar = 40;

    public void printAll() {
        System.out.println(privateVar);
        System.out.println(defaultVar);
        System.out.println(protectedVar);
        System.out.println(publicVar);
    }
}
```

Accessing from another class in the **same package**:

```java
public class Test {
    public static void main(String[] args) {
        Demo d = new Demo();
        // System.out.println(d.privateVar); // Error
        System.out.println(d.defaultVar);   // OK
        System.out.println(d.protectedVar); // OK
        System.out.println(d.publicVar);    // OK
    }
}
```

---

## 6. Full Example: Class with Encapsulation and Validation

```java
class BankAccount {
    private String holderName;
    private double balance;

    public BankAccount(String name, double initialBalance) {
        holderName = name;
        if (initialBalance > 0)
            balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance)
            balance -= amount;
        else
            System.out.println("Invalid withdrawal.");
    }

    public double getBalance() {
        return balance;
    }

    public String getHolderName() {
        return holderName;
    }
}

public class TestAccount {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("John", 1000);
        acc.deposit(500);
        acc.withdraw(300);
        acc.withdraw(2000); // Invalid

        System.out.println("Name: " + acc.getHolderName());
        System.out.println("Balance: " + acc.getBalance());
    }
}
```

---

## 7. Common Pattern: Getters and Setters

Encapsulation heavily relies on **getters and setters**.

### Without Validation:

```java
class Product {
    private String name;
    private double price;

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double p) {
        price = p;
    }

    public double getPrice() {
        return price;
    }
}
```

### With Validation:

```java
public void setPrice(double p) {
    if (p > 0)
        price = p;
    else
        System.out.println("Invalid price");
}
```

---

## 8. Encapsulation and Method Encapsulation

Encapsulation also applies to **methods**, not just variables.

### Private Method Example:

```java
class Logger {
    private void log(String message) {
        System.out.println("[LOG] " + message);
    }

    public void saveToDatabase(String data) {
        // Logic
        log("Data saved: " + data);
    }
}
```

Outside classes **cannot** call `log()` directly.

---

## 9. Encapsulation Across Multiple Classes

### Person.java

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        if (age > 0)
            this.age = age;
    }

    public void introduce() {
        System.out.println("Hi, I'm " + name + " and I'm " + age + " years old.");
    }
}
```

### Main.java

```java
public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Asha", 22);
        p1.introduce();

        // p1.name = "Nina"; // Error: private
    }
}
```

---

## 10. Differences: `private` vs `default` vs `public`

### Default

- No keyword used.
- Accessible only within same package.

```java
class City {
    String name; // default access

    void printName() {
        System.out.println(name);
    }
}
```

### Public

- Accessible anywhere.

```java
public class City {
    public String name;

    public void printName() {
        System.out.println(name);
    }
}
```

---

## 11. Encapsulation Pitfalls

### Direct Access (Wrong):

```java
class BadStudent {
    public String name; // Not recommended
}

public class Main {
    public static void main(String[] args) {
        BadStudent s = new BadStudent();
        s.name = "Lazy way"; // Direct access
    }
}
```

### Proper Way:

```java
class GoodStudent {
    private String name;

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
```

---

## 12. Best Practices

- Make fields `private` or `protected`.
- Use `public` getters and setters.
- Validate inside setters.
- Avoid exposing unnecessary internal logic.
- Keep business logic separate from display logic.

---

## 13. Practice Exercises

1. Create a class `Vehicle` with `brand`, `model`, and `price` fields (private). Add validation in setters.
2. Make a class `User` with `username`, `password`. Allow changing the password only if current password matches.

---

## 14. Conclusion

Encapsulation:
- Is **about protection** and control.
- Keeps class logic **self-contained and modular**.
- Promotes clean and secure Java programming.

Always ask: **Should this be publicly accessible?**
If not, **encapsulate it.**

