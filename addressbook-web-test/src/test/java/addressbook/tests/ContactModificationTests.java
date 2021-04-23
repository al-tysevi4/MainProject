package addressbook.tests;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ContactModificationTests extends  TestBase{


  @Test
  public void testContactModification() throws Exception {
    checkContact();
    pressEdit();
    fillLastname(new ContactData1("tysevich"));
    pressUpdate();
    gotoHomePage();
  }
}
