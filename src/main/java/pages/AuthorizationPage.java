package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage extends HeadPage{

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy (name = "username")
    WebElement Username;

    @FindBy (xpath = "//button[@data-testid='auth-continue-button']")
    WebElement ContinueButton;

    @FindBy (name = "password")
    WebElement Password;

    @FindBy (xpath = "//button[@data-testid='auth-login-button']")
    WebElement LoginButton;

    public AuthorizationPage(WebDriver webDriver) {
        super(webDriver);
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void signIn() {
        wait.until(ExpectedConditions.visibilityOf(Username));
        Username.sendKeys("razlac94@gmail.com");
        ContinueButton.click();
        wait.until(ExpectedConditions.visibilityOf(Password));
        Password.sendKeys("WhiskTest2022");
        LoginButton.click();
    }
}
