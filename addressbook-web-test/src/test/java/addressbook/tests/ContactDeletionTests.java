package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {



  @Test
  public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewPage();
      app.getContactHelper().fillContactForm(new ContactData("alex", "test1"),true);
      app.getContactHelper().submitContactCreation();
      //app.getNavigationHelper().returnToHomePage();
    }
    app.getContactHelper().checkContact();
    app.getContactHelper().deleteContact();
    //app.getNavigationHelper().returnToHomePage();
  }
}
