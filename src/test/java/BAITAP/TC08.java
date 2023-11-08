package BAITAP;

import driver.driverFactory;
import model.pages.TC06_CartPage;
import model.pages.TC06_CheckOutPage;
import model.pages.TC06_LoginPage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TC08 {
    @Test
    public void main() throws InterruptedException {
        WebDriver driver = driverFactory.getChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        TC06_CheckOutPage checkoutPage = new TC06_CheckOutPage(driver);
        TC06_LoginPage loginPage = new TC06_LoginPage(driver);
        TC06_CartPage cartPage = new TC06_CartPage(driver);
        //Step 1  Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");
        Thread.sleep(2000);
        //Step 2  Click on my account link
        loginPage.clickOnMyAccountLink();
        Thread.sleep(2000);
        //Step 3 Login in application using previously created credential
        loginPage.login("nguyenngoctuananh3006@gmail.com", "123456");
        Thread.sleep(2000);
         
        //Step 4 Click on 'REORDER' link , change QTY & click Update
        driver.findElement(By.partialLinkText("MY ORDERS")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id='my-orders-table']/tbody/tr/td[6]/span/a[2]")).click();
        WebElement grandTotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tfoot"));
        String originalGrandTotal = grandTotal.getText();
        Thread.sleep(2000);
        WebElement qtyInput = driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input"));
            qtyInput.clear();
            qtyInput.sendKeys("10");
        driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/button")).click();

        //Step 5 Verify Grand Total is changed
        WebElement newgrandTotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tfoot"));
        String updatedGrandTotal = newgrandTotal.getText();
        Thread.sleep(2000);
        Assert.assertNotEquals(originalGrandTotal, updatedGrandTotal, "Grand Total should not be equal");
        //Step 6 Complete Billing & Shipping Information
          //Enter general shipping country, state/province and zip for the shipping cost estimate
          cartPage.enterShippingInformation("United States", "New York", "123456");
          Thread.sleep(2000);
  
          //Click Estimate
          cartPage.clickOnEstimateLink();
          Thread.sleep(2000);
  
          //Verify Shipping cost generated
          String shippingCost = cartPage.getShippingCost();
          System.out.println("Shipping cost: " + shippingCost);
          
          //Select Shipping Cost, Update Total
          cartPage.selectShippingCost();
          cartPage.updateTotalCost();
          Thread.sleep(2000);
          //Verify shipping cost is added to total
          String totalCost = cartPage.getTotalCost();
          System.out.println("Total cost: " + totalCost);
          Thread.sleep(2000);
  
          //Click "Proceed to Checkout"            
          checkoutPage.clickProceedToCheckout();
          Thread.sleep(2000);
  
          //Enter Billing Information, and click Continue
          checkoutPage.selectNewAddress("New Address");
          checkoutPage.enterBillingInformation( "Tuan Anh",
                   "Nguyen", "FPT", "24rtr",
                  "LVV", "THUDUC", "New York", "123456",
                  "United States", "1234567890", "654321");
         
           
          checkoutPage.selectdiffent();
          Thread.sleep(2000);
          //Enter Shipping Information, and click Continue
          driver.findElement(By.xpath("//*[@id='opc-shipping']/div[1]")).click();
          checkoutPage.selectNewSAddress("New Address");
          checkoutPage.enterShippingInformation( "Tuan Anh",
                   "Nguyen", "Sango", "addd",
                  "Nani", "San Diego", "New York", "123457",
                  "United States", "999231890", "650901");
          Thread.sleep(2000);
          //Enter Shipping Information, and click Continue
          checkoutPage.selectShippingMethod();
          Thread.sleep(2000);
          //In Payment Information select 'Check/Money Order' radio button. Click Continue
          checkoutPage.selectPaymentMethod();
          Thread.sleep(2000);
          
          checkoutPage.clickPaymentInfo();
          Thread.sleep(2000);
          //Click 'PLACE ORDER' button
          checkoutPage.clickPlaceOrder();
  
          String expectedMessage = "THANK YOU FOR YOUR PURCHASE!";
          String actualMessage = checkoutPage.getOrderRecievedMessage();
          assert actualMessage.equals(expectedMessage) : "Order failed!";
          System.out.println("Order sent succeed");
          Thread.sleep(2000);
          //Step 16 Verify Oder is generated. Note the order number
          String orderNumber = checkoutPage.getOrderNumber();
          System.out.println(orderNumber);
        // // Take screen shot of the result
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define the path where you want to save the screenshot
        String screenshotPath = "C:/Study/Study/SWT/selenium-webdriver-java-master/src/test/java/BAITAP/images/TC08.png";

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