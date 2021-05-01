package addressbook.tests;


import addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.*;


public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddNewPage();
    int before  = app.getContactHelper().getContactCount();
    app.getContactHelper().fillContactForm(new ContactData("alex", "test1"),true);
//    int after  = app.getContactHelper().getContactCount();
//    Assert.assertEquals(after, before + 1);
    app.getContactHelper().submitContactCreation();
//    int after  = app.getContactHelper().getContactCount();
//    Assert.assertEquals(after, before + 1);
    app.getNavigationHelper().returnToHomePage();
    int after  = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
    app.logout();

  }
}
