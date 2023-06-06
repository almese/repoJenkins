package pages;

import org.openqa.selenium.WebDriver;

public class PagesFactory {
    private static PagesFactory pagesFactories;

    private final WebDriver driver;

    private final LoginPage loginPage;

    private final CartPage cartPage;

    private PagesFactory(WebDriver driver){
        this.driver = driver;
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);

    }

    public static void start(WebDriver driver){
        pagesFactories = new PagesFactory(driver);
    }

    public WebDriver getDriver(){ return driver;}
}
