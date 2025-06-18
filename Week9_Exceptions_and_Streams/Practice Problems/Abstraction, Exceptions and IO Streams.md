# Part 1: The "Smart Dashain" Festival Organizer - Abstraction & Advanced Exceptions
Bijay and Pooja are planning a grand Dashain celebration and need a smart system to manage the activities, especially considering the inevitable "last-minute surprises"!

## Abstract Festival Activity Planner
**Instructions:**
Planning Dashain is complex. Create a custom checked exception: `FestivalPlanningException`.
Create an abstract class `FestivalActivity` with:
* Fields: `String activityName`, `double estimatedCost`.
* A constructor to set these fields.
* An abstract method `void planActivity() throws FestivalPlanningException;` (This method will contain the specific planning logic for each activity).
* A concrete method `void displayOverview()` that prints the activity name and estimated cost.
**Interrelation:** `planActivity()` explicitly declares `FestivalPlanningException`, meaning any specific Dashain event must handle or propagate planning issues.
**File: FestivalActivity.java**


## Tika Ceremony Planning Module
**Instructions:**
Create a concrete class `TikaCeremony` that extends `FestivalActivity`.
Add fields: `int expectedGuests`, `String mainFamilyElder`.
In its constructor, take `activityName` (set to "Tika Ceremony"), `estimatedCost`, `expectedGuests`, and `mainFamilyElder`.
Override `planActivity()`:
**Interrelation:** Create a custom checked exception: `InvalidGuestCountException`. If `expectedGuests` is less than 5, throw `new InvalidGuestCountException("Not enough guests for a lively Tika! Is everyone on vacation?");`.
Also, create another custom checked exception: `BudgetExceededException`. If `estimatedCost` is greater than 50000, throw `new BudgetExceededException("Tika budget too high! Is this for the whole village?");`.
If all checks pass, print: `"Tika ceremony with " + mainFamilyElder + " planned successfully for " + expectedGuests + " guests!"`
In a main method, create `TikaCeremony` objects (some valid, some triggering exceptions) and demonstrate catching `FestivalPlanningException`, `InvalidGuestCountException`, and `BudgetExceededException`.
**File: TikaCeremony.java**


## Deusi Bhailo Event Planning Module
**Instructions:**
Create a concrete class `DeusiBhailo` that extends `FestivalActivity`.
Add fields: `List<String> plannedRoutes`, `int numberOfPerformers`.
In its constructor, take `activityName` (set to "Deusi Bhailo Program"), `estimatedCost`, `plannedRoutes`, and `numberOfPerformers`.
Override `planActivity()`:
**Interrelation:** Create a custom checked exception: `NoRouteException`. If `plannedRoutes` is empty, throw `new NoRouteException("No routes planned for Deusi Bhailo! Are we just singing in the living room?");`.
If `numberOfPerformers` is less than 3, throw `new FestivalPlanningException("Need at least 3 performers for a proper Deusi Bhailo!");`.
If all checks pass, print: `"Deusi Bhailo program with " + numberOfPerformers + " performers planned for " + plannedRoutes.size() + " routes!"`
In a main method, test `DeusiBhailo` with valid and problematic data, demonstrating catching `FestivalPlanningException` and `NoRouteException`.
**File: DeusiBhailo.java**


## Dashain Festival Master Planner
**Instructions:**
Create a class `DashainFestivalPlanner`.
Add a static method `void executeFestivalPlan(List<FestivalActivity> activities)`.
Inside this method:
* Loop through the activities list.
* For each activity, print its `displayOverview()`.
**Interrelation:** Use a `try-catch` block around `activity.planActivity()`.
* Catch `InvalidGuestCountException` and print: `"Planning Warning (Guests): " + e.getMessage()`.
* Catch `BudgetExceededException` and print: `"Planning Warning (Budget): " + e.getMessage()`.
* Catch `NoRouteException` and print: `"Planning Warning (Routes): " + e.getMessage()`.
* Catch the general `FestivalPlanningException` (for any other `FestivalPlanningException` not caught above) and print: `"General Planning Error: " + e.getMessage()`.
**Interrelation:** Add a `finally` block that always prints: `"Activity planning attempt for [activityName] completed."` (using `activity.activityName`). This simulates ensuring a step is always finished, regardless of success.
In a main method, create a `List<FestivalActivity>` with instances of `TikaCeremony` and `DeusiBhailo` (include some that will trigger various exceptions). Call `executeFestivalPlan()`.
**Humor:** This system helps manage the chaos of Dashain planning, even if someone forgets the budget or the Deusi route!
**File: DashainPlanner.java**

---

# Part 2: The "Kathmandu Commute" GPS App - Interfaces & Advanced Validation
Harikrishna and Damodar are now tackling Kathmandu's infamous traffic with a new GPS app, "Kathmandu Commute"!

## Route Validity Checker Interface
**Instructions:**
To ensure realistic routes, create an interface `RouteValidator` with an abstract method:
`boolean isValidCommuteRoute(String origin, String destination, double distanceKm) throws InvalidRouteException;`
Create a custom checked exception: `InvalidRouteException`.
**Interrelation:** This interface ensures that any route validation system declares potential issues.
**File: RouteValidator.java**


## Kathmandu Traffic Route Validator
**Instructions:**
Create a class `KathmanduTrafficValidator` that implements `RouteValidator`.
Override `isValidCommuteRoute(String origin, String destination, double distanceKm)`:
**Interrelation:** Create a custom checked exception: `SameLocationException`. If `origin.equalsIgnoreCase(destination)`, throw `new SameLocationException("Origin and destination cannot be the same! Are you just spinning in circles, Damodar?");`.
If `distanceKm` is less than 0.1 (too short for a commute) or greater than 30 (likely outside Kathmandu Valley), throw `new InvalidRouteException("Distance " + distanceKm + "km is unrealistic for Kathmandu commute!");`.
Return `true` if all checks pass.
In a main method, test `KathmanduTrafficValidator` with various scenarios (same origin/destination, very short/long distances), demonstrating catching `InvalidRouteException` and `SameLocationException`.
**File: KathmanduTrafficValidator.java**


## Navigation System Interface
**Instructions:**
The app needs a way to navigate. Create an interface `NavigationService` with an abstract method:
`void navigate(String startPoint, String endPoint, RouteValidator validator) throws NavigationFailedException;`
Create a custom checked exception: `NavigationFailedException`.
**Interrelation:** This interface defines the contract for navigation, including the possibility of failure.
**File: NavigationService.java**


## GPS Navigation Module
**Instructions:**
Create a class `GPSNavigationModule` that implements `NavigationService`.
Override `Maps(String startPoint, String endPoint, RouteValidator validator)`:
Print `"Attempting to navigate from " + startPoint + " to " + endPoint + "..."`
**Interrelation:** Use a `try-catch` block around `validator.isValidCommuteRoute(startPoint, endPoint, simulatedDistance(startPoint, endPoint))`.
If `InvalidRouteException` or `SameLocationException` is caught, wrap it in a new `NavigationFailedException` (e.g., `throw new NavigationFailedException("Route validation failed!", e);`). This shows exception chaining.
**Interrelation:** Simulate a "GPS signal lost" scenario: if `startPoint` is "Kalanki" (known for traffic), throw `new NavigationFailedException("GPS signal lost near Kalanki! Welcome to Kathmandu traffic!");`.
If all passes, print: `"Navigation successful! Estimated time: 20 minutes (or 2 hours depending on traffic)."`.
Create a helper private method `double simulatedDistance(String start, String end)` that returns a fixed distance (e.g., 5.0).
In a main method, test `GPSNavigationModule` with different routes (some valid, some failing validation, some simulating GPS loss), demonstrating catching `NavigationFailedException` and inspecting the chained exception.
**File: GPSNavigationModule.java**


## Kathmandu Commute Planner
**Instructions:**
Create a class `CommutePlanner`.
Add a static method `void planMyCommute(String origin, String destination, RouteValidator validator, NavigationService navigator)`.
Inside this method:
Print: `"Planning your commute from " + origin + " to " + destination + "..."`
**Interrelation:** Use a `try-catch` block around `navigator.navigate(origin, destination, validator)`.
Catch `NavigationFailedException`. If caught, check `e.getCause()` to see the original validation error (if chained). Print a user-friendly message based on the cause (e.g., "Cannot plan: GPS issue" or "Cannot plan: Invalid route details").
**Interrelation:** Add a `finally` block that always prints: `"Commute planning for " + origin + " to " + destination + " completed."`
In a main method:
* Instantiate `KathmanduTrafficValidator` and `GPSNavigationModule`.
* Call `planMyCommute` with various origins/destinations to test all exception paths (e.g., "Baneshwor", "Baneshwor"; "Thamel", "Patan" (valid); "Kalanki", "Balaju" (GPS error)).
**Humor:** This app might not fix the traffic, but at least it tells you why you're stuck (or why your route won't work)!
**File: CommutePlanner.java**

---

# Part 3: The "Skill Nepal" Online Learning Platform - Functional Interface & Enrollment
Anisha and Roshan are launching "Skill Nepal," an online platform for students to learn new skills.

## Course Enrollment Eligibility Rule (Functional Interface)
**Instructions:**
To define flexible enrollment criteria, create a `@FunctionalInterface` named `EligibilityRule` with a single abstract method:
`boolean isEligible(String studentId, String courseId) throws EnrollmentDeniedException;`
Create a custom checked exception: `EnrollmentDeniedException`.
**Interrelation:** This functional interface ensures that any eligibility check can throw a specific `EnrollmentDeniedException`.
**File: EligibilityRule.java**


## Online Course Enrollment Manager (with Lambda)
**Instructions:**
Create a class `CourseEnrollmentManager`.
Add a static method `void enrollStudent(String studentId, String courseId, EligibilityRule rule)`.
Inside `enrollStudent`:
Print: `"Attempting to enroll " + studentId + " in " + courseId + "..."`
**Interrelation:** Use a `try-catch` block around `rule.isEligible(studentId, courseId)`.
If `true`, print: `"Enrollment successful for " + studentId + " in " + courseId + "! Happy learning!"`
If `EnrollmentDeniedException` is caught, print: `"Enrollment failed for " + studentId + ": " + e.getMessage()`.
In a main method:
Define an `EligibilityRule` using a lambda expression. This lambda should:
* Return `true` if `studentId` starts with "SKILL" and `courseId` is "JAVA101".
**Interrelation:** If `studentId` is "SKILL999" (a suspended student), throw `new EnrollmentDeniedException("Student account suspended due to outstanding fees, Roshan!");`.
If `courseId` is "JAVA101" but `studentId` is not "SKILL..." prefix, throw `new EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix, Anisha!");`.
For any other combination, return `false` (not eligible).
Call `enrollStudent` with various `studentId`/`courseId` pairs to test all scenarios (valid, suspended, invalid ID format, general not eligible).
**Humor:** Even online learning needs rules! Your lambda ensures no one sneakily enrolls without paying.
**File: CourseEnrollmentManager.java**


## Student Dashboard Enrollment Status
**Instructions:**
Create a class `StudentDashboard`.
Add a method `void displayCourseStatus(String studentId, String courseId, EligibilityRule rule)`.
Inside `displayCourseStatus`:
Print `"Checking enrollment status for " + studentId + " in " + courseId + "..."`
**Interrelation:** Call `rule.isEligible(studentId, courseId)` within a `try-catch` block.
If `true`, print `" You are enrolled! Access course materials now."`
If `EnrollmentDeniedException` is caught, print `" Enrollment denied: " + e.getMessage() + ". Please contact support."`.
**Interrelation:** Add a `finally` block that always prints: `"Status check completed for " + studentId + "."`
In a main method:
* Create a `StudentDashboard` instance.
* Reuse the `EligibilityRule` lambda from `CourseEnrollmentManager`.
* Call `displayCourseStatus` for "SKILL123", "JAVA101" (valid); "SKILL999", "PYTHON202" (suspended); "STUDENT001", "JAVA101" (invalid ID format).
**File: StudentDashboard.java**


# Part 4: The "Nepal Tourism Board" Data System - Polymorphism & Reporting Errors
Sita and Ram are helping the Nepal Tourism Board (NTB) collect and process data from various sources to generate reports.

## Abstract Tourist Data Source
**Instructions:**
NTB needs to fetch data from different places. Create an abstract class `TouristDataSource` with:
* Field: `String sourceName`.
* A constructor.
* An abstract method `List<String> fetchData() throws DataSourceAccessException;`.
Create a custom checked exception: `DataSourceAccessException`.
**Interrelation:** `fetchData()` declares an exception, indicating that getting data from any source can fail.
**File: TouristDataSource.java**


## Airport Arrivals Data Source
**Instructions:**
Create a concrete class `AirportArrivalsDataSource` that extends `TouristDataSource`.
In its constructor, set `sourceName` to "Tribhuvan Airport Arrivals".
Override `fetchData()`:
**Interrelation:** Create a custom checked exception: `ConnectionLostException`. Simulate data fetching: If `sourceName` contains "Tribhuvan" and a random condition (e.g., `Math.random() < 0.3`) is true, throw `new ConnectionLostException("Airport data connection lost! Maybe a pigeon sat on the antenna?");`.
Otherwise, return a `List<String>` of dummy data (e.g., "Visitor: John Doe, USA", "Visitor: Emily White, UK").
In a main method, test `AirportArrivalsDataSource`, demonstrating catching `DataSourceAccessException` (which might wrap `ConnectionLostException`).
**File: AirportArrivalsDataSource.java**


## Hotel Registrations Data Source
**Instructions:**
Create a concrete class `HotelRegistrationsDataSource` that extends `TouristDataSource`.
In its constructor, set `sourceName` to "Kathmandu Hotels Registrations".
Override `fetchData()`:
**Interrelation:** Create a custom checked exception: `AuthenticationFailedException`. If `sourceName` contains "Hotels" and a random condition (`Math.random() < 0.2`) is true, throw `new AuthenticationFailedException("Hotel API authentication failed! Did someone forget the password again?");`.
Otherwise, return a `List<String>` of dummy data (e.g., "Hotel: Yak & Yeti, Guest: Ram Thapa, NP", "Hotel: Annapurna, Guest: Alice Smith, AU").
In a main method, test `HotelRegistrationsDataSource`, demonstrating catching `DataSourceAccessException` (which might wrap `AuthenticationFailedException`).
**File: HotelRegistrationsDataSource.java**


## Data Processing Interface
**Instructions:**
Once data is fetched, it needs processing. Create an interface `DataProcessor` with:
An abstract method `List<String> process(List<String> rawData) throws DataProcessingException;`
Create a custom checked exception: `DataProcessingException`.
**Interrelation:** This interface ensures any data processing module declares potential issues.
**File: DataProcessor.java**


## Unique Visitor Counter Processor
**Instructions:**
Create a class `UniqueVisitorCounter` that implements `DataProcessor`.
Override `process(List<String> rawData)`:
**Interrelation:** Create a custom checked exception: `EmptyDataException`. If `rawData` is empty, throw `new EmptyDataException("No raw data to process! Did all tourists go missing?");`.
Implement logic to count unique visitors. For simplicity, assume each string in `rawData` contains "Visitor: [Name], [Country]" or "Guest: [Name], [Country]". Extract unique names and return a list like "Unique Visitors: 5".
Otherwise, return a processed `List<String>` (e.g., indicating the count of unique visitors).
In a main method, test `UniqueVisitorCounter` with an empty list and a list of dummy data.
**File: UniqueVisitorCounter.java**


## Nepal Tourism Report Generator
**Instructions:**
Create a class `TourismReportGenerator`.
Add a static method `void generateOverallReport(List<TouristDataSource> dataSources, DataProcessor processor)`.
Inside `generateOverallReport`:
Print: `"Generating overall tourism report..."`
Loop through each `TouristDataSource` in the `dataSources` list.
**Interrelation:** Use a `try-catch` block around `dataSource.fetchData()`.
If `DataSourceAccessException` is caught, print: `"Could not fetch data from " + dataSource.sourceName + ": " + e.getMessage() + ". Skipping this source."`.
Use `e.getCause()` to get specific errors like `ConnectionLostException` or `AuthenticationFailedException` if chained, and print a more detailed message (e.g., "Reason: [cause message]").
`continue` to the next data source if an error occurs.
If data is fetched successfully, try to process it using the `processor`. Use another `try-catch` block for `processor.process()`.
If `DataProcessingException` is caught (e.g., `EmptyDataException`), print: `"Error processing data from " + dataSource.sourceName + ": " + e.getMessage() + ". Skipping this data."`.
If processing is successful, print the processed data.
**Interrelation:** Add a `finally` block that always prints: `"Data handling from " + dataSource.sourceName + " completed."`
In a main method:
* Create a `List<TouristDataSource>` with instances of `AirportArrivalsDataSource` and `HotelRegistrationsDataSource` (include some that will trigger exceptions).
* Create an instance of `UniqueVisitorCounter`.
* Call `generateOverallReport()`.

**File: TourismReportGenerator.java**