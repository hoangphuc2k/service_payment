# Payment Service System

## Overview

The Payment Service System is a commands-line application that allows customers to manage and pay their bills. The application supports adding funds to a customer's account, viewing, updating, deleting, and searching for bills. It also supports paying a bill, prioritizing those with the earliest due dates, scheduling bill payments, and viewing payment transaction history.

## Features

1. Add funds to the customer's account.
2. View, and search for bills.
3. Pay a valid bill using available funds.
4. Schedule bill payments.
5. Track bill due dates.
6. View payment transaction history.

## Requirements

- Java Development Kit (JDK) 8 or higher
- Unix-like environment (Linux/MacOS)
- JUnit 4 for testing

## Directory Structure
```dtd
📦service_payment
  ┣ 📂src
  ┃ ┣ 📂commands
  ┃ ┃ ┗ 📜CommandProcessor.java
  ┃ ┣ 📂entities
  ┃ ┃ ┣ 📜Bill.java
  ┃ ┃ ┣ 📜Customer.java
  ┃ ┃ ┗ 📜Transaction.java
  ┃ ┣ 📂enums
  ┃ ┃ ┣ 📜BillState.java
  ┃ ┃ ┣ 📜BillType.java
  ┃ ┃ ┣ 📜Feature.java
  ┃ ┃ ┗ 📜TransactionState.java
  ┃ ┣ 📂services
  ┃ ┃ ┣ 📜PaymentService.java
  ┃ ┃ ┗ 📜PaymentServiceImpl.java
  ┃ ┣ 📂tests
  ┃ ┃ ┣ 📜CommandProcessorTest.java
  ┃ ┃ ┗ 📜PaymentServiceTest.java
  ┃ ┣ 📂utils
  ┃ ┃ ┗ 📜DateUtil.java
  ┃ ┗ 📜Main.java
```
## Setup and Running the Application

1. **Clone the Repository**
   Clone the repository to your local machine.

   ```shell
   git clone <repository-url>
   cd <repository-directory>
   ```
2. **Compile the Source Code**
   Navigate to the src directory and compile the Java files.
   ```shell
   cd src
   javac -d out *.java
   ```
3. **Run the Application**
   Run the Main class to start the application.
   ```shell
   java -classpath "out" Main
   ```
   
## Running Unit Tests
1. **Download JUnit and Hamcrest Libraries**
   Download JUnit 4 and Hamcrest libraries and place them in the libs directory.
2. **Compile the Tests**
   Navigate to the project root directory and compile the tests files along with the source files.
   ```shell
   javac -d out -classpath "libs\junit-4.13.2.jar;libs\hamcrest-core-1.3.jar;out" tests\*.java
   ```
3. **Run the Tests**
   Use the JUnit commands line runner to execute the tests.
   ```shell
   java -classpath "libs\junit-4.13.2.jar;libs\hamcrest-core-1.3.jar;out" org.junit.runner.JUnitCore tests.PaymentServiceTest
   ```
   ```shell
   java -classpath "libs\junit-4.13.2.jar;libs\hamcrest-core-1.3.jar;out" org.junit.runner.JUnitCore tests.CommandProcessorTest
   ```



