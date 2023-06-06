package selenium.almese;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class VisibilidadBotonRemove {

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



        WebElement añadirCarrito = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']"));
        añadirCarrito.click();



        WebElement removeButton = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-onesie']"));
        if (removeButton.isDisplayed()) {
            System.out.println("El botón de Remove es visible");
        } else {
            System.out.println("El botón de Remove no es visible");
        }


        Thread.sleep(5000);
        driver.close();
    }

}
