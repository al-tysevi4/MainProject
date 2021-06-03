package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTests extends TestBase {
    private static SessionFactory sessionFactory;
    private ContactData changedContact;
    private int maxId;

    @BeforeMethod
    public void ensurePreconditions() {

        Contacts result = app.db().contacts();

        for (ContactData contact : result) {
            if (contact.getId() > maxId) {
                maxId = contact.getId();
            }
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("alexey")
                    .withLastname("yagudin"));
        }
    }

    @Test
    public void testContactAddGroup() throws Exception {

        //1. Берем контакт из БД по maxID до добавления его в группу
        ContactData contactInitial = null;
        Contacts before = app.db().contacts();
        for (ContactData contact : before) {
            if (contact.getId() == maxId) {
                contactInitial = contact;
            }
        }
        app.goTo().homePage();
        //2. Присвоение контакту группы
        app.contact().checkContactById(maxId);
        app.contact().pressAddTo();
        app.goTo().homePage();
        //3. Берем из БД контакт, которому мы добавили группу
        Contacts after = app.db().contacts();
        ContactData contactWithGroup = null;
        for (ContactData contact : after) {
            if (contact.getId() == maxId) {
                contactWithGroup = contact;
            }
        }
        //4. У контакта отсутвует группа test 0
        //assert contactInitial != null;
        Assert.assertFalse(
                contactInitial.getGroups().stream()
                .anyMatch(groupData -> groupData.getName().equals("test 0")
                )
        );
        //5. У конктакта есть группа test 0
        Assert.assertTrue(
                contactWithGroup.getGroups().stream()
                .anyMatch(groupData -> groupData.getName().equals("test 0"))
        );
    }
}
