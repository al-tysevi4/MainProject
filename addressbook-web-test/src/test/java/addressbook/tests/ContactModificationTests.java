package addressbook.tests;


import addressbook.model.ContactData;
import addressbook.model.ContactData1;
import org.testng.Assert;
import org.testng.annotations.*;


public class ContactModificationTests extends  TestBase{


  private int before;

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().goToHomePage();
    int before  = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex", "test1"), true);
      app.getContactHelper().submitContactCreation();
    }
    app.getContactHelper().checkContact();
    app.getContactHelper().pressEdit();
    app.getContactHelper().fillLastname(new ContactData1("tysevich", null), false);
    app.getContactHelper().pressUpdate();
    app.getNavigationHelper().returnToHomePage();
    int after  = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
