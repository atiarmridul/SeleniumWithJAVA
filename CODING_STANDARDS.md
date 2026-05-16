# Selenium Java Automation Standards

This repository follows maintainable Selenium automation engineering practices.

---

# Framework Principles

- Keep tests readable and modular
- Separate test logic from page logic
- Avoid duplicated automation steps
- Prefer reusable utilities

---

# Locator Standards

Preferred locator order:

1. id
2. name
3. css selector
4. xpath only if required

Avoid unstable locators.

---

# Wait Strategy

- Use explicit waits
- Avoid hardcoded delays
- Improve synchronization reliability

Bad Example:

```java
Thread.sleep(5000);
```

Preferred:

```java
wait.until(ExpectedConditions.visibilityOf(element));
```

---

# Page Object Model Standards

- Centralize selectors
- Keep methods reusable
- Avoid assertions inside page classes
- Keep page objects clean and maintainable

---

# Assertion Standards

- Assertions should validate expected behavior
- Keep failures understandable
- Avoid weak validations

---

# Engineering Philosophy

This project emphasizes:

- Reliable test automation
- Maintainable architecture
- Reusable framework components
- QA engineering mindset
