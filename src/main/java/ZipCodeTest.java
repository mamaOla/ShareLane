import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ZipCodeTest {

    WebDriver driver = new ChromeDriver();

    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void enterWrongZipCode() throws InterruptedException {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("1111");

        driver.findElement(By.cssSelector("[value=\"Continue\"]")).click();

        String errorMessage = driver.findElement(By.cssSelector("[class=\"error_message\"]")).getText();

        Assert.assertEquals(errorMessage, "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void enterGoodZipCode() throws InterruptedException {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("11111");

        driver.findElement(By.cssSelector("[value=\"Continue\"]")).click();

        driver.findElement(By.name("first_name")).sendKeys("John");
        driver.findElement(By.name("last_name")).sendKeys("Doe");

        driver.findElement(By.name("email")).sendKeys("John.Doe@mail.com");

        driver.findElement(By.name("password1")).sendKeys("Johnny123");

        driver.findElement(By.name("password2")).sendKeys("Johnny123");

        driver.findElement(By.xpath("//input[@value=\"Register\"]")).click();

        String message = driver.findElement(By.xpath("//span[@class=\"confirmation_message\"]")).getText();
        Assert.assertEquals(message, "Account is created!");
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
