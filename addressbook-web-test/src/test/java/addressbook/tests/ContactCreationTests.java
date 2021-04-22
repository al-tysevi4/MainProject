package addressbook.tests;

import java.util.concurrent.TimeUnit;

import addressbook.model.ContactData;
import addressbook.model.ContactData1;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests extends TestBase{
  //  private String baseUrl;
//  private boolean acceptNextAlert = true;
//  private StringBuffer verificationErrors = new StringBuffer();


  @Test
  public void testContactCreation() throws Exception {

    gotoAddNewPage();
    fillContactForm(new ContactData1("aleksandr", "tysevich", "tver, ippodromnaya 1", "+79106465892", "al-tysevi4@yandex.ru", "20", "October", "1970"), "aleksandr", "tysevich", "tver, ippodromnaya 1", "+79106465892", "al-tysevi4@yandex.ru", "20", "October", "1970");
    submitContactCreation();
    homeAndLogout();
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
