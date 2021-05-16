package addressbook.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String group;
    private String lastname;
    private String email;
    private String mobile;
    private String allPhone;
    private String homePhone;
    private String workPhone;
    private String address;
    private String emailCom;
    private String emailRu;
    private String AllEmail;

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }
    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmailCom(String emailCom) {
        this.emailCom = emailCom;
        return this;
    }
    public ContactData withEmailRu(String emailRu) {
        this.emailRu = emailRu;
        return this;
    }
    public ContactData withAllEmail(String AllEmail) {
        this.AllEmail = AllEmail;
        return this;
    }
    public ContactData withAllPhone(String allPhone) {
        this.allPhone = allPhone;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public int getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getGroup() {
        return group;
    }
    public String getLastname() {
        return lastname;
    }
    public String getMobile() { return mobile; }
    public String getAllPhone() { return allPhone; }
    public String getHomePhone() { return homePhone; }
    public String getWorkPhone() { return workPhone; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public String getEmailCom() { return emailCom; }
    public String getEmailRu() { return emailRu; }
    public String getAllEmail() { return  AllEmail; }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", group='" + group + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}
