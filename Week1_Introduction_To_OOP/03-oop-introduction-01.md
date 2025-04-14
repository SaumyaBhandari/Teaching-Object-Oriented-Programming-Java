# 3. Introduction to Object-Oriented Programming

## 3.1 Object-Oriented Thinking

---

### 🧠 A New Way to Think About the World

Before we write code, let’s **shift the way we think**.

- In **procedure-oriented programming**, we focused on *what the computer should do* writing functions that take input, process it, and return output.

- But in **object-oriented programming**, we begin by thinking about **things**, the *objects* involved in our problem, and **what they know** and **what they can do**.

---

### 📦 What is an Object?

An **object** is a real-world entity that has:

- **State** (what it knows)
- **Behavior** (what it can do)

You can think of it like this:

| Real-world Object | State (Attributes) | Behavior (Methods/Actions) |
|-------------------|--------------------|-----------------------------|
| **Car**           | color, model, speed | start(), stop(), accelerate() |
| **Student**       | name, roll, GPA     | study(), submitAssignment()  |
| **Bank Account**  | accountNumber, balance | deposit(), withdraw()      |

In OOP, *everything is treated as an object*. We focus on:

> Who is doing the work?  
> What does the object know about itself?  
> How does the object behave?

---

### 🎯 Real-World Analogy: Teaching with Objects

Let’s say in this classroom,

In object-oriented thinking:

- I (the **Teacher**) am an object.
- The **Students** are objects.
- The **Classroom** is an object.
- The **Whiteboard** is an object.

Each of these has:

- **Attributes** (e.g., teacher has name, subject)
- **Behaviors** (e.g., teacher can teach, student can learn)

You wouldn’t say:
> “Call the `teach()` function and pass the student name and subject as arguments.”

Instead, in OOP-style:
> “Let the **Teacher object** perform the `teach()` method to the **Student object**.”

This **object-to-object interaction** is the heart of object-oriented design.

---

### 💡 Object-Oriented vs Procedure-Oriented Thinking

| Aspect | Procedure-Oriented Thinking | Object-Oriented Thinking |
|--------|-----------------------------|--------------------------|
| Focus  | Functions and procedures     | Objects and their interactions |
| Approach | Top-down, step-by-step logic | Bottom-up, based on modeling real entities |
| Data | Shared across procedures | Encapsulated inside objects |
| Control | Functions act on data | Objects send messages to each other |

---

### 🔧 Simple Code Comparison

#### 🔵 Procedure-Oriented Style (C-style):

```c
float balance = 0;

void deposit(float amount) {
    balance += amount;
}

void withdraw(float amount) {
    if (balance >= amount) balance -= amount;
}
```
#### 🟢 Object-Oriented Style (in pseudocode):
```java
class BankAccount {
    float balance;

    void deposit(float amount) {
        balance += amount;
    }

    void withdraw(float amount) {
        if (balance >= amount) balance -= amount;
    }
}
```
🧠 Key Shift: Instead of asking “What function should I write?”, we ask “What objects are in my problem, and what do they do.

### 🧰 Why Object-Oriented Thinking is Powerful
- **Closer to the real world:** You model real entities.
- **Modularity:** Each object is like a building block.
- **Reusability:** Objects can be reused across programs.
- **Maintainability:** Easier to debug and enhance when programs are modular.

### 🏁 Summary
- Object-Oriented Thinking is about seeing the world as interacting objects.
- Each object has attributes (data) and methods (functions that act on the data).
- This shift in thinking lays the foundation for Object-Oriented Programming (OOP), which we’ll explore next.

