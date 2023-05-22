package testSteps;

import base.Base;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import testScreens.ActionBarScreen;


public class ActionBarSteps extends ActionBarScreen {

    Base bs;

    public ActionBarSteps()
    {
        bs = new Base();
        PageFactory.initElements(new AppiumFieldDecorator(bs.getDriver()),this);
    }

    public void clickActionBarTabs()
    {
        bs.click(actionBarTabs);
    }


}
