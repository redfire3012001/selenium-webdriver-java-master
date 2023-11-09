package BAITAP;

import driver.driverFactory;
import model.pages.TC06_LoginPage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TC07 {
    @Test
    public void main() throws InterruptedException {
        WebDriver driver = driverFactory.getChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
        TC06_LoginPage loginPage = new TC06_LoginPage(driver);
        //Step 1  Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");
        Thread.sleep(2000);
        //Step 2  Click on my account link
        loginPage.clickOnMyAccountLink();
        Thread.sleep(2000);
        //Step 3 Login in application using previously created credential
        loginPage.login("nguyenngoctuananh3007@gmail.com", "123456");
        Thread.sleep(2000);
        //Step 4 Click on MY ORDERS link
        driver.findElement(By.partialLinkText("MY ORDERS")).click();
        Thread.sleep(2000);

        //Step 5 Click VIEW ORDER link
        driver.findElement(By.xpath("//*[@id='my-orders-table']/tbody/tr/td[6]/span/a[1]")).click();
        Thread.sleep(2000);

        //Step 6 Click PRINT ORDER
        driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div[1]/a[2]")).click();
        Thread.sleep(2000);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Take screen shot of the result
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path where you want to save the screenshot
        String screenshotPath = "C:/Study/Study/SWT/selenium-webdriver-java-master/src/test/java/BAITAP/images/TC07.png";

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