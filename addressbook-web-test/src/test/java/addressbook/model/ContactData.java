package addressbook.model;

import java.util.Objects;

public class ContactData {
    //private final String id;
    private final String firstname;
    private String group;
    private final String lastname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstname, that.firstname) && Objects.equals(group, that.group) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, group, lastname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", group='" + group + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public ContactData(String firstname, String lastname, String group) {
        //this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;

    }

    //    public String getId() {
//        return id;
//    }

//    public ContactData(String id, String firstname, String group) {
//        this.id = id;
//        this.firstname = firstname;
//        this.group = group;
//    }

    public String getFirstname() {
        return firstname;
    }

    public String getGroup() {
        return group;
    }
    public String getLastname() {
        return lastname;
    }

}
