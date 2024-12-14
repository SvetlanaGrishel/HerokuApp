import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkFrames() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        driver.findElement(By.xpath("/html/body/div[4]/div/div/button")).click();
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr"))); //переключени на iframe
        WebElement iframe = driver.findElement(By.xpath("//p[text()='Your content goes here.']"));
        String iframeText = iframe.getText();
        Assert.assertEquals(iframeText, "Your content goes here.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
