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

public class ContactRemoveGroupTests extends TestBase {

    private static SessionFactory sessionFactory;
//    private int contactId;
//    private int groupId;
//    private int maxId;
    private GroupData testGroup;

    private ContactData getContactWithGroup() {
        for (Iterator<ContactData> iterator = app.db().contacts().iterator(); iterator.hasNext(); ) {
            ContactData contact = iterator.next();
            if (contact.getGroups().size() != 0) {
                return contact;
            }
        }
        return null;
    }

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 0"));
            app.goTo().returnToHomePage();
        }
        if (getContactWithGroup() == null) {
            if (app.db().contacts().size() == 0) {
                app.goTo().homePage();
                app.contact().create(new ContactData().withFirstname("alexey")
                        .withLastname("yagudin"));
            } else {
                app.goTo().returnToHomePage();
                app.contact().checkContactById(app.db().contacts().iterator().next().getId());
                app.contact().addContactToGroup();
            }
        }
    }

        @Test
        public  void testContactRemoveGroup() {

            ContactData changedContact = getContactWithGroup();
            GroupData changedGroup = changedContact.getGroups().iterator().next();
            int theId = changedContact.getId();

            testGroup = changedGroup;

            app.goTo().homePage();
            app.contact().filterByGroup(testGroup.getName());
            app.contact().checkGroupForContact(changedGroup.getId());
            app.contact().checkContactById(changedContact.getId());
            app.contact().removeContactFromGroup(changedContact);
            app.goTo().returnToHomePage();

            Groups groupBefore = changedContact.ifGroups(changedGroup, false).getGroups();
            Contacts getContactListAfter = app.db().contacts();

            Groups newGroupsList = getContactListAfter.stream().filter(c -> c.getId() == theId).findFirst().get().getGroups();
            MatcherAssert.assertThat(groupBefore, CoreMatchers.equalTo(newGroupsList));
        }
}





