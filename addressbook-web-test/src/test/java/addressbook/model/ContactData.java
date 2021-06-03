package addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Transient
    private String group;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Transient
    //@Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Transient
    private String allPhone;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Transient
    private String emailCom;
    @Transient
    private String emailRu;
    @Transient
    private String AllEmail;
    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    //@Transient
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public File getPhoto() {
        if (photo == null) {
            return null;
        } else {
            return new File(photo);
        }

        //return new File(photo);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }
    public Groups getGroups() {
        return new Groups(groups);
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
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
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

    public ContactData inGroups(GroupData group) {
        groups.add(group);
        return this;
    }
}
