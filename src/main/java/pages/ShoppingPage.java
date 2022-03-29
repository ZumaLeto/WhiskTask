package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ShoppingPage extends HeadPage{
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy (xpath = "//a[@data-testid='create-new-shopping-list-button']")
    WebElement createNewListButton;

    @FindBy (xpath = "//button[@data-testid='create-new-shopping-list-create-button']")
    WebElement createButton;

    @FindBy (xpath = "//input[@data-testid='desktop-add-item-autocomplete']")
    WebElement addItemInput;

    @FindBy (xpath = "//button[@data-testid='vertical-dots-shopping-list-button']")
    WebElement listOptionsButton;

    @FindBy (xpath = "//button[@data-testid='shopping-list-delete-menu-button']")
    WebElement deleteListButton;

    @FindBy (xpath = "//button[@data-testid='confirm-delete-button']")
    WebElement deleteConfirmButton;

    @FindBy (xpath = "//div[@data-testid='shopping-lists-list-name']")
    List<WebElement> shoppingList;

    @FindBy(xpath = "//span[text() = 'List was deleted']")
    WebElement deleteLabel;

    @FindBy (xpath = "//span[text() = 'Milk']")
    WebElement milk;

    @FindBy (xpath = "//span[text() = 'Bread']")
    WebElement bread;

    @FindBy (xpath = "//span[text() = 'Onion']")
    WebElement onion;

    @FindBy (xpath = "//span[text() = 'Butter']")
    WebElement butter;

    @FindBy (xpath = "//span[text() = 'Cheese']")
    WebElement cheese;

    public ShoppingPage(WebDriver webDriver) {
        super(webDriver);
        driver = webDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void createShoppingList() {
        waitAndClick(createNewListButton);
        waitAndClick(createButton);
        // Обновляем страницу чтобы проинициализировать новый shopping list
        driver.navigate().refresh();
        driver.manage().window().fullscreen();
    }

    public void addItems() {
        waitAndClick(shoppingList.get(0));
        waitAndClick(addItemInput);
        waitAndClick(milk);
        bread.click();
        onion.click();
        butter.click();
        cheese.click();
    }

    public void checkItemInList(String name) {
        driver.findElement(By.xpath("//span[@data-testid='shopping-list-item-name' and text()='" + name + "']"));
    }

    public void deleteList() {
        waitAndClick(shoppingList.get(0));
        waitAndClick(listOptionsButton);
        waitAndClick(deleteListButton);
        waitAndClick(deleteConfirmButton);
        // Удаление не проходило после быстрого выхода из теста даже после добавления проверки на появления
        // List was deleted, поэтому пришлось добавить wait на исчезнованения сообщения
        wait.until(ExpectedConditions.visibilityOf(deleteLabel));
        wait.until(ExpectedConditions.invisibilityOf(deleteLabel));
    }

    public void checkExistShoppingList() {
        // Не будет выполнятся поскольку создаются 2 списка, а удаляется 1
        Assert.isTrue(shoppingList.size() == 1, "User has shopping list");
    }
}
