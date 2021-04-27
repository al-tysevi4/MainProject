package addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    //private String browser;



    //public ApplicationManager(String browser) {
        //ApplicationManager.this.browser = browser;
    //}

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/index.php");
        contactHelper = new ContactHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");

        //{

//        if (browser.equals(BrowserType.FIREFOX)) {
//            sessionHelper.wd = new FirefoxDriver();
//        } else if (browser.equals(BrowserType.CHROME)) {
//            sessionHelper.wd = new ChromeDriver();
//        } else if (browser.equals(BrowserType.SAFARI)) {
//            sessionHelper.wd = new SafariDriver();
//        }
//        contactHelper.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        groupHelper = new GroupHelper(groupHelper.wd);
//        navigationHelper = new NavigationHelper(navigationHelper.wd);
//        contactHelper = new ContactHelper(contactHelper.wd);
//        sessionHelper = new SessionHelper(sessionHelper.wd);
//        sessionHelper.login("admin", "secret");
    }

    public void stop() {
       wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper; }
}
