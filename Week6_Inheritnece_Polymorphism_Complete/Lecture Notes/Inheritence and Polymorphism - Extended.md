# Unit IV – Inheritance and Polymorphism

## Dynamic Method Invocation and Runtime Polymorphism

### 1. Dynamic Method Invocation

#### Definition
Dynamic method invocation refers to the process where a call to an overridden method is resolved at runtime rather than compile time. This mechanism allows Java to decide which version of the method to invoke based on the actual object that the reference is pointing to at execution time.

This concept applies when a superclass reference variable is used to refer to a subclass object, and the subclass has overridden a method from the superclass.

#### Key Points
* It only works with method overriding, not overloading.
* The call to the overridden method is resolved during runtime.
* The reference variable must be of the superclass type.
* The object type determines which method gets executed.

#### Example 1: Simple Overridden Method Call
```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    void makeSound() {
        System.out.println("Cat meows");
    }
}

public class Demo {
    public static void main(String[] args) {
        Animal a;

        a = new Dog();  // Animal reference, Dog object
        a.makeSound();  // Output: Dog barks

        a = new Cat();  // Animal reference, Cat object
        a.makeSound();  // Output: Cat meows
    }
}
```

In this example, although the reference type is Animal, the method call depends on the actual object type (Dog or Cat). This is dynamic method invocation.

#### Example 2: With a Superclass Reference Array
```Java

class Shape {
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square extends Shape {
    void draw() {
        System.out.println("Drawing a square");
    }
}

public class DrawingBoard {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Circle();
        shapes[1] = new Square();

        for (Shape s : shapes) {
            s.draw();
        }
        // Output:
        // Drawing a circle
        // Drawing a square
    }
}
```
Here we demonstrate polymorphic behavior in an array of superclass references.

### 2. Runtime Polymorphism
#### Definition
Runtime polymorphism in Java is the ability of the program to decide which method implementation to execute at runtime. It is implemented using method overriding and dynamic method invocation.

Polymorphism allows a single interface to be used with different underlying forms (data types or method implementations).

#### Requirements for Runtime Polymorphism
* Inheritance (a class must extend another class).
* Method overriding (a method in a subclass with the same signature as in the superclass).
* A superclass reference variable must point to a subclass object.
* The method to be executed is decided at runtime.
* The method to be executed is determined by the actual object being referred to, not by the type.

#### Example 1: Runtime Polymorphism in Action

```java

class Vehicle {
    void start() {
        System.out.println("Vehicle starts");
    }
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car starts with key");
    }
}

class Bike extends Vehicle {
    void start() {
        System.out.println("Bike starts with button");
    }
}

public class Transport {
    public static void main(String[] args) {
        Vehicle v;

        v = new Car();
        v.start();  // Output: Car starts with key

        v = new Bike();
        v.start();  // Output: Bike starts with button
    }
}
```

This is a classic example of runtime polymorphism. The reference type is Vehicle, but the actual object decides which start() method is executed.

#### Example 2: Using Polymorphism in Real Scenarios
Consider a payment processing system:

```Java
class Payment {
    void process() {
        System.out.println("Processing generic payment");
    }
}

class CreditCard extends Payment {
    void process() {
        System.out.println("Processing credit card payment");
    }
}

class PayPal extends Payment {
    void process() {
        System.out.println("Processing PayPal payment");
    }
}

public class PaymentSystem {
    public static void processPayment(Payment p) {
        p.process();  // Runtime polymorphism in action
    }

    public static void main(String[] args) {
        Payment p1 = new CreditCard();
        Payment p2 = new PayPal();

        processPayment(p1);  // Output: Processing credit card payment
        processPayment(p2);  // Output: Processing PayPal payment
    }
}
```

This kind of structure is widely used in applications where different classes implement the same interface or extend the same base class, and objects are handled in a uniform manner.

**Incorrect Usage: Not Polymorphism**

```Java

class Printer {
    void print() {
        System.out.println("Printing document");
    }
}

class ColorPrinter extends Printer {
    void printColor() {
        System.out.println("Printing in color");
    }
}

public class TestPrinter {
    public static void main(String[] args) {
        Printer p = new ColorPrinter();
        p.print();         // OK
        // p.printColor(); // Error: Method not found in Printer
    }
}
```
In this case, even though p refers to a ColorPrinter object, the reference is of type Printer, so only methods defined in Printer can be accessed directly. This is not runtime polymorphism because printColor() is not an overridden method.

### Summary
* Dynamic method invocation is the mechanism that enables runtime polymorphism.
* The actual method that gets executed depends on the object type, not the reference type.
* This feature of Java supports flexible design and is the backbone of object-oriented programming.
* Overridden methods allow Java to resolve calls dynamically and implement polymorphic behavior across class hierarchies.