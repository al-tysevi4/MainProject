package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.testng.annotations.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensureContactPreconditions() {
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("alex")
                    .withGroup("test1")
                    .withFirstname("tysevich"));
        }
    }


    @Test //(dataProvider = "validContactsFromJson")//(enabled = false)
  public void testContactDeletion() throws Exception {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        //Contacts after = app.contact().all();
        //assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));

        verifyContactListInUi();

    }
}
