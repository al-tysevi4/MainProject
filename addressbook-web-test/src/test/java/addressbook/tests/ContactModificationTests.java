package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;


public class ContactModificationTests extends  TestBase{

  @BeforeMethod
  public void ensureContactPreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("alex").withGroup("test1").withLastname("tysevich"));
    }
  }


  @Test //(enabled = false)
  public void testContactModification() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact =  new ContactData()
            .withId(before.get(index).getId())
            .withFirstname("alexey")
            .withGroup("test1")
            .withLastname("tysevich");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

//  private void modifyContact(int index, ContactData contact) {
//    app.getContactHelper().checkContact(index);
//    app.getContactHelper().pressEdit(index);
//    app.getContactHelper().fillContactForm(contact, false);
//    app.getContactHelper().pressUpdate();
//    app.goTo().returnToHomePage();
//  }
  //("alex", "test1", "tysevich", null));
  //("alexey", "test1", "tysevich", null);
}
