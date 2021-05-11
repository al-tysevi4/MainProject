package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends  TestBase{


  private int before;

  @Test //(enabled = false)
  public void testContactModification() throws Exception {
    app.goTo().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.goTo().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex", "test1", "tysevich", null), true);
      app.getContactHelper().submitContactCreation();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().checkContact(before.size() - 1);

    app.getContactHelper().pressEdit(before.size() - 1);
    ContactData contact =  new ContactData("alexey", "test1", "tysevich", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().pressUpdate();
    app.goTo().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
