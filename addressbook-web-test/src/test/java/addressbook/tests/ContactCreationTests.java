package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddNewPage();
    ContactData contact = new ContactData("alex", null, "test1");
    app.getContactHelper().fillContactForm(contact, true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    app.logout();

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
