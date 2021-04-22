package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;


    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public void init() {
       wd = new FirefoxDriver();
       wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       wd.get("http://localhost/addressbook/index.php");
       groupHelper = new GroupHelper(wd);
       contactHelper = new ContactHelper(wd);
       navigationHelper = new NavigationHelper(wd);
       sessionHelper = new SessionHelper(wd);
       sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public void addToGroup() {
        wd.findElement(By.xpath("(//option[@value='8'])[2]")).click();
    }

    public void clickGroup() {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText("test1");
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
