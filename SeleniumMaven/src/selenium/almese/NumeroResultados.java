package selenium.almese;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NumeroResultados {

    public static void main(String[] args) throws InterruptedException{

        WebDriver driver;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chrome = new ChromeOptions();

        driver = new ChromeDriver(chrome);
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");


        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("standard_user");


        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");


        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();

        int productCount = driver.findElements(By.xpath("//div[@class='inventory_item']"))
                .size();

        if (productCount == 6) {
            System.out.println("La cantidad de productos mostrados es igual a 6");
        } else {
            System.out.println("La cantidad de productos mostrados es diferente de 6");
        }

        Thread.sleep(5000);
        driver.close();
    }

}
