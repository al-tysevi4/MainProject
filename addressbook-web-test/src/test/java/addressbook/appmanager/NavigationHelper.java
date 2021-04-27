package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
      wd.findElement(By.linkText("groups")).click();
    }

    private boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
            return true;
        }catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void returnToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void gotoAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void gotoHomePage() {
//        if (! isElementPresent(By.id("maintable"))) {
//          return;
//        }
      wd.findElement(By.linkText("home page")).click();
    }
}
