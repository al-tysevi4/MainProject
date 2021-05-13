package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

  @Test   //(enabled = false)
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("alexey")
            .withGroup("test1")
            .withLastname("tysevich")
            .withMobile("+345678945555");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    app.logout();
    assertThat(after, equalTo(
            before.withAdded(
                    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
