package BAITAP;
import driver.driverFactory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import model.pages.TC10_Page;

public class TC10 {
     @Test
     public void main() throws InterruptedException{
        WebDriver driver = driverFactory.getChromeDriver();
        TC10_Page page = new TC10_Page(driver);

        // Step 1: Go to backend login page
        driver.get("http://live.techpanda.org/index.php/backendlogin");

        // Step 2: Login with provided credentials
        page.login("user01", "guru99com");
        Thread.sleep(2000);
         // Step 3: Navigate to Sales -> Orders menu
         page.closeMsgBox();
         Thread.sleep(2000);
         page.goToOrders();
         Thread.sleep(2000);
        //Step 4 Input OrderId and FromDate -> ToDate
        page.Search("100000005", "Aug 23", "Aug 23, 2014 1:21:48 AM");
         //Step 5 Click Search button
        driver.findElement(By.xpath("//*[@title='Search']")).click();

          //Step 6 Take screen shot of the result
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path where you want to save the screenshot
        String screenshotPath = "C:/Study/Study/SWT/selenium-webdriver-java-master/src/test/java/BAITAP/images/TC10.png";

        try {
            // Copy the screenshot file to the desired location
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(2000);
         driver.quit();
    }
}
