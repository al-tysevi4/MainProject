package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.annotations.*;


public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewPage();
    app.getContactHelper().fillContactForm(new ContactData("alex", "test1"),true);
    app.getContactHelper().submitContactCreation();
    //app.getNavigationHelper().returnToHomePage();
  }
}
