package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends AbstractPage{
    private WebDriver driver;
    @FindBy(id = "contents_wrapper")
    private WebElement cartContents;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebElement getPageLoadedTestElement() {
        return cartContents;
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }

    public void removeItemFromCart(String itemName) {
        WebElement itemRow = driver.findElement(By.xpath("//div[contains(@class,'cart_item')]//*[text()='" + itemName + "']/ancestor::div[contains(@class,'cart_item')]"));
        itemRow.findElement(By.cssSelector(".cart_button")).click();
    }

    public String getItemPrice(String itemName) {
        WebElement itemRow = driver.findElement(By.xpath("//div[contains(@class,'cart_item')]//*[text()='" + itemName + "']/ancestor::div[contains(@class,'cart_item')]"));
        return itemRow.findElement(By.cssSelector(".inventory_item_price")).getText();
    }

    public String getItemDescription(String itemName) {
        WebElement itemRow = driver.findElement(By.xpath("//div[contains(@class,'cart_item')]//*[text()='" + itemName + "']/ancestor::div[contains(@class,'cart_item')]"));
        return itemRow.findElement(By.cssSelector(".inventory_item_desc")).getText();
    }

    public String getItemQuantity(String itemName) {

        WebElement itemRow = driver.findElement(By.xpath("//div[contains(@class,'cart_item')]//*[text()='" + itemName + "']/ancestor::div[contains(@class,'cart_item')]"));
        return itemRow.findElement(By.cssSelector(".cart_quantity")).getText();
    }
}
