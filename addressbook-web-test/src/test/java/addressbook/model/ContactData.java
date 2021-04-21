package addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String mobile;
    private final String email;
    private final String bdayear;
    private final String bday;
    private final String bmonth;
    private final String byear;

    public ContactData(String firstname, String lastname, String address, String mobile, String email, String bdayear, String bday, String bmonth, String byear) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.bdayear = bdayear;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getBdayear() {
        return bdayear;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }
}
