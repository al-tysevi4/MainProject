package addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    public void returnToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void gotoAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void gotoHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }
}
