import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DiscountTest {

    WebDriver driver = new ChromeDriver();

    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void checkDiscount() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=2&zip_code=111111&first_name=Test&last_name=Test&email=test%40test.com&password1=Test&password2=Test");
        String email = driver.findElement(By.xpath("//td[contains(text(), \"Email\")]/parent::tr//b")).getText();
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("1111");
        driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
        driver.get("https://www.sharelane.com/cgi-bin/add_to_cart.py?book_id=10");
        driver.get("https://www.sharelane.com/cgi-bin/shopping_cart.py");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("19");
        driver.findElement(By.xpath("//input[@value=\"Update\"]")).click();

        softAssert.assertEquals(Integer.parseInt(driver.findElement(By.xpath("//td[contains(text(), \"10.00\")]/parent::tr/td/p/b")).getText()) , 0);

        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("21");
        driver.findElement(By.xpath("//input[@value=\"Update\"]")).click();

        softAssert.assertEquals(Integer.parseInt(driver.findElement(By.xpath("//td[contains(text(), \"10.00\")]/parent::tr/td/p/b")).getText()) , 2);

        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("50");
        driver.findElement(By.xpath("//input[@value=\"Update\"]")).click();

        softAssert.assertEquals(Integer.parseInt(driver.findElement(By.xpath("//td[contains(text(), \"10.00\")]/parent::tr/td/p/b")).getText()) , 3);
        softAssert.assertAll();


    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
