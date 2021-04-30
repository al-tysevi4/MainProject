package addressbook.tests;


import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;


public class GroupDeletionTests extends TestBase{


  @Test
  public void testGroupDeletion() throws Exception {
    //int before = app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().checkGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before -1);
  }
}
