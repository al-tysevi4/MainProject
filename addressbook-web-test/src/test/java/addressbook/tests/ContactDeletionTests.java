package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {


    private int before;

    @Test
  public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goToHomePage();
        int before  = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex", "test1"),true);
      app.getContactHelper().submitContactCreation();
        }
        app.getContactHelper().checkContact(before - 1);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().returnToHomePage();
        int after  = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
  }
}
