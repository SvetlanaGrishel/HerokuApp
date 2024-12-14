import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicControlTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//button[text()='Remove']")).click(); //кликнули на кнопку
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  //создаем ожидание
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))); //ждем 10 секунд появление сообщения
        //проверка, что чекбокса нет
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@type = 'checkbox']")));
        WebElement isFiledEnabled = driver.findElement(By.xpath("//input[@type = 'text']"));
        Assert.assertFalse(isFiledEnabled.isEnabled());
        driver.findElement(By.xpath("//button[text()='Enable']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type = 'text']")));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
