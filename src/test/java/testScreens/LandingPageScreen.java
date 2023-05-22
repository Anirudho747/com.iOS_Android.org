package testScreens;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class LandingPageScreen {

    @AndroidFindBy(xpath="//*[@text='Continue']")
    @iOSXCUITFindBy(xpath="")
    protected WebElement continueButton;

    @AndroidFindBy(xpath="//*[@text='OK']")
    @iOSXCUITFindBy(xpath="")
    protected WebElement OKButton;

    @AndroidFindBy(accessibility="App")
    @iOSXCUITFindBy(xpath="")
    protected WebElement appButton;
}
