package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {


    private int before;

    @Test
  public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex", "test1"),true);
      app.getContactHelper().submitContactCreation();
        }
        app.getContactHelper().checkContact();
        int before  = app.getContactHelper().getContactCount();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().returnToHomePage();
        int after  = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
  }
}
