# Java Beginner-Level Programming Problems (Unit II & III)

This set of 12 beginner-to-intermediate Java programming problems is designed for students learning the following core concepts:

- Variables and Constants
- Data Types in Java
- Operators in Java
- Reading and Displaying Output
- Command Line Arguments
- Control Statements in Java
- Arrays
- Classes and Objects
- Object Creation and Initialization
- Access Specifiers and Encapsulation

---

## 1. Temperature Converter with Constants
Create a class `TemperatureConverter` that uses a `final` constant for the formula to convert Celsius to Fahrenheit. Accept a temperature from the user (using `Scanner`) and display both Celsius and Fahrenheit values.

**Concepts:** constants, variables, Scanner input, display output, arithmetic operator

---

## 2. Mobile Phone Details Using Class and Access Specifiers
Create a class `MobilePhone` with `brand`, `model`, and `price`. Keep `price` as a private variable and provide `getPrice()` and `setPrice()` methods to access and update it. Display phone details using a method.

**Concepts:** access specifiers, encapsulation, instance variables, object creation

---

## 3. Student Average Using Arrays and Class
Create a `Student` class with `name` and an array of 3 subject marks. Write a method to calculate the average and another to determine pass/fail status (average >= 40). Display the name, marks, average, and result.

**Concepts:** arrays, classes, object creation, methods, if-else

---

## 4. Command Line Argument Sum Calculator
Write a Java program that takes two numbers from command line arguments and prints their sum and product. Validate that the user enters exactly two arguments.

**Concepts:** command-line arguments, data type conversion, operators, validation

---

## 5. Tax Calculator Based on Income Slabs
Create a class `TaxPayer` with name and income. Write a method to calculate tax using if-else:

- Income < 2,50,000 → No Tax  
- 2,50,000 to 5,00,000 → 10%  
- 5,00,000 to 10,00,000 → 20%  
- Above 10,00,000 → 30%  

Display the name and tax payable.

**Concepts:** control statements, data types, arithmetic, methods

---

## 6. College Admission Eligibility (Switch Version)
Create a `Student` class with `stream` (e.g., "Science", "Commerce", "Arts"). Using `switch`, display eligible courses for each stream.

**Concepts:** switch control, classes and methods, variables

---

## 7. Customer Discount Eligibility Checker
Create a `Customer` class with `name`, `isMember` (boolean), and `purchaseAmount`. If a customer is a member and purchase amount > 1000, apply 15% discount. Otherwise, no discount. Display final amount payable.

**Concepts:** boolean type, if-else, arithmetic operators, encapsulation

---

## 8. Subject Grade Calculator with Array and Switch
Create a `Subject` class with a `subjectName` and a `marks` variable. Store 5 subjects in an array. For each subject, use switch or if-else to assign grades:

- ≥ 90 → A  
- 80–89 → B  
- 70–79 → C  
- Below 70 → D  

Display subject name, marks, and grade.

**Concepts:** arrays, classes, methods, control statements

---

## 9. Laptop Specification Viewer
Create a `Laptop` class with `brand`, `ramSize`, and `storage`. Create 3 objects and display their specs only if the RAM is 8 GB or more.

**Concepts:** variables, control statements, object creation, display output

---

## 10. Simple Interest Calculator Using Encapsulation
Create a class `SimpleInterest` with `principal`, `rate`, and `time` as private variables. Use a constructor to initialize them and a method `calculateInterest()` to return simple interest. Display the result.

**Concepts:** encapsulation, constructor, arithmetic operations, methods

---

## 11. Airline Ticket Pricing System
Create a class `Ticket` with `passengerName`, `travelClass` (`Economy`, `Business`, `First`), and `basePrice`. Use if-else or switch to increase price by:

- Economy → No change  
- Business → +30%  
- First → +50%  

Display final price.

**Concepts:** control structures, class/methods, percentage calculations

---

## 12. Array of Books with Access Check
Extend the Book class: create an array of 5 Book objects. Add a method to check if a book's title matches a user-given string (via `Scanner`). If found, display its author and ISBN.

**Concepts:** arrays, class objects, conditional logic, user input

---
