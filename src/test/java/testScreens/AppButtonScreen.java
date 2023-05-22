package testScreens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class AppButtonScreen {

    @AndroidFindBy(accessibility = "Action Bar")
    @iOSXCUITFindBy(xpath="")
    protected WebElement actionBar;
}
