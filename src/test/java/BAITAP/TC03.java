package BAITAP;

import org.testng.Assert;
import org.testng.annotations.Test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TC03 {
    @Test
    public void verifyProductQuantityInCart() {
        // Create a WebDriver instance
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        // Step 2: Click on "MOBILE" menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();

        // Step 3: Click on "ADD TO CART" for Sony Xperia mobile
        WebElement addToCartButton = driver.findElement(
                By.xpath("//a[contains(text(),'Sony Xperia')]/following::button[@title='Add to Cart'][1]"));
        addToCartButton.click();

        // Step 4: Change "QTY" value to 1000 and click "UPDATE" button
        WebElement qtyInput = driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input"));
        qtyInput.clear();
        qtyInput.sendKeys("1000");
        WebElement updateButton = driver.findElement(By.cssSelector("button[title='Update']"));
        updateButton.click();

        // Step 5: Verify the error message
        WebElement errorMessage = driver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[2]/p"));
        String actualMessage = errorMessage.getText();
        String expectedMessage = "* The requested quantity for \"Sony Xperia\" is not available.";
        Assert.assertEquals(actualMessage, expectedMessage, "Error message verification failed");
        // Step 6: Click on "EMPTY CART" link
        WebElement emptyCartLink = driver.findElement(By.xpath(".//*[@id='empty_cart_button']"));
        emptyCartLink.click();
  
        // Step 7: Verify cart is empty
        String noItemMsg = ("You have no items in your shopping cart.");
        String NoItem = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]")).getText();
        Assert.assertEquals(noItemMsg, NoItem, "Error message verification failed");
        // Close the browser
        driver.quit();
    }
}
