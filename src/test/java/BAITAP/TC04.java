package BAITAP;

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

public class TC04 {
    @Test
    public void verifyProductComparison() throws InterruptedException {
        // Create a WebDriver instance
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on "MOBILE" menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3: Click on "Add To Compare" for Sony Xperia and iPhone
        WebElement sonyXperiaCompare = driver.findElement(
                By.xpath("//a[contains(text(),'Sony Xperia')]/following::a[contains(text(),'Add to Compare')]"));
        sonyXperiaCompare.click();
        WebElement iphoneCompare = driver.findElement(
                By.xpath("//a[contains(text(),'IPhone')]/following::a[contains(text(),'Add to Compare')]"));
        iphoneCompare.click();

        // Step 4: Click on "COMPARE" button
        WebElement compareButton = driver.findElement(By.cssSelector("button[title='Compare']"));
        compareButton.click();

        // Step 5: Verify the pop-up window and check the products
        // (You might need to handle the pop-up window here)

        // Step 6: Close the Popup Window
        driver.close();

        // Switch back to the main window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path where you want to save the screenshot
        String screenshotPath = "C:\\Study\\Study\\SWT\\selenium-webdriver-java-master\\src\\test\\java\\BAITAP\\images\\TC4.png";

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
