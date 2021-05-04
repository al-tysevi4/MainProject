package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class ContactDeletionTests extends TestBase {


    @Test
  public void testContactDeletion() throws Exception {
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex",null, "test1"), true);
      app.getContactHelper().submitContactCreation();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        //int before  = app.getContactHelper().getContactCount();
        app.getContactHelper().checkContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        //int after  = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
//        for (int i = 0; i < after.size(); i++) {
//        Assert.assertEquals(before.get(i), after.get(i));
//        }
    }
}
