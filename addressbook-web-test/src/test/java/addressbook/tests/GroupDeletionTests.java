package addressbook.tests;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class GroupDeletionTests extends TestBase{
  //private WebDriver wd;

  public void login(String username, String password) {
      wd.get("http://localhost/addressbook/group.php");
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.id("LoginForm")).submit();
    }

  @Test
  public void testGroupDeletion() throws Exception {
    checkGroup();
    deleteGroup();
    //returnToGroupPage();
    logout();
  }

  //  private void returnToGroupPage() {
//    wd.findElement(By.linkText("group page")).click();
//  }

  //  @AfterMethod(alwaysRun = true)
//  public void tearDown() throws Exception {
//    wd.quit();
//  }

//  private boolean isElementPresent(By by) {
//    try {
//      wd.findElement(by);
//      return true;
//    } catch (NoSuchElementException e) {
//      return false;
//    }
//  }
//
//  private boolean isAlertPresent() {
//    try {
//      wd.switchTo().alert();
//      return true;
//    } catch (NoAlertPresentException e) {
//      return false;
//    }
//  }
}
