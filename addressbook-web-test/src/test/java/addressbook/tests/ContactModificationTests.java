package addressbook.tests;


import addressbook.model.ContactData;
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
      app.getContactHelper().fillContactForm(new ContactData(0,"alex", null), true);
      app.getContactHelper().submitContactCreation();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().checkContact(before.size() - 1);

    app.getContactHelper().pressEdit();// не получается сделать get(index) в методе
    //int id = before.get(before.size() - 1).getId();
    ContactData contact =  new ContactData(before.get(before.size() - 1).getId(),"tysevich", null);

    app.getContactHelper().pressUpdate();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    //int id = before.get(before.size() - 1).getId();
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
