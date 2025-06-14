
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ZipCodeTest {

   @Test
   public void test () {

       WebDriver driver = new ChromeDriver();
       driver.get("https://sharelane.com/cgi-bin/register.py");
       driver.findElement(By.name("zip_code")).sendKeys("1111");
       driver.findElement(By.cssSelector("[value=Continue]")).click();

       String errorMessage = driver.findElement(By.cssSelector("[class=error_message]")).getText();
       Assert.assertEquals(errorMessage,"Oops, error on page. ZIP code should have 5 digits");

       driver.quit();

    }
}
