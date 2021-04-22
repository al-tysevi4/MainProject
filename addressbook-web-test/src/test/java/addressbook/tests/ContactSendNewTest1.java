package addressbook.tests;

import org.testng.annotations.*;



public class ContactSendNewTest1 extends TestBase{


  @Test
  public void testContactSendNewEmail() throws Exception {
    checkContact();
    sendEmail();
  }

}
