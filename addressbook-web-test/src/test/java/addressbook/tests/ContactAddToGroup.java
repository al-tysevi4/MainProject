package addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactAddToGroup extends TestBase{



  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    loginForAddToGroup();
  }

  @Test
  public void testContactAddToGroup() throws Exception {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText("test1");
        wd.findElement(By.xpath("(//option[@value='8'])[2]")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }
}
