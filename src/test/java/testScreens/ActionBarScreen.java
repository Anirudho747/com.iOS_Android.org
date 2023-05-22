package testScreens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class ActionBarScreen {

    @AndroidFindBy(accessibility = "Action Bar Mechanics")
    @iOSXCUITFindBy(xpath="")
    protected WebElement actionBarMechanics;

    @AndroidFindBy(accessibility = "Action Bar Tabs")
    @iOSXCUITFindBy(xpath="")
    protected WebElement actionBarTabs;

    @AndroidFindBy(accessibility = "Action Bar Usage")
    @iOSXCUITFindBy(xpath="")
    protected WebElement actionBarUsage;

    @AndroidFindBy(accessibility = "Action Provider")
    @iOSXCUITFindBy(xpath="")
    protected WebElement actionBarProvider;

    @AndroidFindBy(accessibility = "Display Options")
    @iOSXCUITFindBy(xpath="")
    protected WebElement actionBarDisplayOptions;


}
