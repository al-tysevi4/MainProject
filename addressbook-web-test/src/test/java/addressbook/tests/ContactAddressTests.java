package addressbook.tests;

import addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactAddressTests extends TestBase {

    @BeforeMethod //(dataProvider = "validContactsFromJson")
    public void ensureContactPreconditions() {
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("firstname %s")
                    .withGroup("group %s")
                    .withFirstname("lastname %s"));

//        if (app.contact().list().size() == 0) {
//            app.contact().create(new ContactData()
//                    .withFirstname("alex")
//                    .withGroup("test1")
//                    .withFirstname("tysevich"));
//
        }
    }

    @Test //(dataProvider = "validContactsFromJson")
    public void testContactAddress () {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getAddress(), CoreMatchers.equalTo(contactInfoFromEditForm.getAddress()));
    }
}
