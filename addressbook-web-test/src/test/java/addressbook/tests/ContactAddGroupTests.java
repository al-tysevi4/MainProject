package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.providers.TestDataProvider;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddGroupTests extends TestBase {
    private static SessionFactory sessionFactory;
    private ContactData changedContact;
    private int maxId;
    private GroupData testGroup;

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("alexey")
                    .withLastname("yagudin"));
        }
        Contacts result = app.db().contacts();

        for (ContactData contact : result) {
            if (contact.getId() > maxId) {
                maxId = contact.getId();
            }
        }
    }

    //@Test
    @Test (dataProvider = "groups.xml", dataProviderClass = TestDataProvider.class)
    public void testContactAddGroup(GroupData group) throws Exception {
        testGroup = group;
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
        app.contact().addGroup(testGroup.getName());
        app.goTo().returnToHomePage();
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
                .anyMatch(groupData -> groupData.getName().equals(testGroup.getName())
                )
        );
        //5. У конктакта есть группа test 0
        Assert.assertTrue(
                contactWithGroup.getGroups().stream()
                .anyMatch(groupData -> groupData.getName().equals(testGroup.getName()))
        );
    }
    @AfterMethod
    public void removeContactFromGroup() {
        app.goTo().returnToHomePage();
        app.contact().filterByGroup(testGroup.getName());
        app.contact().checkContactById(maxId);
        app.contact().removeContactFromGroup();
        app.goTo().returnToHomePage();
    }
}
