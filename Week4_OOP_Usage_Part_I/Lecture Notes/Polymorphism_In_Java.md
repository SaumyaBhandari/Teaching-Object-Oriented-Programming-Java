# Polymorphism in Java

---

## What is Polymorphism?

**Polymorphism** means **"many forms."** In Java, it allows one object to behave in **different ways** depending on how it's used.

Think of a person:
- At home: They are a parent
- At office: They are an employee
- At school: They are a student

Same person, different roles. This is polymorphism.

---

## Why is Polymorphism Useful?

- **Flexible code**
- **Code reusability**
- Helps write **cleaner and scalable** programs
- Makes your program **more dynamic**

---

## Types of Polymorphism in Java

1. **Compile-time Polymorphism** (also called **Static Binding** or **Method Overloading**)
2. **Run-time Polymorphism** (also called **Dynamic Binding** or **Method Overriding**)

---

## 1. Compile-time Polymorphism (Method Overloading)

**Method Overloading** means **multiple methods with the same name** but **different parameters** in the **same class**.

### Rules:
- Same method name
- Different number or type of parameters
- Return type can be same or different

### Example:

```java
class Printer {

    void print() {
        System.out.println("Printing nothing");
    }

    void print(String text) {
        System.out.println("Printing text: " + text);
    }

    void print(int number) {
        System.out.println("Printing number: " + number);
    }

    void print(String text, int copies) {
        System.out.println("Printing " + copies + " copies of: " + text);
    }
}
```

### Main class:

```java
public class TestOverloading {
    public static void main(String[] args) {
        Printer p = new Printer();
        p.print();
        p.print("Hello");
        p.print(100);
        p.print("Hello", 3);
    }
}
```

---

## 2. Run-time Polymorphism (Method Overriding)

**Method Overriding** happens when a subclass provides a **new version** of a method that it inherits from the superclass.

### Rules:
- Same method name
- Same parameters
- Subclass overrides the parent method
- Use `@Override` annotation

---

### Example:

```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks.");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meows.");
    }
}
```

### Main class:

```java
public class TestOverriding {
    public static void main(String[] args) {
        Animal a;

        a = new Dog();
        a.makeSound();  // Dog barks.

        a = new Cat();
        a.makeSound();  // Cat meows.
    }
}
```

### Output:

```
Dog barks.
Cat meows.
```

---

## Real-world Analogy: Remote Control

```java
class Remote {
    void pressButton() {
        System.out.println("Generic remote: Button pressed");
    }
}

class TVRemote extends Remote {
    @Override
    void pressButton() {
        System.out.println("TV remote: Power toggled");
    }
}

class ACRemote extends Remote {
    @Override
    void pressButton() {
        System.out.println("AC remote: AC turned ON/OFF");
    }
}
```

### Main:

```java
public class PolymorphismExample {
    public static void main(String[] args) {
        Remote r;

        r = new TVRemote();
        r.pressButton();

        r = new ACRemote();
        r.pressButton();
    }
}
```

### Output:

```
TV remote: Power toggled
AC remote: AC turned ON/OFF
```

---

## Key Differences

| Feature               | Method Overloading          | Method Overriding              |
|----------------------|-----------------------------|--------------------------------|
| Binding time         | Compile-time                | Run-time                       |
| Parameters           | Must differ                 | Must be same                   |
| Return type          | Can differ                  | Must match or be compatible    |
| Inheritance required | No                          | Yes                            |
| Scope                | Same class                  | Parent-Child class relationship|

---

## Summary

- **Polymorphism** makes code **dynamic, flexible, and reusable**
- **Method Overloading** = Same name, different parameters, same class
- **Method Overriding** = Same method in parent and child, child version runs at runtime
- Useful in real-world programming – reduces code duplication

---

## Practice Exercise

Try implementing:

- A `Shape` class and subclasses like `Circle`, `Rectangle`, `Triangle`
- Override a method called `calculateArea()` in each class
- Call the method using a reference of type `Shape`

---

## What’s Next?

Coming up:

- **Abstract Classes and Abstract Methods**
- **Interfaces in Java**
- **Lambda Expressions**

---

## End of Notes
