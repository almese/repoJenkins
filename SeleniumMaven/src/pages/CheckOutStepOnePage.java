package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutStepOnePage extends AbstractPage {
    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public CheckOutStepOnePage(WebDriver driver) {
        super(driver);
    }


    @Override
    public WebElement getPageLoadedTestElement() {
        return firstNameInput;
    }

    public void fillOutPersonalInfo(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOf(postalCodeInput)).sendKeys(postalCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }
}
