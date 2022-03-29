package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends HeadPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[text() = 'You’ve been signed in, welcome back!']")
    WebElement welcome;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(welcome));
        wait.until(ExpectedConditions.invisibilityOf(welcome));
    }

    public void NavigateToShoppingTab() {
        waitAndClick(shopping);
    }

}
