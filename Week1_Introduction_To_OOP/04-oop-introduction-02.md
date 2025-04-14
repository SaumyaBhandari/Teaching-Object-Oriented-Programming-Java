# 3.2 What is Object-Oriented Programming?

---

## 📘 Definition

**Object-Oriented Programming (OOP)** is a programming paradigm based on the concept of “objects”. Objects contain:

- **Data**, in the form of fields (often called **attributes** or **properties**)
- **Code**, in the form of procedures (often called **methods** or **functions**)

> OOP helps organize software as a collection of discrete objects that incorporate both data and behavior.

This approach mirrors how we perceive and interact with the real world, through objects with characteristics and behaviors.

---

## 🧩 Core Concepts of OOP

OOP is built upon **four fundamental concepts**, often referred to as the "Four Pillars of OOP":

### 1. **Encapsulation**
- Wrapping **data** and **methods** that operate on the data into a single unit (object).
- Helps protect internal object state from unintended interference.

```java
class Student {

    private String name;
    private int age;

    public void setName(String n) {
        this.name = n; 
    }

    public String getName() { 
        return this.name; 
    }
}
```

✅ Benefit: Protects internal object logic and prevents misuse.

### 2. Abstraction
Showing only essential features and hiding the complex details.
Helps reduce complexity and improve code readability.

```java
// You use a car by pressing the accelerator, you don’t need to know how the engine works. So just calling the accelerate method words.
car.accelerate();
```
✅ Benefit: Simplifies programming by hiding irrelevant implementation details.
*More on Abstraction on upcoming lecture.

### 3. Inheritance
Mechanism by which one class (child) acquires the properties and behaviors of another class (parent).
Supports reusability and extensibility.
```java
class Animal {
    void speak() { 
        System.out.println("Some sound"); 
    }
}

class Dog extends Animal {
    void speak() { 
        System.out.println("Bark"); 
    }
}

class Cat extends Animal{
    void speak(){
        System.out.println("Meow");
    }
}
```
✅ Benefit: Reduces code duplication and enhances code organization.

### 4. Polymorphism
Ability of different objects to respond to the same method call in different ways.
```java
Animal a;
a = new Dog();
a.speak(); // Outputs: Bark

a = new Cat();
a.speak(); // Outputs: Meow
```
✅ Benefit: Enables flexibility and dynamic behavior in programs.

## 🌟 Additional Features

### Class & Object
A class is a blueprint.
An object is an instance of that class.

#### Message Passing
- Objects communicate with each other using methods (like sending messages).

### Modularity
 Programs can be broken into smaller, independent units (objects/classes).

### 💡 Example: Real-World Representation
Imagine Building a School Management System:
| **Class**  | **Attributes**                        | **Methods**                             |
|------------|---------------------------------------|-----------------------------------------|
| Student    | name, rollNo, courses                | enroll(), viewGrades()                 |
| Teacher    | name, department, subjects           | assignGrades(), teach()                |
| Course     | courseCode, title, creditHours       | addStudent(), removeStudent()          |

Each class is self-contained and interacts with others — mimicking real-world entities.

##⚔️ **OOP vs POP**

| **Feature**               | **Procedure-Oriented Programming**    | **Object-Oriented Programming**         |
|---------------------------|---------------------------------------|-----------------------------------------|
| **Approach**               | Function-based                       | Object-based                           |
| **Data Handling**          | Exposed and shared                   | Encapsulated                           |
| **Code Reusability**       | Limited                               | High (via Inheritance)                 |
| **Security**               | Low (no access control)              | High (via access specifiers)           |
| **Flexibility**            | Rigid, tightly coupled               | Flexible, loosely coupled              |
| **Modularity**             | Difficult to maintain                | Modular, easier to maintain            |

✅ **Advantages of Object-Oriented Programming**
- **Modularity:** Code is organized into objects and classes.
- **Reusability:** Inheritance allows reuse of existing code.
- **Scalability:** Easy to manage growing codebases.
- **Maintainability:** Bugs are easier to isolate and fix.
- **Security:** Encapsulation hides internal object details.
- **Mapping to real world:** Natural alignment with how we understand systems.

❌ **Disadvantages of Object-Oriented Programming**
- **Steeper Learning Curve:** Beginners might find it harder than procedural programming.
- **More Complex Structure:** Code can become verbose and layered.
- **Overhead:** More memory and CPU usage due to abstraction.
- **Not Always Suitable:** For small, simple tasks, OOP might be overkill.

🧠 **Summary**
- OOP models software around objects that contain data and behavior.
- It uses **Encapsulation**, **Abstraction**, **Inheritance**, and **Polymorphism** as its core pillars.
- OOP brings the power of modularity, reusability, and scalability, but comes with its own challenges.
- Shifting from procedural to object-oriented thinking requires understanding both real-world mapping and program design.

