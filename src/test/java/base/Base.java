package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Base {

        protected static AppiumDriver driver;
        private static AppiumDriverLocalService server;
        protected static Properties props;
        private DesiredCapabilities dc;
        private URL url;
        private String propFileName;
        private String appLocation;
        protected static String dateTime;
        protected static FileInputStream fis;
        public static ExtentReports extent;
        public static ExtentSparkReporter sparkAll ;
        static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports();
            sparkAll = new ExtentSparkReporter("spark/SparkAll.html");
            sparkAll.config().setDocumentTitle("Om Prakash Chapter 157");
            sparkAll.config().setReportName("https://www.extentreports.com/docs/versions/5/java/index.html");
            sparkAll.config().setTheme(Theme.DARK);
            extent.attachReporter(sparkAll);
        }
            return extent;
    }

    public static ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static ExtentTest startTest(String testName, String desc)
    {
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }

        public void setDriver(AppiumDriver driver) {
                this.driver = driver;
        }

        public AppiumDriver getDriver() {
                return driver;
        }

        public void startUpServer() {
                server = getAppiumService(); // -> If using Mac, uncomment this statement and comment below statement
                //     server = getAppiumServerDefault(); // -> If using Windows, uncomment this statement and comment above statement
                if(!checkIfAppiumServerIsRunnning(4723)) {
                        server.start();
                        server.clearOutPutStreams(); // -> Comment this if you want to see server logs in the console
                //        log.info("Appium server started");
                } else {
                 //       log.info("Appium server already running");
                }
        }

        public void shutDownServer() {
                server.stop();
        }

        public boolean checkIfAppiumServerIsRunnning(int port)
        {
                boolean isAppiumServerRunning = false;
                ServerSocket socket;
                try {
                        socket = new ServerSocket(port);
                        socket.close();
                } catch (IOException e) {
                        System.out.println("1");
                        isAppiumServerRunning = true;
                } finally {
                        socket = null;
                }
                return isAppiumServerRunning;
        }

        // for Windows
        public AppiumDriverLocalService getAppiumServerDefault() {
                return AppiumDriverLocalService.buildDefaultService();
        }

        // for Mac. Update the paths as per your Mac setup
        public AppiumDriverLocalService getAppiumService()
        {
                HashMap<String, String> environment = new HashMap<String, String>();
                environment.put("PATH", "enter_your_path_here" + System.getenv("PATH"));
                environment.put("ANDROID_HOME", "enter_android_home_path");
                return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("/usr/local/bin/node"))
                        .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .usingPort(4723)
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//				.withArgument(() -> "--allow-insecure","chromedriver_autodownload")
                        .withEnvironment(environment)
                        .withLogFile(new File("ServerLogs/server.log")));
        }


        public void initialiseDriver(String emulator, String platformName, String deviceName) throws Exception {

                dc = new DesiredCapabilities();
                props = new Properties();
                propFileName = System.getProperty("user.dir")+"/src/test/resources/Global.properties";
                appLocation = System.getProperty("user.dir") + "/src/test/resources/ApiDemos.apk";
                fis = new FileInputStream(propFileName);
                props.load(fis);

                switch (platformName) {
                        case "Android":

                                        dc.setCapability(AndroidMobileCapabilityType.PLATFORM_NAME, platformName);
                                        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
                                        dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                                        dc.setCapability(MobileCapabilityType.APP, appLocation);
                                        dc.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
                                        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, props.getProperty("androidAppPackage"));
                                        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, props.getProperty("androidAppActivity"));

                                        if (emulator.equalsIgnoreCase("true"))
                                        {
                                                dc.setCapability("avd", deviceName);
                                        }
                                        else
                                        {
                                                dc.setCapability(MobileCapabilityType.UDID, props.getProperty("androidUDID"));
                                        }
                                        System.out.println("Capabilities Set");
                                        url = new URL("http://127.0.0.1:4723/wd/hub");
                                        System.out.println("URL assigned");
                                        driver = new AndroidDriver(url, dc);
                                        System.out.println("Android driver assigned");

                                break;

                        case "iOS":

                                        dc.setCapability(IOSMobileCapabilityType.PLATFORM_NAME, platformName);
                                        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
                                        dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                                        dc.setCapability(MobileCapabilityType.APP, "iOSapplocation");

                                        if (emulator.equalsIgnoreCase("true")) {
                                                dc.setCapability("platformVersion", props.get("iOSPlatformVersion"));
                                                dc.setCapability("avd", props.get("iOSDevice"));
                                        } else {
                                                dc.setCapability("udid", props.get("iosUDID"));
                                        }


                                        url = new URL("http://127.0.0.1:4723/wd/hub");
                                        driver = new IOSDriver(url, dc);

                                break;

                        default:
                                throw new Exception("Invalid platform! =");
                }
                                setDriver(driver);
                                String sessionId = driver.getSessionId().toString();

}

        public String getDateTime()
        {
            return dateTime;
        }


    public void waitForVisibility(WebElement e)
        {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
                wait.until(ExpectedConditions.visibilityOf(e));
        }

        public void waitForExplicitVisibility(WebElement e){
                Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                        .withTimeout(Duration.ofSeconds(30))
                        .pollingEvery(Duration.ofSeconds(5))
                        .ignoring(NoSuchElementException.class);
                wait.until(ExpectedConditions.visibilityOf(e));
        }

        public void clear(WebElement e) {
                waitForVisibility(e);
                e.clear();
        }

        public void click(WebElement e) {
                waitForVisibility(e);
                e.click();
        }

        public void sendKeys(WebElement e, String txt) {
                waitForVisibility(e);
                e.sendKeys(txt);
        }

        public String getAttribute(WebElement e, String attr) {
                waitForVisibility(e);
                return(e.getAttribute(attr));
        }

        public void quitDriver()
        {
                driver.quit();
        }
}
