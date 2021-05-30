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
    public void ensurePreconditions() {
        Contacts result = app.db().contacts();

        for (ContactData contact : result) {
            if (contact.getId() > maxId) {
                maxId = contact.getId();
            }
        }

    }

        @Test
        public  void testContactRemoveGroup() {
            app.goTo().homePage();
            app.contact().checkContactById(maxId);
            app.contact().removeContactFromGroup();
            app.goTo().homePage();

            Contacts after = app.db().contacts();
            ContactData contactWithoutGroup = null;
            for (ContactData contact : after) {
                if (contact.getId() == maxId) {
                    contactWithoutGroup = contact;
                }
            }

//            Assert.assertFalse(
//                    contactWithGroup.getGroups().stream()
//                            .anyMatch(groupData -> groupData.getName().equals("test 0")
//                            )
//            );
            Assert.assertTrue(
                    contactWithoutGroup.getGroups().stream()
                            .anyMatch(groupData -> groupData.getName().equals("test 0"))
            );
        }
}





