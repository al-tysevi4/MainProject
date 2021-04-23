package addressbook.tests;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ContactDeletionTests extends TestBase {
  //WebDriver wd;


  @Test
  public void testContactDeletion() throws Exception {
    checkContact();
    deleteContact();

  }

  //  private void checkContact() {
//    wd.findElement(By.name("selected[]")).click();
//  }

//  @AfterMethod(alwaysRun = true)
//  public void tearDown() throws Exception {
//    wd.quit();
//  }
//
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
