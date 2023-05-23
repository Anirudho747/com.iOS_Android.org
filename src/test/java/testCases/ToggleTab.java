package testCases;

import base.Base;
import org.testng.annotations.*;
import testSteps.ActionBarSteps;
import testSteps.ActionBarTabsSteps;
import testSteps.AppButtonSteps;
import testSteps.LandingPageSteps;

import java.lang.reflect.Method;

public class ToggleTab {

    LandingPageSteps lps;
    AppButtonSteps appButtonSteps;
    ActionBarSteps actionBarSteps;
    ActionBarTabsSteps actionBarTabsSteps;
    Base bs;

    @BeforeSuite
    public void fireUp()
    {
        bs = new Base();
        //      bs.startAppiumServer();
    }

    @Parameters({"emulator","platformName","deviceName"})
    @BeforeClass
    public void beforeClass(String emulator,String platformName,String deviceName) throws Exception {
        bs.initialiseDriver(emulator,platformName,deviceName);
        lps = new LandingPageSteps();
        appButtonSteps = new AppButtonSteps();
        actionBarSteps = new ActionBarSteps();
        actionBarTabsSteps = new ActionBarTabsSteps();
    }

    @BeforeMethod
    public void beforeMethod(Method m)
    {
        System.out.println("+++++++++ "+m.getName()+"  ++++++++++++");
    }

    @AfterClass
    public void afterClass()
    {
        bs.quitDriver();
    }

    @Test(priority = 2)
    public void loginSuccess()
    {
        lps.clickContinue();
        lps.clickOk();
        lps.clickAppOption();
        appButtonSteps.clickActionBar();
        actionBarSteps.clickActionBarTabs();
        actionBarTabsSteps.clickToggleTab();
    }

}
