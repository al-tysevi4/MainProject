package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if(isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
      click(By.linkText("groups"));
    }

    public boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
            return true;
        }catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void returnToHomePage() {
//        if (! isElementPresent(By.id("maintable"))) {
//          return;
//        }
        wd.findElement(By.linkText("home")).click();
    }

    public void gotoAddNewPage() {
        click(By.linkText("add new"));
    }

    public void homePage() {
        if (! isElementPresent(By.id("maintable"))) {
          return;
        }
        wd.findElement(By.linkText("home")).click();
    }
}
