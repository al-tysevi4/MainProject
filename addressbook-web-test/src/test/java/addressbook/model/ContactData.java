package addressbook.model;

import org.openqa.selenium.WebElement;

import java.util.Objects;

public class ContactData {

    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String group;
    private String lastname;
    //private WebElement email;
    private String mobile;


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

//    public ContactData(int id, String firstname, String group, String lastname, String email, String mobile) {
//        this.firstname = firstname;
//        this.group = group;
//        this.lastname = lastname;
//        //this.email = email;
//        this.mobile = mobile;
//        this.id = Integer.MAX_VALUE;
//    }
//    public ContactData(String firstname, String group, String lastname, String mobile) {
//        this.firstname = firstname;
//        this.group = group;
//        this.lastname = lastname;
//        //this.email = this.email;
//        this.mobile = mobile;
//        //this.id = Integer.MAX_VALUE;
//    }

//    public ContactData(int id, String s, String test1, String firstname, String group) {
//        //this.id = id;
//        this.firstname = firstname;
//        this.group = group;
//        //this.lastname = lastname;
//    }
    public int getId() {
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname);
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

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", group='" + group + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

//    public String getEmail() {
//        return email;
//    }
//        public String getEmail() {
//        return String.valueOf(email);
//    }

    public String getMobile() {
        return mobile;
    }
}
