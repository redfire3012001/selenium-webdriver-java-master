package BAITAP;

import model.pages.RegisterPage;

import org.testng.Assert;
import org.testng.annotations.Test;
import driver.driverFactory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TC05 {
    @Test
    public void createAccountAndShareWishlist() throws InterruptedException {
        // Create a WebDriver instance
        String firstName = "Tuan Anh";
        String lastname = "Nguyen";
        String email_address = "nguyenngoctuananh3001@gmail.com";
        String password = "123456";
        String confirmPassWord = "123456";
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Create a RegisterPage object
        RegisterPage registerPage = new RegisterPage(driver);

        // Step 2: Click on "MY ACCOUNT" link
        registerPage.clickMyAccount();

        // Step 3: Click "CREATE AN ACCOUNT" link
        registerPage.clickCreateAccount();

        // Fill in registration details
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastname);
        registerPage.enterEmail(email_address);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassWord);

        // Step 4: Click "Register"
        registerPage.clickRegisterButton();
        // Switch back to the main window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Step 5: Verify Registration is done. Expected account registration done.
        String Welcomev = ("WELCOME, " + firstName.toUpperCase() + " " + lastname.toUpperCase() + "!");
        String Welcomet = driver.findElement(By.xpath("//div[1]/p[1]")).getText();
        System.out.println("Welcomet = " + Welcomet);
        try {
            Assert.assertEquals(Welcomet, Welcomev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Step 6: Go to TV menu
        driver.findElement(By.xpath(".//*[@id='nav']/ol/li[2]/a")).click();

        Thread.sleep(2000);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Step 7: Add product to your wishlist
        driver.findElement(By.xpath("//a[@class='link-wishlist']")).click();

        Thread.sleep(2000);

        // Handle the new window that opens
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Step 8: Click SHARE WISHLIST
        driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

        Thread.sleep(2000);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Step 9: In the next page, enter Email and a message and click "SHARE
        // WISHLIST"
        registerPage.enterEmail(email_address); 
        String messageToShare = "Check out my wishlist!";
        driver.findElement(By.id("message")).sendKeys(messageToShare);
        driver.findElement(By.xpath("//span[contains(text(),'Share Wishlist')]")).click();

        Thread.sleep(2000);
        // Switch to a new window (if any)
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Step 10: Check wishlist is shared. Expected wishlist shared successfully.
        WebElement successMessageElement = driver
                .findElement(By.xpath("//li/span[contains(text(),'Your Wishlist has been shared.')]"));
        Assert.assertTrue(successMessageElement.isDisplayed());
         File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path where you want to save the screenshot
        String screenshotPath = "C:\\Study\\Study\\SWT\\selenium-webdriver-java-master\\src\\test\\java\\BAITAP\\images\\TC5.png";

        try {
            // Copy the screenshot file to the desired location
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
        // Close the browser
        driver.quit();
    }

}
