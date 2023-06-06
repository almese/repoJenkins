package junit.almese;

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

public class Login {
    WebDriver driver;
    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions chrome = new ChromeOptions();

        driver = new ChromeDriver(chrome);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");



    }

    @Test
    public void loginCorrecto() {
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("standard_user");


        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");


        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();

        Assert.assertEquals("ERROR: No estamos en la pagina correcta","https://www.saucedemo.com/inventory.html",driver.getCurrentUrl());

    }

    @Test
    public void loginIncorrecto() {
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("standard_use");


        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");


        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();

        WebElement mensajeError;

        try {
            mensajeError = driver.findElement(By.cssSelector("[data-test='error']"));
        } catch (NoSuchElementException error) {
            Assert.fail("ERROR: El mensaje de Login Incorrecto no se muestra");
        }

    }

    @After
    public void tearDown() {
        // Cierra el navegador
        driver.close();
    }
}
