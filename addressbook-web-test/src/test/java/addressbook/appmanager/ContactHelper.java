package addressbook.appmanager;

import addressbook.model.ContactData;
import addressbook.model.ContactData1;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
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

    public void pressEdit() {
      //wd.findElement(By.xpath("//img[@alt='Edit']")).get(index).click();
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

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.xpath(".//td/input")).getAttribute("value"));
            String firstname = element.findElement(By.xpath(".//td[2]")).getText();
            ContactData contact = new ContactData(id, firstname, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
