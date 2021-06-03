package addressbook.tests;

import addressbook.appmanager.ApplicationManager;
import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite //(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test " + m.getName());
    }
    public void verifyGroupListInUi() {
        if (Boolean.getBoolean("verifyUi")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            MatcherAssert.assertThat(uiGroups, CoreMatchers.equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactListInUi() {
        if (Boolean.getBoolean("verifyUi")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            MatcherAssert.assertThat(uiContacts, CoreMatchers.equalTo(dbContacts.stream().map((c) -> new ContactData().withId(c.getId())
                    .withFirstname(c.getFirstname())
                    .withGroup("test1")
                    .withLastname(c.getLastname()))));
                    //.withEmail(c.getEmail())
                    //.withMobile(c.getMobile())
                    //.withAddress(c.getAddress()))));
        }
    }
}
