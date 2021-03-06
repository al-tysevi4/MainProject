package mantis.appmanager;

import mantis.model.MailMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private Properties properties;
    private WebDriver wd;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
       if (wd !=  null) {
           wd.quit();
       }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }
    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.FIREFOX) ) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.SAFARI)) {
                wd = new SafariDriver();
            }
            wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public void goToManagement() {
        wd.findElement(By.xpath("//li[6]/a/i")).click();
    }

    public void goToUsersManagement() {
        wd.findElement(By.xpath("//a[contains(text(),'???????????????????? ????????????????????????????')]")).click();
    }

    public void resetPassword() {
        wd.findElement(By.xpath("//input[@value='???????????????? ????????????']")).click();

    }

    public void takeUser(String user) {
        wd.findElement(By.xpath("//a[text()='" + user + "']")).click();
    }

    public void proceed() {
        wd.findElement(By.xpath("//a[contains(text(), '????????????????????')]")).click();
    }
}
