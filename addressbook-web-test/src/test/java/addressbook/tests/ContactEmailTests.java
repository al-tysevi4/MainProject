package addressbook.tests;

import addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ContactEmailTests extends TestBase {
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
//
        }
    }
    @Test
    public void testContactEmail() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getAllEmail(), CoreMatchers.equalTo(mergeEmail(contactInfoFromEditForm)));

//        MatcherAssert.assertThat(contact.getEmail(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getEmail())));
//        MatcherAssert.assertThat(contact.getEmailCom(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getEmailCom())));
//        MatcherAssert.assertThat(contact.getEmailRu(), CoreMatchers.equalTo(cleaned(contactInfoFromEditForm.getEmailRu())));
    }
    private String mergeEmail (ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmailCom(), contact.getEmailRu())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String email) {
        return email.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
