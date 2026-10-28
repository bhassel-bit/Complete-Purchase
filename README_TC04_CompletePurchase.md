# TC04 – Complete Purchase Automation Test

## Overview
This Java Selenium WebDriver test automates a purchase checkout flow on the [Cloudberry Store](https://cloudberrystore.services/). It selects the **HP LP3065**, adds it to the cart, signs into an existing customer account, progresses through shipping and payment selections, confirms the order, and verifies that an order confirmation heading is displayed.

## Test Case Details
- **Test case ID:** TC04
- **Java class:** `day7.TC04_CompletePurchase`
- **Test type:** End-to-end functional UI automation / checkout flow
- **Browser:** Google Chrome
- **Test application:** Cloudberry Store
- **Expected result:** The confirmation page displays `Your order has been placed!`.

## Test Steps
1. Launch Chrome and open `https://cloudberrystore.services/`.
2. Maximize the browser window.
3. Navigate to **Laptops & Notebooks** and select **Show All Laptops & Notebooks**.
4. Scroll to and open the **HP LP3065** product.
5. Enter a delivery date five days after the current date, formatted as `MM/dd/yyyy`.
6. Add the product to the cart.
7. Navigate to **Checkout**.
8. Open the login page and sign in with an existing account.
9. Select the shipping address at dropdown index `1`.
10. Open the shipping-method options and continue.
11. Open the payment-method options and continue.
12. Click **Confirm Order**.
13. Verify that the heading `Your order has been placed!` is visible.
14. Close Chrome using `driver.quit()`.

## Tools and Technologies
- Java
- Selenium WebDriver and ChromeDriver
- XPath locators
- Selenium `Select` for the shipping-address dropdown
- JavaScriptExecutor for scrolling
- Java `LocalDate` and `DateTimeFormatter` for the delivery date

## Prerequisites
- JDK and Google Chrome installed
- Selenium Java dependencies configured
- ChromeDriver available through Selenium Manager or your WebDriver setup
- Internet access to the test website
- A valid customer account and an existing shipping address for the checkout flow
- A safe test environment and account authorized for placing orders

## How to Run
1. Place `TC04_CompletePurchase.java` in the `day7` package of a Java project with Selenium configured.
2. **Before sharing the source code, remove the hard-coded login credentials.** Load test credentials securely from environment variables or another secret-management mechanism.
3. Update the login steps to use those credentials and ensure the shipping address and available checkout options match the test account.
4. Run `TC04_CompletePurchase.java` as a **Java Application** in Eclipse or another Java IDE.
5. Review the console for the test result and verify the order was placed only in an approved test environment.

## Test Validation
The script checks whether the final order confirmation heading is displayed:

```java
boolean status = driver.findElement(
    By.xpath("//h1[normalize-space()='Your order has been placed!']")
).isDisplayed();

if (status) {
    System.out.println("Test Passed");
} else {
    System.out.println("Test Failed");
}
```

**Expected console output:** `Test Passed` when the confirmation heading appears. A missing heading may instead cause Selenium to throw an exception before the failure message is printed.

## Notes and Possible Enhancements
- The current Java file contains a hard-coded email address and password. Never commit real credentials to GitHub; remove them from the file and rotate the password if it has been exposed.
- The script selects the second shipping-address option by index; a stable value or visible-text selection is less brittle.
- The script assumes default shipping and payment options will be available and accepted.
- Consider explicit waits instead of fixed sleeps, assertions via JUnit/TestNG, and `try/finally` for browser cleanup.
- Verify product, totals, shipping method, and order details in addition to the final confirmation heading.
- Run purchase-confirmation tests only against a sandbox or approved account to avoid unintended orders.

## Source File
`TC04_CompletePurchase.java`
