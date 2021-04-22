package addressbook.tests;

import addressbook.model.ContactData1;
import org.testng.annotations.*;


public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData1("aleksandr", "tysevich", "tver, ippodromnaya 1", "+79106465892", "al-tysevi4@yandex.ru", "20", "October", "1970"), "aleksandr", "tysevich", "tver, ippodromnaya 1", "+79106465892", "al-tysevi4@yandex.ru", "20", "October", "1970");
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().homeAndLogout();
  }
}
