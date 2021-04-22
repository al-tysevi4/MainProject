package addressbook.appmanager;

import addressbook.model.ContactData1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void homeAndLogout() {
      wd.findElement(By.linkText("home")).click();
      wd.findElement(By.linkText("Logout")).click();
    }

    public void submitContactCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData1 contactData1, String aleksandr, String tysevich, String s, String s1, String s2, String s3, String october, String s4) {// String aleksandr, String tysevich, String s, String s1, String s2, String s3, String october, String s4) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(contactData1.getFirstname());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(contactData1.getLastname());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(contactData1.getAddress());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(contactData1.getMobile());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(contactData1.getEmail());
      wd.findElement(By.name("bday")).click();
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData1.getBday());
      wd.findElement(By.xpath("//option[@value='20']")).click();
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData1.getBmonth());
      wd.findElement(By.xpath("//option[@value='October']")).click();
      wd.findElement(By.name("byear")).click();
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys(contactData1.getByear());
    }

    public void gotoAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void sendAnEmail() {
      wd.findElement(By.xpath("//input[@value='Send e-Mail']")).click();
    }

    public void checkTheContact() {
      wd.findElement(By.id("25")).click();
    }

    public void deletion() {
       wd.switchTo().alert().accept();
     }

    public void deleteSelectedGroups() {
       wd.findElement(By.xpath("//input[@value='Delete']")).click();
     }

    public void selectAllContacts() {
       wd.findElement(By.id("MassCB")).click();
     }
}
