package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensureContactPreconditions() {
        if(app.db().contacts().size() == 0) {
            app.goTo().homePage();
                app.contact().create(new ContactData()
                        .withFirstname("firstname %s")
                        .withGroup("group %s")
                        .withFirstname("lastname %s"));
//        app.goTo().homePage();
//        if (app.contact().list().size() == 0) {
//            app.contact().create(new ContactData()
//                    .withFirstname("alex")
//                    .withGroup("test1")
//                    .withFirstname("tysevich"));

        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getAllPhone(), CoreMatchers.equalTo(mergePhones(contactInfoFromEditForm)));

//        MatcherAssert.assertThat(contact.getHomePhone(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
//        MatcherAssert.assertThat(contact.getMobile(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getMobile())));
//        MatcherAssert.assertThat(contact.getWorkPhone(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobile(), contact.getWorkPhone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
