package addressbook.tests;

import addressbook.model.ContactData;
import addressbook.model.Contacts;
import addressbook.model.GroupData;
import addressbook.model.Groups;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
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
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.getProperties().getProperty("source.contacts.xml"))))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    JsonDeserializer<File> deserializer = (json, typeOfT, context) -> new File(json.getAsJsonPrimitive().getAsString());
    Gson gson = new GsonBuilder().registerTypeAdapter(File.class, deserializer).create();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.getProperties().getProperty("source.contacts.json"))))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      //Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test  (dataProvider = "validContactsFromJson") //(enabled = false)
  public void testContactCreation(ContactData contact) throws Exception {
    if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/AndroidBug.jpg");
    app.contact().create(contact.inGroups(groups.iterator().next()));

    app.goTo().homePage();
    Contacts before = app.db().contacts();
    //app.contact().create(contact);

    assertThat(app.contact().count(), equalTo(before.size())); // ?????????? before.size() +1
    Contacts after = app.db().contacts();
    //app.logout();
    assertThat(after, equalTo(
            before.withAdded(
                    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    verifyContactListInUi();
  }
}
