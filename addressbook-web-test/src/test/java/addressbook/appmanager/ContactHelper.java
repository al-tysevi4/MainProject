package addressbook.appmanager;

import addressbook.model.ContactData1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void homeAndLogout() {
        getClick(By.linkText("home"));
        getClick(By.linkText("Logout"));
    }

    public void submitContactCreation() {
      getClick(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData1 contactData1, String aleksandr, String tysevich, String s, String s1, String s2, String s3, String october, String s4) {// String aleksandr, String tysevich, String s, String s1, String s2, String s3, String october, String s4) {
        enterFirstname(By.name("firstname"), contactData1.getFirstname());
        enterFirstname(By.name("lastname"), contactData1.getLastname());
        enterFirstname(By.name("address"), contactData1.getAddress());
        enterFirstname(By.name("mobile"), contactData1.getMobile());
        enterFirstname(By.name("email"), contactData1.getEmail());
        getClick(By.name("bday"));
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData1.getBday());
        getClick(By.xpath("//option[@value='20']"));
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData1.getBmonth());
        getClick(By.xpath("//option[@value='October']"));
        enterFirstname(By.name("byear"), contactData1.getByear());
    }

    public void gotoAddNewPage() {
        getClick(By.linkText("add new"));
    }

    public void sendAnEmail() {
        getClick(By.xpath("//input[@value='Send e-Mail']"));
    }

    public void checkTheContact() {
        getClick(By.id("25"));
    }

    public void deletion() {
       wd.switchTo().alert().accept();
     }

    public void deleteSelectedGroups() {
        getClick(By.xpath("//input[@value='Delete']"));
    }

    public void selectAllContacts() {
        getClick(By.id("MassCB"));
    }
}
