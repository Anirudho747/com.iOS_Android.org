package testSteps;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import testScreens.LandingPageScreen;
import base.Base;

public class LandingPageSteps extends LandingPageScreen {

    Base bs;

    public LandingPageSteps()
    {
        bs = new Base();
        PageFactory.initElements(new AppiumFieldDecorator(bs.getDriver()),this);
    }

    public void clickContinue()
    {
        bs.click(continueButton);
    }

    public void clickOk()
    {
        bs.click(OKButton);
    }

    public void clickAppOption()
    {
        bs.click(appButton);
    }
}
