package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.ContactData1;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
//      wd.findElement(By.name("firstname")).click();
//      wd.findElement(By.name("firstname")).clear();
//      wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
//        if(creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }


    }

    public void pressUpdate() {
      wd.findElement(By.name("update")).click();
    }

    public void fillLastname(ContactData1 contactData1, boolean creation) {
        type(By.name("lastname"), contactData1.getLastname());
//      wd.findElement(By.name("lastname")).click();
//      wd.findElement(By.name("lastname")).clear();
//      wd.findElement(By.name("lastname")).sendKeys(contactData1.getLastname());
    }

    public void pressEdit() {
      wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void deleteContact() {
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
        wd.switchTo().alert().accept();
    }
    public void checkContact() {
        wd.findElement(By.name("selected[]"));
    }

    public void submitContactCreation() {

        wd.findElement(By.name("submit"));
    }
}
