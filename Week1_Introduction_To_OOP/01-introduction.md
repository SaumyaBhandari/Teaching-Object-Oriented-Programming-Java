# 1. Introduction to Object-Oriented Programming

## 1.1 Overview: How Traditional Programming Evolved

Programming started with very simple, linear approaches. Here's how it evolved over time:

### 🧱 Machine Language (1st Generation)
- Programs were written using 0s and 1s (binary).
- Very low-level, hard to understand, and prone to error.
- Example:
  ```10111010 01100001```


### ⚙️ Assembly Language (2nd Generation)
- Introduced mnemonics like `MOV`, `ADD`, `SUB`.
- Slightly more human-readable but still machine-specific.
- Example:
   ```MOV AX, 5 ADD AX, 3```


### 🔁 Procedure-Oriented Programming (3rd Generation)
- Languages: C, Pascal, Fortran.
- Programs are divided into functions or procedures.
- Focuses on steps and procedures to solve a problem.
- Data and functions are separate.

---

## 1.2 Characteristics of Procedure-Oriented Programming (POP)

- **Top-down design:** Start from the main problem and break it into sub-problems.
- **Focus on functions:** Procedures are the building blocks.
- **Global data usage:** Functions operate on shared variables.
- **Tight coupling:** Changes in data structure can affect multiple functions.

### ✍️ Example: Simple Banking System in C

```c
float balance = 1000.0;

void deposit(float amount) {
  balance += amount;
}

void withdraw(float amount) {
  balance -= amount;
}
```


## 1.2 Characteristics of Procedure-Oriented Programming (POP)

- **Top-down design:** Start from the main problem and break it into sub-problems.
- **Focus on functions:** Procedures are the building blocks.
- **Global data usage:** Functions operate on shared variables.
- **Tight coupling:** Changes in data structure can affect multiple functions.

### ✍️ Example: Simple Banking System in C

```c
float balance = 1000.0;

void deposit(float amount) {
  balance += amount;
}

void withdraw(float amount) {
  balance -= amount;
}
```
All functions directly manipulate global data (balance). As the system grows, tracking changes and debugging becomes harder.

---

## 1.3 Limitations in Real-World Modeling
Procedure-Oriented Programming works fine for small programs but fails to scale for complex systems:

 - Poor at modeling real-world entities.
 - No direct relationship between data and behavior.
 - Code duplication increases with project size.
 - Difficult to maintain and extend.
 - Low reusability of code blocks.

---
## 1.4 Real-World Analogy: Why We Need a New Approach

### 🚗 Analogy: Building a Car

| **Procedure-Oriented Programming**  | **Object-Oriented Programming**                          |
|-------------------------------------|----------------------------------------------------------|
| Focuses on `buildEngine()`, `paintCar()`  | Focuses on the `Car` object itself                      |
| Data is global or passed around          | Data is encapsulated inside the object                  |
| Hard to reuse code for a new type (e.g., Bike) | Inheritance allows easy reuse and extension              |


### 🧍 Analogy: Modeling a Person
In POP:

```c
void walk(char name[]) { ... }
void eat(char name[]) { ... }
```

In OOP:

```java 
person.walk();
person.eat();
```
Here, the person object holds both data and behavior, making the code more intuitive and modular.

## 1.5 Conclusion
Procedural programming was a major breakthrough but is limited for large-scale, real-world systems. Object-Oriented Programming (OOP) offers a new way of thinking that mirrors real-world entities with attributes and actions.
