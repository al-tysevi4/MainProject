package addressbook.tests;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactDeletionAllTests extends TestBase {
  //private WebDriver wd;
//  private String baseUrl;
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();

//  @BeforeMethod(alwaysRun = true)
//  public void setUp() throws Exception {
//    wd = new FirefoxDriver();
//    //baseUrl = "https://www.google.com/";
//    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//  }

  @Test
  public void testContactDeletion() throws Exception {
//    wd.get("http://localhost/addressbook/index.php");
//    wd.findElement(By.name("user")).clear();
//    wd.findElement(By.name("user")).sendKeys("admin");
//    wd.findElement(By.name("pass")).click();
//    wd.findElement(By.name("pass")).clear();
//    wd.findElement(By.name("pass")).sendKeys("secret");
//    wd.findElement(By.id("LoginForm")).submit();
    selectAllContacts();
    //acceptNextAlert = true;
    deleteSelectedGroups();
    //assertTrue(closeAlertAndGetItsText().matches("^Delete 8 addresses[\\s\\S]$"));
    extracted();
  }

  private void extracted() {
    wd.switchTo().alert().accept();
  }

  private void deleteSelectedGroups() {
    wd.findElement(By.xpath("//input[@value='Delete']")).click();
  }

  private void selectAllContacts() {
    wd.findElement(By.id("MassCB")).click();
  }

//  @AfterMethod(alwaysRun = true)
//  public void tearDown() throws Exception {
//    wd.quit();
//    String verificationErrorString = verificationErrors.toString();
//    if (!"".equals(verificationErrorString)) {
//      fail(verificationErrorString);
//    }
//  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

//  private String closeAlertAndGetItsText() {
//    try {
//      Alert alert = driver.switchTo().alert();
//      String alertText = alert.getText();
//      if (acceptNextAlert) {
//        alert.accept();
//      } else {
//        alert.dismiss();
//      }
//      return alertText;
//    } finally {
//      acceptNextAlert = true;
//    }
//  }
}
