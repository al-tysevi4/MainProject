package mantis.tests;


import mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;


public class TestBase {
    //Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite //(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.back", "config_inc.php");
        app.stop();
    }
}
