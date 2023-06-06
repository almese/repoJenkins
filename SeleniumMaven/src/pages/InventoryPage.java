package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InventoryPage extends AbstractPage{
    private WebDriver driver;
    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(css = ".btn_primary.btn_inventory")
    private List<WebElement> addButtons;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public WebElement getPageLoadedTestElement() {
        return sortDropdown;
    }

    public void selectSortOption(String option) {
        System.out.println(option);

        wait.until(ExpectedConditions.elementToBeClickable(sortDropdown)).click();
        driver.findElement(By.xpath("//option[text()='" + option + "']")).click();

    }

    public int getProductCount() {
        return productNames.size();
    }

    public String getProductName(int index) {
        return productNames.get(index).getText();
    }

    public String getProductPrice(int index) {
        return productPrices.get(index).getText();
    }

    public void clickAddButton(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(addButtons.get(index))).click();
    }
}
