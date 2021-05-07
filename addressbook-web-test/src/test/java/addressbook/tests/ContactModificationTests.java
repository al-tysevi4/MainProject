package addressbook.tests;


import addressbook.model.ContactData;
import addressbook.model.ContactData1;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.List;


public class ContactModificationTests extends  TestBase{


  private int before;

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex", null), true);
      app.getContactHelper().submitContactCreation();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().checkContact(before.size() - 1);
    app.getContactHelper().pressEdit(before.size() - 1);// не получается сделать get(index) в методе
    ContactData contact =  new ContactData("tysevich", null);
    app.getContactHelper().pressUpdate();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
