# 1. Introduction to Interfaces
---
An interface in Java is a blueprint of a class. It defines a contract specifying
a set of methods that a class must implement. Unlike abstract classes, interfaces
are primarily designed to achieve 100% abstraction (prior to Java 8) and provide
a mechanism for multiple inheritance of type (or behavior).

Imagine an interface as a formal agreement. Any class that signs this agreement
(by implementing the interface) must fulfill all the terms of the agreement (by
providing implementations for all the methods declared in the interface).

# 2. Key Characteristics of Interfaces
---
Let's explore the essential features of interfaces in detail:

* **Implicitly `public abstract` Methods (Pre-Java 8)**:
    * Before Java 8, all methods declared in an interface were implicitly `public`
        and `abstract`. You didn't need to explicitly use these keywords.
    * Implication: Any concrete class implementing such an interface must provide
        an implementation for all its methods.
    * Example: `void myMethod();` is automatically `public abstract void myMethod();`.

* **`default` Methods (Java 8 and Later)**:
    * Introduced in Java 8, `default` methods allow interfaces to have methods
        with concrete implementations.
    * They are marked with the `default` keyword.
    * Purpose: To add new methods to an existing interface without breaking all
        existing classes that implement that interface. Classes can inherit the
        default implementation or override it.
Example:
```Java

interface MyInterface {
    void abstractMethod();
    default void defaultMethod() {
        System.out.println("Default implementation.");
    }
}
```
* **`static` Methods (Java 8 and Later)**:
    * Also introduced in Java 8, `static` methods in interfaces are similar to
        `static` methods in classes. They belong to the interface itself and
        cannot be overridden by implementing classes.
    * They are implicitly `public`.
    * Purpose: To provide utility methods related to the interface that can be
        called directly using the interface name.
Example:
```Java

interface MyInterface {
    // ...
    static void staticMethod() {
        System.out.println("Static method in interface.");
    }
}
// Usage: MyInterface.staticMethod();
```

* **Implicitly `public static final` Variables (Constants)**:
    * All variables declared in an interface are implicitly `public`, `static`,
        and `final`. They are treated as constants.
    * You don't need to use these keywords explicitly.
    * Syntax: `int MAX_VALUE = 100;` is automatically
        `public static final int MAX_VALUE = 100;`.
    * Implication: These variables **must** be initialized at the time of declaration.

* **Cannot be Instantiated**: Like abstract classes, you cannot create an object
    directly from an interface.
    * Example: `Printable p = new Printable();` (If `Printable` is an interface,
        this will cause a compile-time error).

* **No Constructors**: Interfaces cannot have constructors, as they cannot be
    instantiated.

* **A Class Can Implement Multiple Interfaces**: This is a crucial distinction
    from abstract classes. A class can implement multiple interfaces, allowing it
    to inherit behaviors from various sources, thus overcoming Java's single
    inheritance limitation for classes.
    * Syntax: `class MyClass implements Interface1, Interface2 { ... }`

* **An Interface Can Extend Multiple Interfaces**: An interface can extend one
    or more other interfaces. This allows for creating interface hierarchies.
    * Syntax: `interface SubInterface extends SuperInterface1, SuperInterface2 { ... }`

* **Cannot Implement a Class**: An interface cannot implement a class.

* **Interface body can contain**:
    * Constant fields (`public static final`)
    * Abstract methods (`public abstract`)
    * Default methods (`public default`)
    * Static methods (`public static`)
    * Private methods (`private`, Java 9+)

# 3. When to Use Interfaces
---
Interfaces are best suited for the following scenarios:

* **Defining a Contract/API**: When you want to define a specification of
    behavior that multiple unrelated classes might need to adhere to. Any class
    implementing the interface promises to provide implementations for all its methods.

* **Achieving Multiple Inheritance of Behavior**: When a class needs to exhibit
    behaviors from multiple distinct sources. Since Java doesn't support multiple
    inheritance of classes, interfaces provide this capability by allowing a class
    to implement multiple contracts.

* **Loose Coupling and Flexibility**: Interfaces promote loose coupling between
    components. Instead of depending on concrete implementations, you can depend
    on interfaces. This makes your code more flexible and easier to change or extend.

* **Implementing Design Patterns**: Many common design patterns (e.g., Strategy,
    Observer, Factory Method, Adapter) heavily rely on interfaces for their
    flexibility and extensibility.

* **Providing Callback Mechanisms**: Interfaces are often used to define callback
    mechanisms, where one object can notify another object of an event.

# 4. Syntax of Interfaces

```Java

// Declaring an interface
interface MyInterface {

    // Constant variable (implicitly public static final)
    int MAX_VALUE = 100;
    String GREETING = "Hello from Interface";

    // Abstract method (implicitly public abstract, pre-Java 8)
    void abstractMethod1();
    String abstractMethod2(int value);

    // Default method (Java 8+)
    default void defaultMethod() {
        System.out.println("This is a default method implementation.");
        privateHelperMethod(); // Can call private methods
    }

    // Static method (Java 8+)
    static void staticUtilityMethod() {
        System.out.println("This is a static utility method in the interface.");
    }

    // Private method (Java 9+) - for use by default or static methods within the interface
    private void privateHelperMethod() {
        System.out.println("This is a private helper method.");
    }

    // Private static method (Java 9+)
    private static void privateStaticHelper() {
        System.out.println("This is a private static helper method.");
    }
}

// A class implementing the interface
class MyClass implements MyInterface {
    @Override
    public void abstractMethod1() {
        System.out.println("Implementation of abstractMethod1 in MyClass.");
    }

    @Override
    public String abstractMethod2(int value) {
        return "Implementation of abstractMethod2 with value: " + value;
    }

    // MyClass can optionally override defaultMethod()
    @Override
    public void defaultMethod() {
        System.out.println("MyClass has overridden the default method.");
    }
}
```

# 5. Detailed Examples

#### Example 1: Drivable and Swimmable Objects

Consider objects that can be Drivable or Swimmable. These are behaviors, not types of "is-a" relationships.

```Java

// Interface 1: Drivable
interface Drivable {
    void start();
    void accelerate();
    void brake();
    default void honk() { // Java 8 default method
        System.out.println("Honk! Honk!");
    }
    static void displayDrivingInstructions() { // Java 8 static method
        System.out.println("Follow traffic rules and drive safely.");
    }
}

// Interface 2: Swimmable
interface Swimmable {
    void dive();
    void surface();
    void swim();
}

// Class implementing only Drivable
class Car implements Drivable {
    String model;

    public Car(String model) {
        this.model = model;
    }

    @Override
    public void start() {
        System.out.println(model + " engine started.");
    }

    @Override
    public void accelerate() {
        System.out.println(model + " is accelerating.");
    }

    @Override
    public void brake() {
        System.out.println(model + " is braking.");
    }

    // Car-specific method
    public void openDoors() {
        System.out.println(model + " doors are open.");
    }
}

// Class implementing both Drivable and Swimmable (Amphibious Vehicle)
class AmphibiousVehicle implements Drivable, Swimmable {
    String name;

    public AmphibiousVehicle(String name) {
        this.name = name;
    }

    // Drivable methods
    @Override
    public void start() {
        System.out.println(name + " engine starting for land travel.");
    }

    @Override
    public void accelerate() {
        System.out.println(name + " accelerating on land.");
    }

    @Override
    public void brake() {
        System.out.println(name + " braking on land.");
    }

    // Swimmable methods
    @Override
    public void dive() {
        System.out.println(name + " is diving into water.");
    }

    @Override
    public void surface() {
        System.out.println(name + " is surfacing from water.");
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming.");
    }

    // AmphibiousVehicle-specific method
    public void switchMode() {
        System.out.println(name + " is switching between land and water modes.");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        System.out.println("--- Car Operations ---");
        Car myCar = new Car("Sedan X");
        myCar.start();
        myCar.accelerate();
        myCar.honk(); // Uses default method from Drivable
        myCar.brake();
        myCar.openDoors();
        Drivable.displayDrivingInstructions(); // Calls static method from Drivable
        System.out.println("\n");

        System.out.println("--- Amphibious Vehicle Operations ---");
        AmphibiousVehicle aquaCar = new AmphibiousVehicle("Aqua Explorer");
        aquaCar.start();    // Drivable
        aquaCar.accelerate(); // Drivable
        aquaCar.switchMode();
        aquaCar.dive();     // Swimmable
        aquaCar.swim();     // Swimmable
        aquaCar.surface();  // Swimmable
        aquaCar.brake();    // Drivable (even in water, could be specific implementation)
        System.out.println("\n");

        // Polymorphic usage with interfaces
        System.out.println("--- Polymorphic Drivable Objects ---");
        Drivable d1 = new Car("Compact Y");
        Drivable d2 = new AmphibiousVehicle("Sea-Land Rover");

        d1.start();
        d1.accelerate();
        d1.honk(); // Both will use the default honk, unless overridden
        d2.start();
        d2.accelerate();
        d2.honk();
        System.out.println("\n");

        System.out.println("--- Polymorphic Swimmable Objects ---");
        Swimmable s1 = new AmphibiousVehicle("Water Rider");
        // Swimmable s2 = new Car("Test Car"); // Compile-time error: Car does not implement Swimmable
        s1.dive();
        s1.swim();
        s1.surface();
    }
}
```

**Explanation**

* `Drivable` and `Swimmable` are interfaces defining behaviors.
* `Car` implements only `Drivable`, fulfilling its contract.
* `AmphibiousVehicle` implements both `Drivable` and `Swimmable`,
    demonstrating multiple inheritance of behavior. It provides implementations
    for all methods from both interfaces.
* The `honk()` method in `Drivable` is a `default` method, showing how
    interfaces can provide a common implementation that can be optionally
    overridden.
* `displayDrivingInstructions()` is a `static` method, callable directly on
    the interface.
* Polymorphism is clearly shown by using `Drivable` and `Swimmable` interface
    types to refer to `Car` and `AmphibiousVehicle` objects, allowing us to
    interact with them based on their capabilities.


#### Example 2: Device with On/Off and Volume Control

Imagine a generic Device that can be turned PowerControllable and have VolumeControllable features.

```Java

// Interface 1: PowerControllable
interface PowerControllable {
    void turnOn();
    void turnOff();
    boolean isOn();
}

// Interface 2: VolumeControllable
interface VolumeControllable {
    void increaseVolume(int level);
    void decreaseVolume(int level);
    int getCurrentVolume();
    int MAX_VOLUME = 100; // Implicitly public static final
}

// Class implementing both interfaces
class Television implements PowerControllable, VolumeControllable {
    private boolean powerStatus = false;
    private int currentVolume = 10;
    private String brand;

    public Television(String brand) {
        this.brand = brand;
    }

    // PowerControllable implementations
    @Override
    public void turnOn() {
        if (!powerStatus) {
            powerStatus = true;
            System.out.println(brand + " TV is now ON.");
        } else {
            System.out.println(brand + " TV is already ON.");
        }
    }

    @Override
    public void turnOff() {
        if (powerStatus) {
            powerStatus = false;
            System.out.println(brand + " TV is now OFF.");
        } else {
            System.out.println(brand + " TV is already OFF.");
        }
    }

    @Override
    public boolean isOn() {
        return powerStatus;
    }

    // VolumeControllable implementations
    @Override
    public void increaseVolume(int level) {
        if (powerStatus) {
            currentVolume = Math.min(currentVolume + level, VolumeControllable.MAX_VOLUME);
            System.out.println(brand + " TV volume increased to: " + currentVolume);
        } else {
            System.out.println(brand + " TV is off. Cannot change volume.");
        }
    }

    @Override
    public void decreaseVolume(int level) {
        if (powerStatus) {
            currentVolume = Math.max(currentVolume - level, 0);
            System.out.println(brand + " TV volume decreased to: " + currentVolume);
        } else {
            System.out.println(brand + " TV is off. Cannot change volume.");
        }
    }

    @Override
    public int getCurrentVolume() {
        return currentVolume;
    }

    // Television-specific method
    public void changeChannel(int channel) {
        if (powerStatus) {
            System.out.println(brand + " TV changing channel to: " + channel);
        } else {
            System.out.println(brand + " TV is off. Cannot change channel.");
        }
    }
}

public class DeviceDemo {
    public static void main(String[] args) {
        Television myTV = new Television("Sony");

        myTV.decreaseVolume(5); // TV is off, won't work
        myTV.turnOn();
        myTV.changeChannel(7);
        myTV.increaseVolume(20);
        myTV.decreaseVolume(5);
        System.out.println("Current TV Volume: " + myTV.getCurrentVolume());
        myTV.increaseVolume(90); // Should cap at MAX_VOLUME
        System.out.println("Current TV Volume: " + myTV.getCurrentVolume());
        myTV.turnOff();
        myTV.increaseVolume(10); // TV is off, won't work

        System.out.println("\n--- Using Interface References ---");
        PowerControllable pc = myTV; // Polymorphism
        pc.turnOn();
        System.out.println("Is TV On? " + pc.isOn());
        // pc.increaseVolume(10); // Compile-time error: pc only knows PowerControllable methods

        VolumeControllable vc = myTV; // Polymorphism
        vc.increaseVolume(5);
        System.out.println("Volume from vc: " + vc.getCurrentVolume());
        System.out.println("Max volume constant: " + VolumeControllable.MAX_VOLUME);
        // vc.turnOff(); // Compile-time error: vc only knows VolumeControllable methods
    }
}
```

**Explanation**

* `PowerControllable` and `VolumeControllable` define distinct sets of behaviors.
* `Television` implements both interfaces, demonstrating how a single class
    can acquire multiple functionalities.
* `MAX_VOLUME` in `VolumeControllable` is a `public static final` constant,
    demonstrating how interfaces can hold shared constant values.
* Polymorphism is again highlighted by using `PowerControllable` and
    `VolumeControllable` references, showing that you can interact with the
    `Television` object based on the specific interface it implements.

# 6. Abstract Class vs. Interface: A Recap

| Feature                   | Abstract Class                                            | Interface                                                                |
| :------------------------ | :-------------------------------------------------------- | :----------------------------------------------------------------------- |
| Declaration Keyword       | `abstract class`                                          | `interface`                                                              |
| Instantiation             | Cannot be instantiated                                    | Cannot be instantiated                                                   |
| Methods                   | Can have abstract and concrete methods                    | All methods are implicitly `public abstract` (before Java 8). Can have `default` and `static` methods (Java 8+). |
| Variables                 | Can have any type of variables (instance, static, final)  | All variables are implicitly `public static final` (constants)           |
| Constructors              | Can have constructors                                     | Cannot have constructors                                                 |
| Inheritance/Implementation| A class can extend only one abstract class                | A class can implement multiple interfaces                                |
| Level of Abstraction      | Can be partial (0-100%)                                   | Full (100% before Java 8; less strict with `default` and `static` methods) |
| Relationship              | Defines an "is-a" relationship (e.g., Dog is a Animal)    | Defines a "has-a" capability or "can-do" relationship (e.g., Car has a Driveable capability) |
| Purpose                   | Designed for class hierarchy and code sharing             | Designed for defining behavior contracts and achieving multiple inheritance of type |

# 7. Conclusion
Interfaces are a cornerstone of Java's flexibility, extensibility, and the
ability to achieve true abstraction. They enforce contracts, promote loose
coupling, enable multiple inheritance of behavior, and are essential for many
powerful design patterns. By understanding how to effectively use interfaces,
you can write more modular, maintainable, and adaptable Java applications.