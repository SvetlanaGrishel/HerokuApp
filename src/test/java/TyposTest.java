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

public class TyposTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkTypos() {
        driver.get("http://the-internet.herokuapp.com/typos");
        SoftAssert softAssert = new SoftAssert();
        String referenceString = "Sometimes you'll see a typo, other times you won't.";
        int quantityOfRefresh = 5;

        for (int i = 0; i < quantityOfRefresh; i++) {
            driver.navigate().refresh();
            List<WebElement> textFromPage = driver.findElements(By.tagName("p")); //тут у нас 2 элемента: 1 и 2
            String textForCheck = textFromPage.get(1).getText();
            System.out.println("Cycle: " + i + ", text from page is: " + textForCheck);
            softAssert.assertEquals(textForCheck, referenceString, "The typo is detected in the word 'won't'");
        }
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
