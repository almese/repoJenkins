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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.InventoryPage;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inventario {
    WebDriver driver;

    @Before
    public void setUp() {

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



    }

    @Test
    public void validarNumelementos() {
        int productCount = driver.findElements(By.xpath("//div[@class='inventory_item']")).size();

        Assert.assertEquals("ERROR: El numero de elementos mostrados no es 6", 6, productCount);
    }

    @Test
    public void validarItem() {
        WebElement item = driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Bolt T-Shirt')]"));

        Assert.assertTrue("ERROR: El item buscado no se encuentra", item.isDisplayed());
    }

    @Test
    public void validarAñadirCarrito() {
        WebElement añadirCarrito = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        añadirCarrito.click();


        WebElement carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        String valorCarrito = carrito.getText();

        Assert.assertEquals("ERROR: El item no se ha añadido al carrito", 1, Integer.parseInt(valorCarrito));
    }

    @Test
    public void validarEliminarCarrito() {
        WebElement añadirCarrito = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']"));
        añadirCarrito.click();


        WebElement removeButton = driver.findElement(By.xpath("//button[@id='remove-sauce-labs-bolt-t-shirt']"));
        removeButton.click();


        boolean carrito = driver.findElements(By.xpath("//span[@class='shopping_cart_badge']")).isEmpty();

        if (carrito) {
            System.out.println("El carrito se ha vaciado correctamente");
        } else {
            System.out.println("El carrito no se ha vaciado");
        }
    }

    @Test
    public void validarAñadirVarios() {

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
            int randomIndex = random.nextInt(items.size());

            WebElement item = items.get(randomIndex);

            item.findElement(By.xpath("//button[text()='Add to cart']")).click();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException error) {
                System.out.println(error);
            }

        }

        WebElement carrito = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
        String valorCarrito = carrito.getText();

        Assert.assertEquals("ERROR: Los items no se han añadido al carrito", 3, Integer.parseInt(valorCarrito));
    }

    @Test
    public void validarOrdenarAZ() throws InterruptedException {
        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.selectSortOption("Name (Z to A)");



        Thread.sleep(5000);
/*
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[text()='Name (Z to A)']")).click();

        String nombreItem = "Z";
        for (WebElement product : driver.findElements(By.xpath("//div[@class='inventory_item_name']"))) {
            String nuevoItem = product.getText();
            System.out.println(nuevoItem);
            Assert.assertTrue("ERROR: El filtro no ha ordenado correctamente los productos", nombreItem.compareTo(nuevoItem) >= 0);
            nombreItem = nuevoItem;
        }
*/
    }

    @Test
    public void validarOrdenarPrecio() {
        driver.findElement(By.xpath("//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath("//option[text()='Price (low to high)']")).click();

        double precioItem = 0;
        for (WebElement product : driver.findElements(By.xpath("//div[@class='inventory_item_price']"))) {
            String precio = product.getText().substring(1); //Esto quita el simbolo del dolar
            double nuevoPrecio = Double.parseDouble(precio);
            Assert.assertTrue("ERROR: El filtro no ha ordenado correctamente los productos", nuevoPrecio >= precioItem);
            precioItem = nuevoPrecio;
        }

    }

    @Test
    public void validarEliminarProducto() {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
            int randomIndex = random.nextInt(items.size());
            WebElement item = items.get(randomIndex);
            item.findElement(By.xpath("//button[text()='Add to cart']")).click();
        }


        driver.findElement(By.xpath("//a[contains(@class, 'shopping_cart')]")).click();


        List<WebElement> carrito = driver.findElements(By.xpath("//div[@class='cart_item']"));
        carrito.get(1).findElement(By.xpath(".//button[text()='Remove']")).click();


        Assert.assertEquals("ERROR: El elemento no se ha borrado del carrito", "1", driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText());


    }

    @Test
    public void validarPrecioCheckout() {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
            int randomIndex = random.nextInt(items.size());
            WebElement item = items.get(randomIndex);
            item.findElement(By.xpath("//button[text()='Add to cart']")).click();
        }


        driver.findElement(By.xpath("//a[contains(@class, 'shopping_cart')]")).click();


        driver.findElement(By.xpath("//button[text()='Checkout']")).click();


        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("NOMBRE");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("APELLIDO");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();


        WebElement subtotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        WebElement tax = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        WebElement total = driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']"));

        String subTotalText = subtotal.getText().substring(13);
        String taxText = tax.getText().substring(5);
        String totalText = total.getText().substring(7);

        List<WebElement> itemsPrices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        List<Double> itemPricesDouble = new ArrayList<Double>();
        for (WebElement price : itemsPrices) {
            itemPricesDouble.add(Double.parseDouble(price.getText().replace("$", "")));
        }

        double suma = 0;
        for (double price : itemPricesDouble) {
            suma += price;
        }


        double expectedTax = suma * 0.08;
        double expectedTotal = suma + expectedTax;

        Assert.assertEquals("ERROR: El subtotal no se ha sumado correctamente", String.valueOf(Math.round(suma * 100.0) / 100.0), subTotalText);
        Assert.assertEquals("ERROR: La suma de impuestos no se ha calculado correctamente", "$" + Math.round(expectedTax * 100.0) / 100.0, taxText);
        Assert.assertEquals("ERROR: El total no se ha calculado correctamente", "$" + Math.round(expectedTotal * 100.0) / 100.0, totalText);
    }

    @Test
    public void validarPedido() {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            List<WebElement> items = driver.findElements(By.xpath("//div[@class='inventory_item']"));
            int randomIndex = random.nextInt(items.size());
            WebElement item = items.get(randomIndex);
            item.findElement(By.xpath("//button[text()='Add to cart']")).click();
        }


        driver.findElement(By.xpath("//a[contains(@class, 'shopping_cart')]")).click();


        driver.findElement(By.xpath("//button[text()='Checkout']")).click();


        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("NOMBRE");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("APELLIDO");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.findElement(By.xpath("//button[text()='Finish']")).click();

        String mensaje = driver.findElement(By.xpath("//div[@class='complete-text']")).getText();

        Assert.assertEquals("ERROR: El mensaje no se muestra correctamente", mensaje, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");


    }

    @Test
    public void validarLogOut() {
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException error) {
            System.out.println(error);
        }

        driver.findElement(By.xpath("//a[@id='logout_sidebar_link']")).click();
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals("ERROR: No ha funcionado el logout correctamente",currentUrl,"https://www.saucedemo.com/");
    }
    @Test
    public void valiarDrag() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        Actions actions = new Actions(driver);

        WebElement perfil= driver.findElement(By.xpath("//*[@id='content']/div/div[3]/img"));
        actions.moveToElement(perfil).perform();




        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement viewProfile = driver.findElement(By.xpath("//*[@id='content']/div/div[3]/div/a"));
        actions.click(viewProfile).perform();

        // Esperar a que la página cargue completamente
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("COMPLETADO");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("ERROR: No estamos en la pagina correspodiente.","https://the-internet.herokuapp.com/users/3",currentUrl);
    }

    @After
    public void tearDown() {
        // Cierra el navegador
        driver.close();
    }
}
