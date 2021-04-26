package addressbook.model;

public class ContactData {
    private final String firstname;
    private String group;

    public ContactData(String firstname, String group) {
        this.firstname = firstname;
        this.group = group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getGroup() {
        return group;
    }
}
