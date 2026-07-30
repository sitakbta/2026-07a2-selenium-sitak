# SauceDemo Selenium Automation Tests

**Student:** Sita Karumudi  
**Assignment:** QA Testing Module 2

## Overview

This project contains automated test cases for the [SauceDemo](https://www.saucedemo.com/) application using Selenium WebDriver. The tests are built using the **Page Object Model (POM)** design pattern to ensure maintainability and scalability.

## Project Structure

```
src/
├── main/
│   └── java/
└── test/
    └── java/
        ├── pages/
        │   ├── SauceDemoLoginPage.java       # Login page object
        │   ├── SauceDemoInventoryPage.java   # Inventory page object
        │   └── SauceDemoCartPage.java        # Shopping cart page object
        └── tests/
            └── POMSauceDemoTests.java        # Test cases
```

## Prerequisites

- **Java 26** or higher
- **Maven 3.8.0** or higher
- **Firefox Browser** (for WebDriver)

## Technologies & Dependencies

- **Selenium WebDriver** (v4.46.0) - Browser automation
- **TestNG** (v7.9.0) - Test framework
- **JUnit Jupiter** (v5.10.2) - Assertion framework
- **Commons IO** (v2.21.0) - Utility library

## Setup Instructions

### 1. Create the Repository

```bash
cd 2026-07a2-selenium-sitak
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Run the Tests

**Run all tests:**
```bash
mvn test
```

**Run a specific test:**
```bash
mvn test -Dtest=POMSauceDemoTests#test5
```

**Run tests in debug mode:**
```bash
mvn test -X
```

## Test Cases

### Test 1: Empty Username Validation
- **Objective:** Verify error message when username field is empty
- **Steps:**
  1. Open SauceDemo login page
  2. Leave username empty
  3. Click Login button
- **Expected Result:** Error message "Epic sadface: Username is required" is displayed

### Test 2: Empty Password Validation
- **Objective:** Verify error message when password field is empty
- **Steps:**
  1. Open SauceDemo login page
  2. Enter username: `standard_user`
  3. Leave password empty
  4. Click Login button
- **Expected Result:** Error message "Epic sadface: Password is required" is displayed

### Test 3: Invalid Password Validation
- **Objective:** Verify error message when valid username is used with wrong password
- **Steps:**
  1. Open SauceDemo login page
  2. Enter username: `standard_user`
  3. Enter password: `wrongpassword`
  4. Click Login button
- **Expected Result:** Error message "Epic sadface: Username and password do not match any user in this service" is displayed

### Test 4: Successful Login
- **Objective:** Verify successful login with valid credentials
- **Steps:**
  1. Open SauceDemo login page
  2. Enter username: `standard_user`
  3. Enter password: `secret_sauce`
  4. Click Login button
- **Expected Result:** User is redirected to Products page with title "Products" displayed

### Test 5: Add Item, Verify Cart, and Remove Item
- **Objective:** Verify full shopping cart workflow (add → verify → remove → verify empty)
- **Steps:**
  1. Login with valid credentials (standard_user / secret_sauce)
  2. Add an item to cart by defining [1]/[2]/13]/[4]/[5]/[6]
  3. Verify item count in cart badge (should be 1)
  4. Navigate to cart page
  5. Verify item is present in cart
  6. Remove item from cart
  7. Verify cart is empty
- **Expected Result:** 
  - Cart badge shows "1" after adding item
  - Item appears in cart page
  - Cart is empty after removal


## Page Object Model (POM)

### SauceDemoLoginPage
Handles all login-related interactions:
- `openSauceDemoPage()` - Navigate to SauceDemo URL
- `sendTextToUsername(String text)` - Enter username
- `sendTextToPassword(String text)` - Enter password
- `clickLoginButton()` - Click login button

### SauceDemoInventoryPage
Handles product inventory page interactions:
- `addFirstItemToCart()` - Add first product to cart
- `getCartBadgeCount()` - Get cart item count
- `goToCart()` - Navigate to cart page

### SauceDemoCartPage
Handles shopping cart page interactions:
- `getCartItemCount()` - Get number of items in cart
- `removeItemFromCart()` - Remove item from cart
- `isCartEmpty()` - Check if cart is empty

## Test Credentials

| Username | Password | Status |
|----------|----------|--------|
| standard_user | secret_sauce | Valid ✓ |
secret_sauce | Valid (Performance issues) |

## Running Tests with Maven

### Clean and Run All Tests
```bash
mvn clean test
```

### Run with Specific Browser Configuration
The tests are configured to use Firefox. To use Chrome or other browsers, modify the WebDriver initialization in `POMSauceDemoTests.java`.

### Generate Test Report
```bash
mvn surefire-report:report
```

Test reports are generated in `target/surefire-reports/`

## Best Practices Implemented

✅ **Page Object Model (POM)** - Separates test logic from page interactions  
✅ **Explicit Waits** - Uses WebDriverWait for element synchronization  
✅ **Descriptive Assertions** - Clear assertion messages for debugging  
✅ **Clean Code** - Follows Java naming conventions  
✅ **Reusable Methods** - Common actions encapsulated in page objects  
✅ **Resource Cleanup** - Proper driver cleanup with `quit()`  

## Troubleshooting

### Browser Connection Issues
Ensure Firefox is installed and compatible with the system.

### Test Timeouts
Increase timeout duration in WebDriverWait:
```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
```

## Contributing

To add new tests:
1. Create a new test method in `POMSauceDemoTests.java`
2. Create corresponding page object classes if needed
3. Follow the existing POM pattern
4. Run tests locally before committing

## Author

**Sita Karumudi**

## License

This project is for educational purposes as part of QA Testing Module 2 assignment.
