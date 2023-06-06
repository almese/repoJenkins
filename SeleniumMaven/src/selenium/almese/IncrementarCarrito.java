package selenium.almese;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class IncrementarCarrito {

    public static void main(String[] args) throws InterruptedException {

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


        WebElement añadirCarrito = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        añadirCarrito.click();


        WebElement carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        String valorCarrito = carrito.getText();


        if (valorCarrito.equals("1")) {
            System.out.println("El producto se ha agregado correctamente al carrito");
        } else {
            System.out.println("El producto no se ha agregado correctamente al carrito");
        }


        Thread.sleep(5000);
        driver.close();
    }

}
