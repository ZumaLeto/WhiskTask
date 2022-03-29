import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.ShoppingPage;

@Test
public class WhiskTestsUI {

    WebDriver driver;
    ShoppingPage shoppingPage;

    @BeforeGroups({"addItem","deleteItem"})
    public void authorization() {
        //Указать корректный путь до драйверов на своем устройстве
        System.setProperty("webdriver.chrome.driver", "/Users/polzovatel/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://my.whisk-dev.com/");
        driver.manage().window().fullscreen();
        AuthorizationPage authorizationPage = new AuthorizationPage(driver);
        authorizationPage.signIn();
        MainPage mainPage = new MainPage(driver);
        mainPage.NavigateToShoppingTab();
        shoppingPage = new ShoppingPage(driver);
        shoppingPage.createShoppingList();
    }

    @BeforeGroups("addItem")
    public void putItems() {
        shoppingPage = new ShoppingPage(driver);
        shoppingPage.addItems();
    }


    @Test(dataProvider = "itemsName", groups = {"addItem"})
    public void checkItemsTest(String name) {
        shoppingPage.checkItemInList(name);
    }

    @DataProvider(name = "itemsName", parallel = false)
    public Object [][] getData() {
        return new Object[][] {
                {"Milk"}, {"Bread"}, {"Onion"}, {"Butter"},{"Cheese"}
        };
    }

    @Test(groups = "deleteItem")
    public void deleteShoppingListTest() {
        shoppingPage = new ShoppingPage(driver);
        shoppingPage.deleteList();
        shoppingPage.checkExistShoppingList();
    }

    @AfterGroups({"addItem","deleteItem"})
    public void closeOff() {
        driver.close();
    }
}

