package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class ContactDeletionTests extends TestBase {


    @Test //(enabled = false)
  public void testContactDeletion() throws Exception {
        app.goTo().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex","test1","tysevich", null), true);
      app.getContactHelper().submitContactCreation();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().checkContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.goTo().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
