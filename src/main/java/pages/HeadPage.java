package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeadPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy (xpath = "//a[text() = 'Shopping']")
    WebElement shopping;

    public HeadPage(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void waitAndClick(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }


}
