import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkInputs() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        //check ARROW_DOWN
        driver.findElement(By.tagName("input")).sendKeys("100");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN);
        String resultArrowDown = driver.findElement(By.tagName("input")).getAttribute("value");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(resultArrowDown, "97", "The result is incorrect, please recheck");

        //check ARROW_UP
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP, Keys.ARROW_UP);
        String resultArrowUp = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(resultArrowUp, "99", "The result is incorrect, please recheck");

        //check not digits
        driver.findElement(By.tagName("input")).sendKeys("ABC");
        String resultLetters = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(resultLetters, "99", "Letters were entered into the input field");

        //check special symbols
        driver.findElement(By.tagName("input")).sendKeys("***___");
        String resultSpecialSymbols = driver.findElement(By.tagName("input")).getAttribute("value");
        softAssert.assertEquals(resultSpecialSymbols, "99", "Special symbols were entered into the input field");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
