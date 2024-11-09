import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkAddRemoveElement() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        driver.findElement(By.xpath("//button[text() = 'Add Element']")).click();
        driver.findElement(By.xpath("//button[text() = 'Add Element']")).click();
        List<WebElement> buttonDelete = driver.findElements(By.xpath("//button[text() = 'Delete']"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(buttonDelete.size(), 2);
        buttonDelete.get(1).click();
        List<WebElement> buttonDeleteAfterDelete = driver.findElements(By.xpath("//button[text() = 'Delete']"));
        softAssert.assertEquals(buttonDeleteAfterDelete.size(), 1);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
