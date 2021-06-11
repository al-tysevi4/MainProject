package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import java.util.Iterator;



public class ContactAddGroupTests extends TestBase {
    private ContactData changedContact;
    private GroupData testGroup;


    private ContactData getContactWithoutGroup() {

        for (Iterator<ContactData> iterator = app.db().contacts().iterator(); iterator.hasNext(); ) {
            ContactData contact = iterator.next();
            if (contact.getGroups().size() == 0) {
                return contact;
            }
        }
        return null;
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 1"));
        }
        if (getContactWithoutGroup() == null) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("alexey")
                    .withLastname("yagudin"));
        }
    }

    @Test
    public void testContactAddGroup() throws Exception {

        GroupData changedGroup = app.db().groups().iterator().next();
        ContactData changedContact = getContactWithoutGroup();
        int theId = changedContact.getId();

        app.goTo().homePage();
        app.contact().checkContactById(changedContact.getId());
        app.contact().checkGroupForContact(changedGroup.getId());
        app.contact().addContactToGroup();
        app.goTo().returnToHomePage();


        Groups groupBefore = changedContact.ifGroups(changedGroup, true).getGroups();
        Contacts getContactListAfter = app.db().contacts();
        Groups newGroupsList = getContactListAfter.stream().filter(c -> c.getId() == theId).findFirst().get().getGroups();
        MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupsList));
    }
}
