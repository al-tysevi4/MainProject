package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.ContactData1;
import addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        //type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobile());

        if(creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void pressUpdate() {
      click(By.name("update"));
    }

    public void fillLastname(ContactData contactData) {
        type(By.name("lastname"), contactData.getLastname());
    }

    public void fillFirstName(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
    }

    public void pressEdit(int index) {
      //wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }
    public void checkContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        //click(By.name("selected[]"));
    }
    public void checkContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
        //click(By.name("selected[]"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.xpath(".//td/input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
//            WebElement  email = element.findElement(By.xpath(".//td[5]"));
//            String emailText;
//            if (email != null) {
//                emailText = email.getText();
//            }
            //String email = element.findElement(By.xpath(".//td[5]")).getText();
            String mobile = element.findElement(By.xpath(".//td[6]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
//            ContactData contact = new ContactData()
//                    .withId(id)
//                    .withFirstname(firstname)
//                    .withGroup("test1")
//                    .withLastname(lastname)
//                    .withMobile(mobile);
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withGroup("test1")
                    .withLastname(lastname)
                    .withMobile(mobile));
            //(firstname,"test1", lastname, mobile);
        }
        return contacts;
    }
    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.xpath(".//td/input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
//            WebElement  email = element.findElement(By.xpath(".//td[5]"));
//            String emailText;
//            if (email != null) {
//                emailText = email.getText();
//            }
            //String email = element.findElement(By.xpath(".//td[5]")).getText();
            String mobile = element.findElement(By.xpath(".//td[6]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
//            ContactData contact = new ContactData()
//                    .withId(id)
//                    .withFirstname(firstname)
//                    .withGroup("test1")
//                    .withLastname(lastname)
//                    .withMobile(mobile);
            contacts.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withGroup("test1")
                    .withLastname(lastname)
                    .withMobile(mobile));
            //(firstname,"test1", lastname, mobile);
        }
        return contacts;
    }
    public void gotoAddNewPage() {
        click(By.linkText("add new"));
    }

    public void homePage() {
        if (! isElementPresent(By.id("maintable"))) {
            return;
        }
        wd.findElement(By.linkText("home")).click();
    }
    public void returnToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }
    public void create(ContactData contact) {
        gotoAddNewPage();
        fillContactForm(contact, true);
        submitContactCreation();
        returnToHomePage();
    }
    public void modify( ContactData contact) {
        checkContactById(contact.getId());
        pressEditById(contact.getId());
        fillContactForm(contact, false);
        pressUpdate();
        returnToHomePage();
    }

    private void pressEditById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id +"']")).click();
        //wd.findElement(By.xpath("//img[@alt='Edit" + id +"']")).click();
    }
    public void delete(int index) {
        checkContact(index);
        deleteContact();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        checkContactById(contact.getId());
        deleteContact();
        returnToHomePage();
    }
}
