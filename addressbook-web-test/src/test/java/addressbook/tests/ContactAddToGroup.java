package addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ContactAddToGroup extends TestBase{



  @Test
  public void testContactAddToGroup() throws Exception {
      clickGroup();
      addToGroup();
  }

}
