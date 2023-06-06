package selenium.almese;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Main {

    public static void main(String[] args) throws InterruptedException{



        WebDriver driver2;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chrome2 = new ChromeOptions();

        driver2 = new ChromeDriver(chrome2);
        driver2.manage().window().maximize();

        driver2.get("https://www.saucedemo.com/");

        driver2.findElement(By.id("user-name")).sendKeys("standard_use");

        driver2.findElement(By.id("password")).sendKeys("secret_sauce");

        driver2.findElement(By.id("login-button")).click();

        String error = driver2.findElement(By.cssSelector("[data-test='error']")).getText();

        if (error.equals("Epic sadface: Username and password do not match any user in this service")) {
            System.out.println("El mensaje de error es correcto: " + error);
        } else {
            System.out.println("El mensaje de error es incorrecto: " + error);
        }

        Thread.sleep(5000);
        driver2.close();
    }
}
