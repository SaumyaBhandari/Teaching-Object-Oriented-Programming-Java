# Java OOP Practice Problems: Inheritance, Polymorphism, and Encapsulation

These practice problems are designed to help you master Java OOP concepts: **Inheritance**, **Polymorphism**, and **Encapsulation**. Each question now includes **clear implementation guidelines**, **expected methods**, **field usage**, and **file/class names** for better structure and understanding.

---

## 1. SmartDevice Hierarchy

**Instructions:**  
- Create a base class `SmartDevice` with fields: `brand`, `model`, and methods `turnOn()` and `turnOff()`.  
- Create subclasses: `SmartPhone` and `SmartWatch`.  
- Override `turnOn()` and `turnOff()` in both subclasses to show device-specific behavior.  
- Instantiate devices and call their methods to demonstrate **runtime polymorphism**.  
- File: `SmartDevices.java`.

---

## 2. Bank Account Simulation

**Instructions:**  
- Create a class `BankAccount` with:
  - Fields: `accountNumber`, `balance`
  - Methods: `deposit(double amount)`, `withdraw(double amount)`, and `getBalance()`
- Create a subclass `SavingsAccount` with:
  - Field: `interestRate`
  - Override `withdraw()` to prevent withdrawal if it causes the balance to fall below a threshold (e.g., 1000).
  - Add a method `addInterest()` to update balance based on the interest rate.
- Make all fields private and use **getters/setters**.
- Demonstrate account creation, deposit, interest addition, and controlled withdrawal.
- File: `BankSystem.java`.

---

## 3. Animal Sound System

**Instructions:**  
- Create a base class `Animal` with method `makeSound()`.  
- Subclasses: `Dog`, `Cat`, `Cow` override `makeSound()` to print specific animal sounds.  
- Write a main method that stores different animals in an array and uses **polymorphism** to call `makeSound()` on each.  
- File: `AnimalSounds.java`.

---

## 4. Shape Area Calculator

**Instructions:**  
- Abstract class: `Shape` with abstract method `double calculateArea()`.  
- Subclasses:
  - `Rectangle` with fields `length` and `width`
  - `Circle` with field `radius`
  - `Triangle` with fields `base` and `height`
- Override `calculateArea()` in each to return area.  
- Create an array of `Shape` references and compute total area.  
- File: `ShapeAreaCalculator.java`.

---

## 5. Employee Management System

**Instructions:**  
- Base class `Employee` with private fields `name`, `id`, and method `calculateSalary()`.  
- Subclasses:
  - `Manager` with `bonus`
  - `Developer` with `overtimeHours`
- Use encapsulation for fields and override `calculateSalary()`:
  - For `Manager`, salary = base + bonus
  - For `Developer`, salary = base + overtimeHours * rate  
- File: `EmployeeSystem.java`.

---

## 6. Vehicle Rental System

**Instructions:**  
- Create base class `Vehicle`:
  - Fields: `plateNumber`, `baseRate`
  - Method: `calculateRental(int days)`
- Subclasses:
  - `Car`: charges baseRate * days
  - `Truck`: charges baseRate * days + load fee
  - `Bike`: fixed rate regardless of days
- Override `calculateRental()` to reflect different pricing models.  
- File: `VehicleRental.java`.

---

## 7. Student Grade Book

**Instructions:**  
- Class `Student` with private fields: `name`, `rollNumber`, `marks[]`.  
- Encapsulate with getters/setters.  
- Method `calculateAverage()` returns average marks.  
- Subclass `GraduateStudent` adds `thesisTopic` and a method `printThesisTitle()`.  
- Demonstrate both types of students.  
- File: `GradeBook.java`.

---

## 8. E-Commerce Product Catalog

**Instructions:**  
- Base class `Product` with fields: `name`, `price`.  
- Method: `displayDetails()` prints basic info.  
- Subclasses:
  - `Electronics`: add `warrantyPeriod`
  - `Clothing`: add `size` and `material`
  - `Grocery`: add `expiryDate`
- Override `displayDetails()` to show product-specific info.  
- File: `ProductCatalog.java`.

---

## 9. Library Management System

**Instructions:**  
- Class `LibraryItem` with fields: `title`, `author`, `id`.  
- Method: `getInfo()` prints details.  
- Subclasses: `Book`, `Magazine`, `Newspaper` override `getInfo()` to include type-specific info (e.g., publication frequency, issue number).  
- Demonstrate a catalog using an array of `LibraryItem` objects.  
- File: `LibrarySystem.java`.

---

## 10. Music Player App

**Instructions:**  
- Class `Song`: fields `title`, `artist`, and method `play()`  
- Subclasses: `PopSong`, `RockSong`, `JazzSong` override `play()` with unique print messages  
- Demonstrate polymorphic behavior using a playlist of `Song[]`  
- File: `MusicApp.java`.

---

## 11. Hospital Patient Records

**Instructions:**  
- Class `Patient`: private fields `name`, `age`, and method `getTreatmentPlan()`  
- Subclasses:
  - `InPatient`: add `roomNumber`, override `getTreatmentPlan()` with full plan
  - `OutPatient`: add `appointmentDate`, override `getTreatmentPlan()` accordingly
- Use encapsulation for all fields  
- File: `HospitalRecords.java`.

---

## 12. Game Character Classes

**Instructions:**  
- Base class `GameCharacter`: field `name`, method `attack()`  
- Subclasses:
  - `Warrior`: override `attack()` to return melee damage
  - `Archer`: uses `arrowCount`
  - `Mage`: uses `mana`  
- Override `attack()` in each and demonstrate use via polymorphic method calls.  
- File: `GameCharacters.java`.

---

## 13. Online Course Portal

**Instructions:**  
- Class `Course`: fields `title`, `duration`, `instructor`, method `getCourseInfo()`  
- Subclasses:
  - `LiveCourse`: add `scheduleTime`
  - `RecordedCourse`: add `videoCount`  
- Use encapsulation and method overriding  
- File: `CoursePortal.java`.

---

## 14. Transport Ticket System

**Instructions:**  
- Base class `Ticket`: fields `ticketNumber`, `price`, method `generateTicket()`  
- Subclasses:
  - `BusTicket`: includes `seatNumber`
  - `TrainTicket`: adds `coachType`
  - `FlightTicket`: adds `boardingGate`  
- Each override `generateTicket()` to print formatted ticket info.  
- File: `TicketSystem.java`.

---

## 15. Food Ordering App

**Instructions:**  
- Class `FoodItem`: private fields `name`, `price`  
- Subclasses:
  - `Pizza`: add `size`, `toppings[]`
  - `Burger`: add `cheese`, `pattyType`
  - `Salad`: add `ingredients[]`  
- Use encapsulation and override method `displayMenuItem()`  
- File: `FoodOrderApp.java`.

---

## 16. University People System

**Instructions:**  
- Base class `Person`: private fields `name`, `id`, and method `getRoleDetails()`  
- Subclasses:
  - `Student`: add `program`
  - `Teacher`: add `subject`
  - `Admin`: add `department`  
- Override `getRoleDetails()` to show respective info.  
- File: `UniversitySystem.java`.

---

## 17. Movie Theater Booking System

**Instructions:**  
- Class `Seat`: field `seatNumber`, method `bookSeat()`  
- Subclasses:
  - `RegularSeat`: booking is allowed anytime
  - `PremiumSeat`: booking includes snack and luxury tax  
- Use method overriding and encapsulation  
- File: `TheaterBooking.java`.

---

## 18. Smart Home Automation

**Instructions:**  
- Class `Device`: field `deviceName`, method `operate()`  
- Subclasses:
  - `Light`: operate() turns on light
  - `Thermostat`: operate() sets temperature
  - `SecurityCamera`: operate() starts recording  
- Demonstrate polymorphism using a `Device[]` list.  
- File: `SmartHomeSystem.java`.

---

**Concept Reminders:**  
- Use `super()` where appropriate to invoke parent constructors  
- Practice both **method overriding (runtime polymorphism)** and **method overloading (compile-time polymorphism)**  
- Use `static` variables to track common data like total objects created or shared counters
- Use `final` to prevent subclass modifications or to ensure immutability