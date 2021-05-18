package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromXml () throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test  (dataProvider = "validContactsFromJson") //(enabled = false)
  public void testContactCreation(ContactData contact) throws Exception {
    //File photo = new File("src/test/resources/AndroidBug.jpg");
//    ContactData contact = new ContactData()
//            .withFirstname("alexey")
//            .withGroup("test1")
//            .withLastname("tysevich")
//            .withMobile("+345678945555")
//            .withAddress("Moscow, Lenina 1")
//            .withEmail("12345@mail")
//            .withHomePhone("333555")
//            .withWorkPhone("555333")
//            .withEmailCom("23456@mail.com")
//            .withEmailRu("34567@mail.ru");
            //.withPhoto(photo);
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    //app.logout();
    assertThat(after, equalTo(
            before.withAdded(
                    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
