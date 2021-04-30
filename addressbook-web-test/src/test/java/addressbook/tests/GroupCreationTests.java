package addressbook.tests;


import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;


public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    //int before = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before + 1 );

//    app.getGroupHelper().initGroupCreation();
//    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
//    app.getGroupHelper().submitGroupCreation();
//    app.getGroupHelper().returnToGroupPage();

  }

}
