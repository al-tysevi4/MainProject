package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;

import addressbook.model.Groups;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactRemoveGroupTests extends TestBase {

    private static SessionFactory sessionFactory;
    private int contactId;
    private int groupId;
    private int maxId;


    @BeforeMethod
    public void ensurePreconditions () {

//        Contacts contacts = app.db().contacts();
//        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("alexey")
                    .withLastname("yagudin")
                    .withGroup("test 0"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 0"));
            app.goTo().homePage();
        }
    }

        @Test
        public  void testContactRemoveGroup() {

            Contacts result = app.db().contacts();

            for (ContactData contact : result) {
                if (contact.getId() > maxId) {
                    maxId = contact.getId();
                }
            }

            app.goTo().homePage();
            app.contact().checkContactById(maxId);
            app.contact().removeContactFromGroup();
            app.goTo().homePage();

            Contacts after = app.db().contacts();
            ContactData contactWithoutGroup = null;
            ContactData contactWithGroup = null;
            for (ContactData contact : after) {
                if (contact.getId() == maxId) {
                    contactWithoutGroup = contact;
                    contactWithGroup = contact;
                }
            }

            Assert.assertFalse(
                    contactWithoutGroup.getGroups().stream()
                            .anyMatch(groupData -> groupData.getName().equals("test 0")
                            )
            );
            Assert.assertTrue(
                    contactWithGroup.getGroups().stream()
                            .anyMatch(groupData -> groupData.getName().equals("test 0"))
            );
        }
}





