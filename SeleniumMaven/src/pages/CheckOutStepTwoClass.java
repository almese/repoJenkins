package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutStepTwoClass extends AbstractPage{

    @FindBy(id = "cart_contents_container")
    private WebElement cartList;

    @FindBy(id = "checkout_summary_container")
    private WebElement checkoutInfo;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckOutStepTwoClass(WebDriver driver) {
        super(driver);
    }


    @Override
    public WebElement getPageLoadedTestElement() {
        return cartList;
    }

    public String getCheckoutInfo() {
        return wait.until(ExpectedConditions.visibilityOf(checkoutInfo)).getText();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }
}
