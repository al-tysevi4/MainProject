package addressbook.tests;


import org.testng.annotations.*;


public class ContactDeletionTests extends TestBase {



  @Test
  public void testContactDeletion() throws Exception {
    app.checkContact();
    app.deleteContact();

  }
}