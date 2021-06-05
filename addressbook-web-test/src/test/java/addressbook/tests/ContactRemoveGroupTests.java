package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;

import addressbook.model.Groups;
import addressbook.providers.TestDataProvider;
import org.hibernate.SessionFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

public class ContactRemoveGroupTests extends TestBase {

    private static SessionFactory sessionFactory;
    private int contactId;
    private int groupId;
    private int maxId;
    private GroupData testGroup;



    @BeforeMethod
    public void ensurePreconditions () {
        //Если нет группы - создаем
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 0"));
            app.goTo().homePage();
        }
        //Если нет контакта - создаем
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("alexey")
                    .withLastname("yagudin"));

        }
        //Считываем контакты из базы, запоминаем maxId
        Contacts result = app.db().contacts();
        for (ContactData contact : result) {
            if (contact.getId() > maxId) {
                maxId = contact.getId();
            }
        }
    }

        @Test (dataProvider = "groups.xml", dataProviderClass = TestDataProvider.class)
        public  void testContactRemoveGroup(GroupData group) {
            testGroup = group;

            //Добавляем контакт с макс id в группу
            app.goTo().homePage();
            app.contact().checkContactById(maxId);
            app.contact().addGroup(testGroup.getName());
            app.goTo().returnToHomePage();

            //Запоминаем состояние контакта с макс id до удаления группы
            Contacts before = app.db().contacts();
            ContactData contactWithGroup = null;
            for (ContactData contact : before) {
                if (contact.getId() == maxId) {
                    contactWithGroup = contact;
                }
            }

            //Удаляем контакт с макс id из группы
            app.goTo().returnToHomePage();
            app.contact().filterByGroup(testGroup.getName());
            app.contact().checkContactById(maxId);
            app.contact().removeContactFromGroup();
            app.goTo().returnToHomePage();

            Contacts after = app.db().contacts();
            ContactData contactWithoutGroup = null;
            for (ContactData contact : after) {
                if (contact.getId() == maxId) {
                    contactWithoutGroup = contact;
                }
            }

            //У контакта изначально(before) группа была
            Assert.assertFalse(
                    contactWithGroup.getGroups().stream()
                            .filter(groupData -> groupData.getName().equals(testGroup.getName()))
                            .collect(Collectors.toList())
                            .isEmpty()
            );
            Assert.assertFalse(
                    contactWithoutGroup.getGroups().stream()
                            .anyMatch(groupData -> groupData.getName().equals(testGroup.getName()))
            );
        }
}





