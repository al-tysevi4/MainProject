package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.*;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }
}
