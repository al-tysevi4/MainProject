package addressbook.tests;


import addressbook.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {
//    app.getNavigationHelper().gotoGroupPage();
//    if (! app.getGroupHelper().isThereAGroup()){
//      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
//    }
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    delete(index);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(index);
    Assert.assertEquals(before, after);

  }

  private void delete(int index) {
    app.group().checkGroup(index);
    app.group().deleteGroup();
    app.group().returnToGroupPage();
  }
}
