package addressbook.tests;

import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;


public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensureContactPreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("alex")
                    .withGroup("test1")
                    .withFirstname("tysevich"));
        }
    }

    @Test //(enabled = false)
  public void testContactDeletion() throws Exception {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
    //("alex", "test1", "tysevich", null));
}
