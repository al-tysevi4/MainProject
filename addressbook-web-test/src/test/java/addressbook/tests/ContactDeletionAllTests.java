package addressbook.tests;


import org.testng.annotations.*;


public class ContactDeletionAllTests extends TestBase {


  @Test
  public void testContactDeletion() throws Exception {
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().deleteSelectedGroups();
    app.getContactHelper().deletion();
  }
}
