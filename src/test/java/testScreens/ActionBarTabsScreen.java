package testScreens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ActionBarTabsScreen {

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/action_bar']//following::android.widget.TextView")
    @iOSXCUITFindBy(xpath="")
    protected WebElement titleBar;

    @AndroidFindBy(id = "btn_toggle_tabs")
    @iOSXCUITFindBy(xpath="")
    protected WebElement toggleTab;

    @AndroidFindBy(id = "btn_add_tab")
    @iOSXCUITFindBy(xpath="")
    protected WebElement addNewTab;

    @AndroidFindBy(id = "btn_remove_tab")
    @iOSXCUITFindBy(xpath="")
    protected WebElement removeLastTab;

    @AndroidFindBy(id = "btn_remove_all_tabs")
    @iOSXCUITFindBy(xpath="")
    protected WebElement removeAllTabs;

    @AndroidFindBy(xpath = "//*[contains(@text,'TAB')]")
    @iOSXCUITFindBy(xpath="")
    protected List<WebElement> listofTabs;

    @AndroidFindBy(id = "text")
    @iOSXCUITFindBy(xpath="")
    protected WebElement textInTab;
}
