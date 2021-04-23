package addressbook.tests;


import addressbook.model.ContactData1;
import org.testng.annotations.*;


public class ContactModificationTests extends  TestBase{


  @Test
  public void testContactModification() throws Exception {
    app.checkContact();
    app.getContactHelper().pressEdit();
    app.getContactHelper().fillLastname(new ContactData1("tysevich"));
    app.getContactHelper().pressUpdate();
    app.getNavigationHelper().gotoHomePage();
  }
}
