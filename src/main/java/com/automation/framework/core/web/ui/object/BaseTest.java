package com.automation.framework.core.web.ui.object;

import com.automation.framework.core.base.OrgBaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseTest extends OrgBaseTest {
    private static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeMethod
    public void runBeforeTest() {
        if (OS.isWindows())
            System.setProperty("webdriver.chrome.driver",

                    System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "chromedriver.exe");
        else if (OS.isMac())
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "chromedriver");

//                System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "chromedriver.exe");
//        else if (OS.isMac())
//            System.setProperty("webdriver.chrome.driver",
//                System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "chromedriver");


        ChromeOptions options = new ChromeOptions();
        //options.addArguments("window-size=1200x600");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }
/*

    @Parameters("browser")
    public void runBeforeTest(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            if (OS.isWindows())
                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "chromedriver.exe");
            else if (OS.isMac())
                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            if (OS.isWindows())
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            if (OS.isWindows())
                System.setProperty("webdriver.ie.driver",
                        System.getProperty("user.dir") + File.separator + "WebDriver" + File.separator + "IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
*/



   @AfterMethod




    public void runAfterTest() {
        closeBrowser();
    }


    public void closeBrowser() {
        try {
            driver.quit();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }


    //wait

   // public void sleep(int timeToWait) {

    public void sleep(int timeToWait){

        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


class OS {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

//    class OS {
//        private static String OS = System.getProperty("os.name").toLowerCase();
//
//        public static boolean isWindows() {
//            return (OS.indexOf("win") >= 0);
//        }
//
//        public static boolean isMac() {
//            return (OS.indexOf("mac") >= 0);
//        }
//
//        public static boolean isUnix() {
//            return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
//        }
//
//        public static boolean isSolaris() {
//            return (OS.indexOf("sunos") >= 0);
//        }
//    }

}