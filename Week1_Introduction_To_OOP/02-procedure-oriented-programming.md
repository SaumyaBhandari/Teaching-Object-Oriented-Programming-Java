# 2. Procedure-Oriented Programming (POP)

## 2.1 What is Procedure-Oriented Programming?

**Procedure-Oriented Programming (POP)** is a programming paradigm that focuses on writing procedures or functions to operate on data. It follows a **top-down** approach where a problem is broken down into smaller sub-problems (tasks or functions), and each part is handled individually using a sequence of steps.

The program is organized around:
- Procedures (also called functions or subroutines)
- A logical sequence of operations

### 📌 Simple Definition:
> POP is a method of programming where the logic of the program is built around functions, and data is typically passed or shared between those functions.

---

## 2.2 Key Characteristics of POP

### 🔁 A. **Function-Centric Approach**

In POP, the focus is on writing **functions** (also called procedures) that perform specific tasks. The entire program is seen as a collection of these functions that interact through shared or passed data.

#### ✅ Example:
```c
#include <stdio.h>

void add(int a, int b) {
    printf("Sum: %d\n", a + b);
}

int main() {
    int x = 5, y = 10;
    add(x, y);
    return 0;
}
```
 - Here, add() is a function that performs a task.
 - It does not belong to any object or class (unlike in OOP).
 - Data is passed as arguments to the function.

### 🌐 B. Global Data and Separate Functions
Functions in POP often use global variables, making them dependent on external data that exists outside their scope.

✅ Example:
```c
#include <stdio.h>

int total = 0;  // Global variable

void add(int a) {
    total += a;
}

void show() {
    printf("Total: %d\n", total);
}

int main() {
    add(10);
    add(20);
    show();
    return 0;
}
```
```total``` is a global variable shared across multiple functions.
This can lead to unintended side effects if any function changes it incorrectly.

### 🔃 C. Sequential Flow of Control
 - Programs follow a top-down execution.
 - Control flows from the beginning of the program to the end, usually through a series of function calls.
 - There is no encapsulation, so all functions have potential access to shared data.

✅ Example: Simple Calculator

```c
#include <stdio.h>

int main() {
    int a = 10, b = 5;
    printf("Add: %d\n", a + b);
    printf("Subtract: %d\n", a - b);
    printf("Multiply: %d\n", a * b);
    printf("Divide: %d\n", a / b);
    return 0;
}
```

## 2.3 Common POP Languages
The following are the most well-known Procedure-Oriented Programming languages:

| **Language** | **Description**                                                                 |
|--------------|---------------------------------------------------------------------------------|
| C            | The most popular POP language. Functions are used to perform tasks; no built-in object support. |
| Pascal       | Designed for teaching structured programming; uses functions and procedures for modularity. |
| Fortran      | One of the earliest POP languages, widely used in scientific and engineering applications. |


## 🧠 How POP Solves a Problem: A Real-World Analogy
Let’s say you are managing a hotel:

#### In POP, you would have:
- A set of procedures for checkIn(), checkOut(), calculateBill(), etc.
- Guest information would be stored in global variables accessed and updated by all these procedures.

#### 🧱 The Problem:
If one procedure mistakenly modifies guestRoomNumber, it affects all others. It becomes hard to debug and manage.

#### 🧪 Sample Project Structure in POP

Goal: Student Management System (Add student, view student)

✅ Example in C:

```c
#include <stdio.h>
#include <string.h>

char name[100];
int roll;

void inputStudent() {
    printf("Enter name: ");
    scanf("%s", name);
    printf("Enter roll number: ");
    scanf("%d", &roll);
}

void displayStudent() {
    printf("Name: %s\n", name);
    printf("Roll No: %d\n", roll);
}

int main() {
    inputStudent();
    displayStudent();
    return 0;
}
```
- The global variables name and roll are accessible by all functions.
- No concept of data hiding or encapsulation.
- Not scalable if we want to add more students or additional details.

## 🚫 Limitations of POP
- **Data is not secure:** No data hiding, global data can be accessed/modified by any function.
- **Code is hard to maintain:** Large codebases become difficult to manage as everything is interdependent.
- **Low reusability:** Difficult to reuse functions for different types of data.
- **No real-world mapping:** Objects like "Student", "Car", or "BankAccount" can’t be naturally represented.
- **Scalability issues:** Difficult to manage larger projects with thousands of lines of code.

## Conclusion
Procedure-Oriented Programming was revolutionary in its time and still plays an important role, especially in systems-level programming. However, as programs became more complex, the need to model the real world more naturally, ensure better data security, and reuse code effectively led to the rise of Object-Oriented Programming (OOP).

In the next section, we will explore how OOP solves these limitations and introduces a new way to think about code: around objects, not just functions.
