package testSteps;

import base.Base;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import testScreens.AppButtonScreen;
import testScreens.LandingPageScreen;

public class AppButtonSteps extends AppButtonScreen {

    Base bs;

    public AppButtonSteps()
    {
        bs = new Base();
        PageFactory.initElements(new AppiumFieldDecorator(bs.getDriver()),this);
    }

    public void clickActionBar()
    {
        bs.click(actionBar);
    }


}
