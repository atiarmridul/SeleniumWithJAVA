# Selenium with Java

This project uses Selenium with Java to automate web browser interactions for testing purposes. It is built with Maven and uses TestNG as the testing framework.

## Project Structure

The project follows a standard Maven directory layout:

```
.
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── practicetestautomation
    │   │           └── pageobjects
    │   │               ├── BasePage.java
    │   │               ├── ExceptionsPage.java
    │   │               ├── LoginPage.java
    │   │               └── SuccessfulLoginPage.java
    │   └── resources
    │       └── msedgedriver.exe
    └── test
        ├── java
        │   └── com
        │       └── practicetestautomation
        │           └── tests
        │               ├── exceptions
        │               │   └── ExceptionsTests.java
        │               └── login
        │                   └── LoginTests.java
        └── resources
            └── TestSuits
                ├── DebugSuite.xml
                ├── FullRegressionSuite.xml
                └── SmokeTestSuite.xml
```

### Page Objects

The `src/main/java` directory contains the page objects, which model the pages of the web application under test. This separates the test logic from the UI details.

- `BasePage.java`: A base class for other page objects, likely containing common functionality.
- `LoginPage.java`: Models the login page.
- `SuccessfulLoginPage.java`: Models the page shown after a successful login.
- `ExceptionsPage.java`: Models a page with elements that can trigger various Selenium exceptions.

### Tests

The `src/test/java` directory contains the TestNG tests.

- `LoginTests.java`: Contains tests for the login functionality, including positive and negative test cases.
- `ExceptionsTests.java`: Contains tests that handle different Selenium exceptions.

### Test Suites

The `src/test/resources/TestSuits` directory contains TestNG suite XML files for running different sets of tests.

- `FullRegressionSuite.xml`: Runs all tests.
- `SmokeTestSuite.xml`: Runs a subset of tests for a quick sanity check.
- `DebugSuite.xml`:  A suite for debugging purposes.

## Dependencies

The project uses the following main dependencies (see `pom.xml` for details):

- **Selenium Java**: For browser automation.
- **TestNG**: As the testing framework.

## How to Run Tests

This project uses Maven to manage dependencies and run tests.

### Prerequisites

- Java Development Kit (JDK) 22 or higher installed.
- Apache Maven installed.
- A web browser (Chrome or Firefox) installed.

### Running Tests from the Command Line

You can run the tests from the command line using Maven. The `pom.xml` is configured to use the `maven-surefire-plugin` to execute the TestNG suites.

To run a specific test suite, you can use the `suiteXmlFile` property. For example, to run the smoke test suite:

```bash
mvn test -DsuiteXmlFile=SmokeTestSuite.xml
```

To run the full regression suite:

```bash
mvn test -DsuiteXmlFile=FullRegressionSuite.xml
```

By default, the `FullRegressionSuite.xml` is used if no `suiteXmlFile` is specified.

### Browser Configuration

The tests can be configured to run on different browsers (Chrome or Firefox). This is handled by the `@Parameters("browser")` annotation in the test classes. The browser can be specified in the TestNG suite XML files. If no browser is specified, Chrome is used as the default.
