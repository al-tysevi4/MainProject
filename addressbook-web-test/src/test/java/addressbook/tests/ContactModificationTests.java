package addressbook.tests;


import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends  TestBase{

  @BeforeMethod
  public void ensureContactPreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("alex")
              .withGroup("test1")
              .withLastname("tysevich"));
    }
  }


  @Test //(enabled = false)
  public void testContactModification() throws Exception {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact =  new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname("alex")
            .withGroup("test1")
            .withLastname("tysevich");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    //Contacts after = app.contact().all();
    //assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    verifyContactListInUi();
  }


}
