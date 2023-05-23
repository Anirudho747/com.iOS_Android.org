package testSteps;

import base.Base;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import testScreens.ActionBarScreen;
import testScreens.ActionBarTabsScreen;


public class ActionBarTabsSteps extends ActionBarTabsScreen {

    Base bs;

    public ActionBarTabsSteps()
    {
        bs = new Base();
        PageFactory.initElements(new AppiumFieldDecorator(bs.getDriver()),this);
    }

    public void clickToggleTab()
    {
        bs.click(toggleTab);
    }

    public void clickAddNewTab()
    {
        bs.click(addNewTab);
    }

    public void clickRemoveLastTab()
    {
        bs.click(removeLastTab);
    }

    public void clickRemoveAllTabs()
    {
        bs.click(removeAllTabs);
    }

    public String getTitle()
    {
        return bs.getAttribute(titleBar,"text");
    }

    public String getTabText()
    {
        return bs.getAttribute(textInTab,"text");
    }

    public int getTabCount()
    {
        return listofTabs.size();
    }
}
