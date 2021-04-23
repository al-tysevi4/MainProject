package addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      login("admin", "secret");
    }

//    @BeforeMethod(alwaysRun = true)
//    public void setUp() throws Exception {
//      wd = new FirefoxDriver();
//      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//      login("admin", "secret");
//    }

//    private void login(String username, String password) {
//      wd.get("http://localhost/addressbook/index.php");
//      wd.findElement(By.name("user")).clear();
//      wd.findElement(By.name("user")).sendKeys(username);
//      wd.findElement(By.name("pass")).click();
//      wd.findElement(By.name("pass")).clear();
//      wd.findElement(By.name("pass")).sendKeys(password);
//      wd.findElement(By.id("LoginForm")).submit();
//    }

    protected void returnToGroupPage() {
      wd.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).click();
      wd.findElement(By.name("group_name")).clear();
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).click();
      wd.findElement(By.name("group_header")).clear();
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).click();
      wd.findElement(By.name("group_footer")).clear();
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    protected void returnToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    protected void submitContactCreation() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillContactForm(ContactData contactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    }

    protected void gotoAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    protected void gotoHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    protected void pressUpdate() {
      wd.findElement(By.name("update")).click();
    }

    protected void fillLastname(ContactData1 contactData1) {
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData1.getLastname());
    }

    protected void pressEdit() {
      wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    protected void checkContact() {
      wd.findElement(By.name("selected[]")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
      wd.quit();
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

    protected void logout() {
      wd.findElement(By.linkText("Logout")).click();
    }

    protected void deleteGroup() {
      wd.findElement(By.xpath("(//input[@name='delete'])[2]")).click();
    }

    protected void checkGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

//    @BeforeMethod(alwaysRun = true)
//    public void setUp() throws Exception {
//      wd = new FirefoxDriver();
//      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//      login("admin", "secret");
//    }

//    public void login(String username, String password) {
//      wd.get("http://localhost/addressbook/group.php");
//      wd.findElement(By.name("user")).clear();
//      wd.findElement(By.name("user")).sendKeys(username);
//      wd.findElement(By.name("pass")).click();
//      wd.findElement(By.name("pass")).clear();
//      wd.findElement(By.name("pass")).sendKeys(password);
//      wd.findElement(By.id("LoginForm")).submit();
//    }

//    @BeforeMethod(alwaysRun = true)
//    public void setUp() throws Exception {
//      wd = new FirefoxDriver();
//      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//      login("admin", "secret");
//    }

    protected void login(String username, String password) {
      wd.get("http://localhost/addressbook/index.php");
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void deleteContact() {
      wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }
}
