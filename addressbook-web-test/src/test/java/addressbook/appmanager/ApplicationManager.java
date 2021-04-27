package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    //WebDriver wd;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;



    public ApplicationManager(String browser) {
        ApplicationManager.this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            sessionHelper.wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            sessionHelper.wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.SAFARI)) {
            sessionHelper.wd = new SafariDriver();
        }
        //wd = new FirefoxDriver();
        contactHelper.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        groupHelper = new GroupHelper(contactHelper.wd);
        navigationHelper = new NavigationHelper(contactHelper.wd);
        contactHelper = new ContactHelper(contactHelper.wd);
        sessionHelper = new SessionHelper(sessionHelper.wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
       contactHelper.wd.quit();
    }

    //    public void logout() {
//      wd.findElement(By.linkText("Logout")).click();
//    }
//
//    public void login(String username, String password) {
//      wd.get("http://localhost/addressbook/index.php");
//      wd.findElement(By.name("user")).clear();
//      wd.findElement(By.name("user")).sendKeys(username);
//      wd.findElement(By.name("pass")).click();
//      wd.findElement(By.name("pass")).clear();
//      wd.findElement(By.name("pass")).sendKeys(password);
//      wd.findElement(By.xpath("//input[@value='Login']")).click();
//    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
//    public void submitContactCreation() {
//        groupHelper.submitGroupCreation();
//    }
//
//    //private class MyContactHelper extends ContactHelper {
//        public boolean isElementPresent(By by) {
//          try {
//            wd.findElement(by);
//            return true;
//          } catch (NoSuchElementException e) {
//            return false;
//          }
//        }
//
//        public boolean isAlertPresent() {
//          try {
//            wd.switchTo().alert();
//            return true;
//          } catch (NoAlertPresentException e) {
//            return false;
//          }
//        }
//    }
}
