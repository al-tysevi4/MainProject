package addressbook.tests;

import org.testng.annotations.*;



public class ContactSendEmailTests extends TestBase{

  @Test
  public void testContactSendEmail() throws Exception {
    app.getContactHelper().checkTheContact();
    app.getContactHelper().sendAnEmail();
  }
}
