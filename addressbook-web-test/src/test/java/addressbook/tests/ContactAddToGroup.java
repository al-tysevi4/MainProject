package addressbook.tests;

import org.testng.annotations.*;

public class ContactAddToGroup extends TestBase{



  @Test
  public void testContactAddToGroup() throws Exception {
      app.clickGroup();
      app.addToGroup();
  }

}
