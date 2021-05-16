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
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());

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

    public int count() {
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
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.xpath(".//td/input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String email = element.findElement(By.xpath(".//td[5]")).getText();
            String emailCom = element.findElement(By.xpath(".//td[5]")).getText();
            String emailRu = element.findElement(By.xpath(".//td[5]")).getText();
            String AllEmail = element.findElement(By.xpath(".//td[5]")).getText();
            String mobile = element.findElement(By.xpath(".//td[6]")).getText();
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String homePhone = element.findElement(By.xpath(".//td[6]")).getText();
            String workPhone = element.findElement(By.xpath(".//td[6]")).getText();
            String allPhone = element.findElement(By.xpath(".//td[6]")).getText();
            String [] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            String [] emails = element.findElement(By.xpath(".//td[5]")).getText().split("\n");
            contactCache.add(new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withAllPhone(allPhone)
                    .withGroup("test1")
//                    .withMobile(phones[1])
                    .withAddress(address)
                    //.withEmail(emails[0])
                    //.withEmailCom(emails[1])
                    //.withEmailRu(emails[2])
                    .withAllEmail(AllEmail));
//                    .withHomePhone(phones[0])
                    //.withWorkPhone(phones[2]));
        }
        return new Contacts(contactCache);
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
        contactCache = null;
        returnToHomePage();
    }
    public void modify( ContactData contact) {
        checkContactById(contact.getId());
        pressEditById(contact.getId());
        fillContactForm(contact, false);
        pressUpdate();
        contactCache = null;
        returnToHomePage();
    }

    private void pressEditById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id +"']")).click();

    }

    public void delete(ContactData contact) {
        checkContactById(contact.getId());
        deleteContact();
        contactCache = null;
        returnToHomePage();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String emailCom = wd.findElement(By.name("email2")).getAttribute("value");
        String emailRu = wd.findElement(By.name("email3")).getAttribute("value");
        String AllEmail = email + "\n"  + emailCom + "\n" + emailRu;

        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withGroup("test1")
                .withLastname(lastname)
                .withMobile(mobile)
                .withHomePhone(homePhone)
                .withWorkPhone(workPhone)
                .withEmail(email)
                .withEmailCom(emailCom)
                .withEmailRu(emailRu)
                .withAllEmail(AllEmail)
                .withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
        //cells.get(6).findElement(By.tagName("a")).click();

//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
//        wd.findElement(By.xpath(String.format("//tr[.//input[value='%s']]/td[8]/a", id))).click();
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

}
