
# Inheritance in Java

Inheritance is one of the four main pillars of Object-Oriented Programming (OOP). It allows a class to acquire the properties and behaviors (methods) of another class. In Java, this is implemented using the `extends` keyword.

---

## 1. What is Inheritance?

**Definition**: Inheritance is a mechanism where a new class (subclass/child) is derived from an existing class (superclass/parent). The subclass inherits all non-private data members and methods from the superclass.

### Example Analogy:

Think of real-world hierarchy:

- `Animal` is a general class.
    - `Dog`, `Cat`, `Lion` are specific animals. They all inherit basic properties like `eat()`, `sleep()`, `walk()` from `Animal`.
    - `Dog` can further have types like `GoldenRetriever`, `Bulldog`, etc.

```java
class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
    void sleep() {
        System.out.println("This animal sleeps...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}

public class TestInheritance {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();      // inherited
        d.sleep();    // inherited
        d.bark();     // defined in Dog
    }
}
```

---

## 2. Types of Inheritance in Java

### 1. **Single Inheritance**
One subclass inherits from one superclass.

```java
class Animal {
    void eat() { System.out.println("eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("barking..."); }
}
```

### 2. **Multilevel Inheritance**
A class is derived from a class which is also derived from another class.

```java
class Animal {
    void eat() { System.out.println("eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("barking..."); }
}

class Puppy extends Dog {
    void weep() { System.out.println("weeping..."); }
}
```

### 3. **Hierarchical Inheritance**
Multiple subclasses inherit from one superclass.

```java
class Animal {
    void eat() { System.out.println("eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("barking..."); }
}

class Cat extends Animal {
    void meow() { System.out.println("meowing..."); }
}
```

### 4. **Hybrid and Multiple Inheritance (Not Supported)**
Java does not support multiple inheritance with classes due to ambiguity, but it supports it with interfaces.

---

## 3. The `super` Keyword

`super` refers to the immediate parent class object. It can be used to:

- Access parent class methods/variables.
- Call parent class constructor.

### Example: Using `super`

```java
class Animal {
    Animal() {
        System.out.println("Animal is created");
    }
    void eat() { System.out.println("Animal eats"); }
}

class Dog extends Animal {
    Dog() {
        super();  // calls parent class constructor
        System.out.println("Dog is created");
    }

    void eat() {
        super.eat(); // calls parent method
        System.out.println("Dog eats bones");
    }
}
```

Output:
```
Animal is created
Dog is created
Animal eats
Dog eats bones
```

---

## 4. Static Members and Inheritance

- Static methods and variables belong to the class, not the instance.
- Static methods are not overridden.

```java
class Animal {
    static void category() {
        System.out.println("I am an Animal");
    }
}

class Dog extends Animal {
    static void category() {
        System.out.println("I am a Dog");
    }
}

public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.category();  // Output: I am an Animal (method hiding, not overriding)
    }
}
```

---

## 5. Real-World Example with Multiple Breeds

```java
class Animal {
    void sound() { 
        System.out.println("Some sound"); 
    }
}

class Dog extends Animal {
    void sound() { 
        System.out.println("Dog barks"); 
    }
}

class GoldenRetriever extends Dog {
    void origin() {
        System.out.println("Golden Retriever from Scotland");
    }
}

class GermanShepherd extends Dog {
    void origin() {
        System.out.println("German Shepherd from Germany");
    }
}
```

You can keep going to simulate deep hierarchies and demonstrate reuse.

---

## 6. Advantages of Inheritance

- **Code Reusability**: Avoid code duplication.
- **Method Overriding**: Runtime polymorphism.
- **Structure**: Better structure and readability.

---

## 7. Summary

| Concept | Description |
|--------|-------------|
| `extends` | Keyword to inherit a class |
| `super` | Refers to parent class |
| `super()` | Calls parent class constructor |
| Static Members | Are class-level, not overridden |
| Types | Single, Multilevel, Hierarchical (Java supports only these with classes) |

---

## 8. Practice Exercise

1. Create a class `Vehicle` with methods like `start()`, `stop()` and `fuelType()`.
2. Create subclasses `Car`, `Bike`, `Truck` inheriting from `Vehicle`.
3. Add specific behavior in each subclass.
4. Use `super` keyword to call base class methods.
5. Add static method `vehicleCategory()` in `Vehicle` and redefine in subclasses.

---

Happy Coding!
