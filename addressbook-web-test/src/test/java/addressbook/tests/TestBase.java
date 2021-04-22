package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.ContactData1;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();
    //protected WebDriver wd;
     WebDriver wd;

//    @BeforeMethod(alwaysRun = true)
//    public void setUp() throws Exception {
//        app.init();
//    }


    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
      wd = new FirefoxDriver();
      //baseUrl = "https://www.google.com/";
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/index.php");
      login("admin", "secret");
    }

    private void login(String username, String password) {
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.id("LoginForm")).submit();
    }

    protected void homeAndLogout() {
      wd.findElement(By.linkText("home")).click();
      wd.findElement(By.linkText("Logout")).click();
    }

    protected void submitContactCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    protected void fillContactForm(ContactData1 contactData1, String aleksandr, String tysevich, String s, String s1, String s2, String s3, String october, String s4) {// String aleksandr, String tysevich, String s, String s1, String s2, String s3, String october, String s4) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactData1.getFirstname());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData1.getLastname());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(contactData1.getAddress());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(contactData1.getMobile());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactData1.getEmail());
      wd.findElement(By.name("bday")).click();
  //    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("11");
  //    wd.findElement(By.xpath("//option[@value='11']")).click();
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData1.getBday());
      wd.findElement(By.xpath("//option[@value='20']")).click();
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData1.getBmonth());
      wd.findElement(By.xpath("//option[@value='October']")).click();
      wd.findElement(By.name("byear")).click();
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys(contactData1.getByear());
    }

    protected void gotoAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown() throws Exception {
//        app.stop();
//    }

    protected void loginForAddToGroup() {
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/index.php");
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys("admin");
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys("secret");
      wd.findElement(By.id("LoginForm")).submit();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
      wd.quit();
  //    String verificationErrorString = verificationErrors.toString();
  //    if (!"".equals(verificationErrorString)) {
  //      fail(verificationErrorString);
  //    }
    }

    protected void extracted() {
      wd.switchTo().alert().accept();
    }

    protected void deleteSelectedGroups() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    protected void selectAllContacts() {
      wd.findElement(By.id("MassCB")).click();
    }

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
}
