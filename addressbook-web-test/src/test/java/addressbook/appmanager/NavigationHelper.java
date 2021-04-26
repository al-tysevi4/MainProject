package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
//        if(isElementPresent(By.tagName("h1"))
//                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
//                && isElementPresent(By.name("new"))) {
//            return;
//        }
      click(By.linkText("groups"));
    }

    public void returnToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void gotoAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void gotoHomePage() {
//        if(isElementPresent(By.id("maintable"))) {
//            return;
//        }
      click(By.linkText("home page"));
    }
}
