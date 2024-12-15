import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropDownTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkDropDown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropDown);
        List<WebElement> options = select.getOptions(); //получить все вкладки, которые находятся в этом списке
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(options.get(0).getText(), "Please select an option");
        softAssert.assertEquals(options.get(1).getText(), "Option 1");
        softAssert.assertEquals(options.get(2).getText(), "Option 2");
        select.selectByVisibleText("Option 1");
        softAssert.assertTrue(select.getFirstSelectedOption().isSelected()); //берем уже выбранный элемент
        softAssert.assertAll();
    }

    @Test
    public void clickDropDownOptions() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        Actions actions = new Actions(driver); //создаем экземпляр
        actions.click(dropDown).perform();
        actions.doubleClick().perform(); //только через actions можно
        actions.contextClick(dropDown).perform(); //клик правой кнопкой мыши
        actions.clickAndHold(dropDown).perform(); //нажать и ужержать левую кнопку мыши на элементе
        actions.clickAndHold(dropDown).release().perform(); //нажать, удержать и отпустить
        actions.moveToElement(dropDown).perform(); //навести мышкой на элемент
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
