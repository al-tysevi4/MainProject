package addressbook.tests;


import addressbook.model.ContactData;
import addressbook.model.ContactData1;
import org.testng.annotations.*;


public class ContactModificationTests extends  TestBase{


  @Test
  public void testContactModification() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex", "test1"), true);
      app.getContactHelper().submitContactCreation();
      //app.getNavigationHelper().returnToHomePage();
    }
    app.getContactHelper().checkContact();
    app.getContactHelper().pressEdit();
    //app.getContactHelper().fillLastname(new ContactData1("tysevich", null), false);
    app.getContactHelper().pressUpdate();
    //app.getNavigationHelper().returnToHomePage();
  }
}
