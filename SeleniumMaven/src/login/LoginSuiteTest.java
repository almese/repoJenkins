package login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuiteTest {
    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement mensajeError;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chrome = new ChromeOptions();
        driver = new ChromeDriver(chrome);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void loginCorrecto() {
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        Assert.assertEquals("ERROR: No estamos en la pagina correcta", "https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl());
    }

    @Test
    public void loginIncorrecto() {
        username.sendKeys("standard_use");
        password.sendKeys("secret_sauce");
        loginButton.click();


            Assert.assertTrue("ERROR: El mensaje de Login Incorrecto no se muestra", mensajeError.isDisplayed());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
