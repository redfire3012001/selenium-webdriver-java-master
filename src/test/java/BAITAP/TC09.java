package BAITAP;

import java.time.Duration;
import driver.driverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC09 {
    @Test
    public void main() throws InterruptedException{
        WebDriver driver = driverFactory.getChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

         //Step 1: get Link
         driver.get("http://live.techpanda.org/");
         // Step 2: Click on MOBILE menu and add IPHONE to cart
        driver.findElement(By.linkText("MOBILE")).click();
        driver.findElement(By.xpath("//a[contains(text(),'IPhone')]/following::button[@title='Add to Cart'][1]")).click();
        // Step 3:  Enter Coupon Code
        WebElement grandTotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tfoot"));
        String originalGrandTotal = grandTotal.getText();
        System.out.println("Orginal: "+ originalGrandTotal);
        WebElement couponInput = driver.findElement(By.xpath("//*[@id='coupon_code']"));
            couponInput.clear();
            couponInput.sendKeys("GURU50");
        driver.findElement(By.xpath("//*[@id='discount-coupon-form']/div/div/div/div/button")).click();
        Thread.sleep(2000);
        
        // Step 4:  Verify the discount generated
        WebElement newgrandTotal = driver.findElement(By.xpath("//*[@id='shopping-cart-totals-table']/tfoot"));
        String updatedGrandTotal = newgrandTotal.getText();
        System.out.println("After Discount: "+ updatedGrandTotal);
        Thread.sleep(2000);
        Assert.assertNotEquals(originalGrandTotal, updatedGrandTotal, "Grand Total should not be equal");
        Thread.sleep(2000);
        driver.quit();
    }
    

}
