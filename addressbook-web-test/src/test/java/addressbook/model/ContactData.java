package addressbook.model;

import java.util.Objects;

public class ContactData {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private final String firstname;
    private String group;
    private final String lastname;

    public ContactData(String firstname, String lastname) {
        this.id = 0;
        this.firstname = firstname;
        this.lastname = lastname;
        this.group = group;

    }

        public int getId() {
        return id;
    }

    public ContactData(int id, String firstname, String group) {
        this.id = id;
        this.firstname = firstname;
        this.group = group;
        this.lastname = null;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(id, that.id) && Objects.equals(firstname, that.firstname) && Objects.equals(group, that.group) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, group, lastname);
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
}
