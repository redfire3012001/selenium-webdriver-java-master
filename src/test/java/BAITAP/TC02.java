package BAITAP;

import org.testng.annotations.Test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TC02 {
    @Test
    public void main() {

        // Create a WebDriver instance
        WebDriver driver = driverFactory.getChromeDriver();

        // Step 1: get Link
        driver.get("http://live.techpanda.org/");

        // Step bonus: Verify Title of the page
        String expectedTitle = "TechPanda";
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Verification successfully");
        } else {
            System.out.println("Verification failed");
        }
        // Step 2: Click on MOBILE menu
        WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
        mobileMenu.click();
        // Step 3: Read the cost of Sony Xperia mobile
        String listPageCost = driver.findElement(By.xpath("//h2[contains(@class, 'product-name')]/a[text()='Sony Xperia']/parent::h2/following-sibling::div/span[@class='regular-price']/span[@class='price']")).getText();

        // Step 4: Click on Sony Xperia mobile
        driver.findElement(By.linkText("SONY XPERIA")).click();

         // Step 5: Read the Sony Xperia mobile cost from the detail page
         String detailPageCost = driver.findElement(By.cssSelector("span#product-price-1")).getText();

         // Step 6: Compare the product cost in the list and details page
         assert listPageCost.equals(detailPageCost) : "Product cost mismatch";
         driver.quit();

    }
}
