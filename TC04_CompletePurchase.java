package day7;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC04_CompletePurchase {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * 4th Test case TC04_CompletePurchase ------- 
		 * 1. Launch Browser (chrome) 
		 * 2. Open URL https://cloudberrystore.services/ 
		 * 3. Click on Laptops and NoteBooks
		 * 4. Click on Show all Laptops and NoteBooks 
		 * 5. Select an item "HP LP3065" 
		 * 6. Set Delivery Date 
		 * 7. Click on Add to Cart 
		 * 8. Go to Checkount 
		 * 9. Login 
		 * 10. Complete Checkout forms 
		 * 11. Confirm order 
		 * 12. Validate Order Confirmation 
		 * 13. Close WebDriver
		 */
		
		// 1. Launch Browser (chrome)
		WebDriver driver = new ChromeDriver(); // open the chrome
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicitly Wait

		// 2. Open URL https://cloudberrystore.services/
		driver.get("https://cloudberrystore.services/");
		driver.manage().window().maximize();

		// 3. Click on Laptops and NoteBooks.
		driver.findElement(By.xpath("//a[normalize-space()='Laptops & Notebooks']")).click();

		// 4. Click on Show all Laptops and NoteBooks
		driver.findElement(By.xpath("//a[normalize-space()='Show All Laptops & Notebooks']")).click();

		// 5. Select an item "HP LP3065"
		WebElement elementHP = driver
				.findElement(By.xpath("//div[@class='description']//a[contains(text(),'HP LP3065')]"));

		// Scroll to view and click
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", elementHP);
		// Add a small wait
		Thread.sleep(1000);
		elementHP.click(); // click on element

		// 6. Set Delivery Date
		LocalDate CurrentDate = LocalDate.now();
		// System.out.println("Current Date: " + CurrentDate);

		// Add 5 days
		LocalDate DeliveryDate = CurrentDate.plusDays(5);
		// System.out.println(DeliveryDate);

		// Convert to format MM/dd/yyyy
		String formattedDeliveryDate = DeliveryDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		// System.out.println(formattedDeliveryDate);

		driver.findElement(By.xpath("//input[@id='input-option-225']")).sendKeys(formattedDeliveryDate);

		// 7.Click on Add to Cart
		WebElement elementAddToCart = driver.findElement(By.xpath("//button[@id='button-cart']"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", elementAddToCart);
		// Add a small wait
		Thread.sleep(1000);
		elementAddToCart.click();

		// 8. Go to Checkout
		WebElement elementCheckout = driver
				.findElement(By.xpath("//a[@title='Checkout']//i[@class='fa-solid fa-share']"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", elementCheckout);
		// Add a small wait
		Thread.sleep(1000);
		elementCheckout.click(); // click on element

		// 9. Login Page
		driver.findElement(By.xpath("//strong[normalize-space()='login page']")).click();

		// Enter Valid Email and Password

		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("brookhassel@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("120kbeforejan");
		
		//Click Login
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

		// 10. Complete Checkout Forms
		Select select = new Select(driver.findElement(By.xpath("//select[@id='input-shipping-address']")));
		
		select.selectByIndex(1);
		
		// Select Shipping Method
		driver.findElement(By.xpath("//button[@id='button-shipping-methods']")).click();
		
		// Click continue
		driver.findElement(By.xpath("//button[@id='button-shipping-method']")).click();
		
		// Select payment method
		driver.findElement(By.xpath("//button[@id='button-payment-methods']")).click();
		driver.findElement(By.xpath("//button[@id='button-payment-method']")).click();
		
		// 11.Confirm Order	
		WebElement elementConfirmOrder = driver.findElement(By.xpath("//button[@id='button-confirm']"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", elementConfirmOrder);
		// Add a small wait
		Thread.sleep(1000);
		elementConfirmOrder.click();
		
		// 12. Validate Order Confirmation
		boolean Status = driver.findElement(By.xpath("//h1[normalize-space()='Your order has been placed!']")).isDisplayed();
		
		if (Status == true) {
			System.out.println("Test Passed");
		}	else	{
			System.out.println("Test Failed");
		}
		
		// 13. Close WebDriver
		
		driver.quit();
		
		
		

	}
	
		

}
