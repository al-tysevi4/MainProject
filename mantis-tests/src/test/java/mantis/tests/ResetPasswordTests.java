package mantis.tests;

import mantis.model.MailMessage;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    private String user;
    private String password;
    private String email;

    @BeforeMethod
    public void userRegistration() {
        long now = System.currentTimeMillis();
        user = String.format("user%s", now);
        password = "password";
        email = String.format("user%s@localhost.localdomain", now);
        app.registration().start(user, email);
        app.proceed();

        app.mail().start();
    }
    @Test
    public void testResetPassword () throws IOException, MessagingException {

        app.getDriver().get(app.getProperty("web.baseUrl") + "/index.php");
        app.getDriver().findElement(By.xpath("//input[@id='username']")).sendKeys(app.getProperty("web.adminLogin"));
        app.getDriver().findElement(By.xpath("//input[@value='Вход']")).click();
        app.getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys(app.getProperty("web.adminPassword"));
        app.getDriver().findElement(By.xpath("//input[@value='Вход']")).click();

        app.goToManagement();
        app.goToUsersManagement();
        app.takeUser(user);
        app.resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);

        app.registration().finish(confirmationLink, password);
    }
    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
