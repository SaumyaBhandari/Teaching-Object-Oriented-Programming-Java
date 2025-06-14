# 1. Introduction to Abstract Classes
An **abstract class** in Java is a special type of class that cannot be directly
created as an object. Its main purpose is to act as a **blueprint** or a **base**
for other classes. It can contain a mix of methods with full implementations
(known as **concrete methods**) and methods without implementations (called
**abstract methods**). The keyword `abstract` is used to declare both abstract
classes and abstract methods.

Think of an abstract class as a partially completed class. It sets certain
expectations for its subclasses by declaring abstract methods, forcing them
to provide specific implementations. At the same time, it can offer common
functionality through concrete methods that all its subclasses can inherit.

# 2. Key Characteristics of Abstract Classes
Let's break down the essential features of abstract classes:

* **Cannot be Instantiated**: You can't create an object (instance) of an
    abstract class using the `new` keyword.
    * Example: `Animal myAnimal = new Animal();` (If `Animal` is abstract,
        this will cause a compile-time error).
* **Can have Abstract Methods**: An **abstract method** is a method declared
    without an implementation (a body). It's marked with the `abstract` keyword
    and ends with a semicolon.
    * Syntax: `public abstract void methodName();`
    * Implication: Any concrete (non-abstract) class that extends an abstract
        class **must** provide an implementation for all inherited abstract
        methods. If a subclass fails to implement all abstract methods, it must
        also be declared `abstract` itself.
* **Can have Concrete (Non-Abstract) Methods**: Abstract classes can contain
    regular methods with full implementations, just like non-abstract classes.
    These methods provide common functionality that all subclasses can inherit
    and use directly or override if needed.
    * Example: In an `Animal` abstract class, `eat()` or `sleep()` methods
        could be concrete, as their basic behavior might be consistent across
        various animals.
* **Can have Constructors**: Abstract classes can have constructors. These
    constructors are invoked when an object of a concrete subclass is created.
    The constructor of the abstract class helps in initializing the common state
    inherited by its subclasses.
    * Example: `super(name);` in a subclass constructor to call the abstract
        superclass's constructor.
* **Can have `final` Methods**: An abstract class can declare methods as `final`.
    A `final` method cannot be overridden by subclasses. This is useful when you
    want to enforce a specific behavior that shouldn't be changed by any subclass.
* **Can have `static` Methods**: Abstract classes can also have static methods.
    These methods belong to the class itself, not to any specific instance, and
    can be called directly using the abstract class name.
* **Cannot be `final`**: An abstract class cannot be declared `final`. If it
    were `final`, it couldn't be extended, which contradicts its primary purpose
    of being a base for inheritance.
* **Requires at least one concrete subclass to be useful**: Since an abstract
    class cannot be instantiated, it's only useful if there's at least one
    concrete subclass that extends it and provides implementations for its
    abstract methods.

# 3. When to Use Abstract Classes
Abstract classes are particularly useful in the following scenarios:

* **Defining a Template Method Pattern**: When you want to define a skeleton
    of an algorithm in a method, deferring some steps to subclasses. The abstract
    class provides the overall structure, and subclasses fill in the specific
    details.
* **Providing a Common Base for Related Classes**: When a group of classes
    share common characteristics and behaviors, but also have some unique aspects
    that need to be defined by each individual class. The abstract class serves
    as the common superclass, holding the shared code.
* **Enforcing Method Implementation**: When you want to ensure that subclasses
    provide an implementation for specific methods. Declaring these methods as
    abstract forces subclasses to override them.
* **Sharing Code Among Subclasses**: When you have common code (concrete
    methods, fields) that can be inherited by multiple subclasses, reducing
    code duplication.

# 4. Syntax of Abstract Classes and Methods

```Java

// Declaring an abstract class
abstract class MyAbstractClass {

    // Instance variables (can be of any access modifier)
    String commonData;
    private int id;

    // Constructor (optional, but useful for initializing common data)
    public MyAbstractClass(String commonData, int id) {
        this.commonData = commonData;
        this.id = id;
        System.out.println("Abstract class constructor called.");
    }

    // Abstract method (no body, ends with semicolon)
    // Must be implemented by concrete subclasses
    public abstract void abstractMethod();

    // Concrete method (with implementation)
    // Can be inherited and/or overridden by subclasses
    public void concreteMethod() {
        System.out.println("This is a concrete method in the abstract class.");
        System.out.println("Common Data: " + commonData + ", ID: " + id);
    }

    // Final method (cannot be overridden by subclasses)
    public final void finalMethod() {
        System.out.println("This is a final method and cannot be overridden.");
    }

    // Static method (belongs to the class, not instances)
    public static void staticMethod() {
        System.out.println("This is a static method in the abstract class.");
    }
}
```

# 5. Detailed Examples
Let's explore several examples to solidify our understanding.

#### Example 1: Geometric Shapes
We want to create a system for geometric shapes. All shapes have an area and a perimeter, but the calculation method varies for each shape.

```Java

// 1. Abstract Class: Shape
abstract class Shape {
    String color;

    // Constructor to initialize common properties
    public Shape(String color) {
        this.color = color;
        System.out.println("Shape constructor called. Color: " + color);
    }

    // Concrete method common to all shapes
    public void displayColor() {
        System.out.println("The shape's color is: " + color);
    }

    // Abstract methods - concrete subclasses must implement these
    public abstract double calculateArea();
    public abstract double calculatePerimeter();

    // Another concrete method (could be overridden)
    public void printInfo() {
        System.out.println("This is a generic shape.");
    }
}


// 2. Concrete Subclass: Circle
class Circle extends Shape {
    double radius;

    public Circle(String color, double radius) {
        super(color); // Call to abstract superclass constructor
        this.radius = radius;
        System.out.println("Circle created with radius: " + radius);
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    // Circle-specific method
    public void describeCircle() {
        System.out.println("I am a circle with radius " + radius);
    }

    @Override
    public void printInfo() {
        super.printInfo(); // Call superclass method
        System.out.println("Specifically, this is a circle.");
    }
}

// 3. Concrete Subclass: Rectangle
class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
        System.out.println("Rectangle created with length: " + length + ", width: " + width);
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    // Rectangle-specific method
    public void describeRectangle() {
        System.out.println("I am a rectangle with dimensions " + length + "x" + width);
    }
}

// 4. Main Class to demonstrate
public class ShapeDemo {
    public static void main(String[] args) {
        // Cannot instantiate abstract class directly
        // Shape genericShape = new Shape("Unknown"); // Compile-time error

        System.out.println("--- Creating Circle ---");
        Circle circle = new Circle("Red", 5.0);
        circle.displayColor();
        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());
        circle.describeCircle();
        circle.printInfo(); // Calls overridden method
        System.out.println("\n");

        System.out.println("--- Creating Rectangle ---");
        Rectangle rectangle = new Rectangle("Blue", 4.0, 6.0);
        rectangle.displayColor();
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        System.out.println("Rectangle Perimeter: " + rectangle.calculatePerimeter());
        rectangle.describeRectangle();
        rectangle.printInfo(); // Calls superclass method (not overridden)
        System.out.println("\n");

        // Polymorphic usage: Reference variable of abstract type pointing to subclass object
        Shape s1 = new Circle("Green", 3.0);
        Shape s2 = new Rectangle("Yellow", 2.0, 7.0);

        System.out.println("--- Polymorphic Calls ---");
        System.out.println("Area of s1 (Circle): " + s1.calculateArea());
        System.out.println("Perimeter of s2 (Rectangle): " + s2.calculatePerimeter());
        s1.displayColor();
        s2.displayColor();

        // s1.describeCircle(); // Compile-time error: describeCircle() is not in Shape
    }
}
```
**Explanation**

* `Shape` is an abstract class. It defines `color` (a common property) and a
    constructor to initialize it.
* `displayColor()` is a concrete method in `Shape`, providing shared
    functionality.
* `calculateArea()` and `calculatePerimeter()` are abstract methods. They
    **must** be implemented by any concrete subclass. This ensures that every
    `Shape` knows how to calculate its area and perimeter, even if how it does so
    varies.
* `Circle` and `Rectangle` are concrete subclasses. They extend `Shape` and
    **must** provide implementations for `calculateArea()` and `calculatePerimeter()`.
* The constructors of `Circle` and `Rectangle` call `super(color)` to
    initialize the `color` property defined in the `Shape` class.
* We demonstrate polymorphic behavior by creating `Shape` reference variables
    (`s1`, `s2`) that point to `Circle` and `Rectangle` objects, respectively.
    This allows us to treat different shapes uniformly.


#### Example 2: Vehicle Management System
Let's model different types of vehicles. All vehicles have a common startEngine() and stopEngine() mechanism, but the way they fuelUp() might differ (petrol, electric).

```Java

// Abstract Class: Vehicle
abstract class Vehicle {
    String make;
    String model;
    int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        System.out.println("Vehicle created: " + year + " " + make + " " + model);
    }

    // Concrete method: common behavior for all vehicles
    public void startEngine() {
        System.out.println(make + " " + model + " engine started.");
    }

    public void stopEngine() {
        System.out.println(make + " " + model + " engine stopped.");
    }

    // Abstract method: specific implementation required by subclasses
    public abstract void fuelUp();

    // Concrete method that uses abstract method internally (Template Method pattern idea)
    public void performMaintenance() {
        System.out.println("Performing general maintenance for " + make + " " + model + ".");
        fuelUp(); // Calls the specific fuelUp for the subclass
        System.out.println("Maintenance complete.");
    }

    // Final method: cannot be overridden
    public final void displayVehicleDetails() {
        System.out.println("Details: " + year + " " + make + " " + model);
    }
}

// Concrete Subclass: Car
class Car extends Vehicle {
    int numberOfDoors;

    public Car(String make, String model, int year, int numberOfDoors) {
        super(make, model, year);
        this.numberOfDoors = numberOfDoors;
        System.out.println("Car created: " + numberOfDoors + " doors.");
    }

    @Override
    public void fuelUp() {
        System.out.println(make + " " + model + " is fueling up with Petrol/Gasoline.");
    }

    public void drive() {
        System.out.println(make + " " + model + " is driving.");
    }
}

// Concrete Subclass: ElectricCar
class ElectricCar extends Vehicle {
    double batteryCapacityKWh;

    public ElectricCar(String make, String model, int year, double batteryCapacityKWh) {
        super(make, model, year);
        this.batteryCapacityKWh = batteryCapacityKWh;
        System.out.println("Electric Car created with " + batteryCapacityKWh + " kWh battery.");
    }

    @Override
    public void fuelUp() {
        System.out.println(make + " " + model + " is charging its battery.");
    }

    public void charge() {
        System.out.println(make + " " + model + " is actively charging.");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        System.out.println("--- Creating Car ---");
        Car myCar = new Car("Honda", "Civic", 2020, 4);
        myCar.startEngine();
        myCar.fuelUp(); // Specific fuelUp for Car
        myCar.drive();
        myCar.performMaintenance(); // Uses the abstract method internally
        myCar.displayVehicleDetails();
        myCar.stopEngine();
        System.out.println("\n");

        System.out.println("--- Creating Electric Car ---");
        ElectricCar myElectricCar = new ElectricCar("Tesla", "Model 3", 2023, 75.0);
        myElectricCar.startEngine();
        myElectricCar.fuelUp(); // Specific fuelUp for ElectricCar
        myElectricCar.charge();
        myElectricCar.performMaintenance(); // Uses the abstract method internally
        myElectricCar.displayVehicleDetails();
        myElectricCar.stopEngine();
        System.out.println("\n");

        // Polymorphic Array
        Vehicle[] vehicles = new Vehicle[2];
        vehicles[0] = new Car("Toyota", "Camry", 2022, 4);
        vehicles[1] = new ElectricCar("Nissan", "Leaf", 2021, 62.0);

        System.out.println("--- Processing Vehicles Polymorphically ---");
        for (Vehicle v : vehicles) {
            v.startEngine();
            v.fuelUp(); // Polymorphic call: Car fuels up with petrol, ElectricCar charges
            v.performMaintenance();
            v.displayVehicleDetails();
            v.stopEngine();
            System.out.println("---");
        }
    }
}
```
**Explanation**

* `Vehicle` is an abstract class with common properties (`make`, `model`, `year`)
    and concrete methods (`startEngine`, `stopEngine`).
* `fuelUp()` is an abstract method, forcing `Car` to define how it fuels (petrol)
    and `ElectricCar` to define how it fuels (charging).
* `performMaintenance()` is a concrete method in `Vehicle` that calls the abstract
    `fuelUp()` method. This demonstrates a simple form of the **Template Method
    pattern**: the general maintenance process is defined, but a specific step
    (`fuelUp`) is left to subclasses.
* `displayVehicleDetails()` is a `final` method, ensuring that no subclass can
    alter how vehicle details are displayed.
* Polymorphism is again showcased by iterating through an array of `Vehicle`
    objects, where `fuelUp()` behaves differently based on the actual object type.

# 6. Limitations and Considerations
* **Single Inheritance**: A key limitation is that a class can only extend one
    abstract class in Java. If you need to inherit behavior from multiple distinct
    sources, interfaces are a better choice.
* **Partial Abstraction**: Abstract classes offer partial abstraction (0-100%).
    If you need 100% abstraction where all methods must be implemented by
    subclasses, an interface might be more suitable (especially pre-Java 8).
* **Purpose**: Use abstract classes when you have a strong "is-a" relationship
    and want to provide a common base implementation along with enforcing certain
    behaviors. If the relationship is more about "can-do" or capabilities,
    interfaces are generally preferred.

# 7. Conclusion
Abstract classes are powerful tools in Java for achieving abstraction and
promoting code reuse and maintainability. They allow you to define a common
blueprint for a family of related classes, providing both shared functionality
and enforcing specific behavior through abstract methods. By understanding their
characteristics and knowing when to apply them, you can design more robust,
flexible, and scalable object-oriented systems.