package addressbook.generators;

import addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter (names = "-f", description = "Target file")
    public String file;
    @Parameter (names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void saveAsCsv (List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress(), contact.getEmail(), contact.getMobile()));
            }
        } //// Instead of writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++ ) {
            contacts.add(new ContactData().withFirstname(String.format("alex", i))
            .withLastname(String.format("dubas", i))
            .withAddress(String.format("Moscow, Lenina 1", i))
            .withEmail(String.format("alex_dubas@nail.com", i))
            .withMobile(String.format("+34999887766", i))
            .withPhoto(new File("src/test/resources/AndroidBug.jpg")));
        }
        return contacts;
    }

    private void run()  throws IOException{
        List<ContactData> contacts = generateContacts(count);
        saveAsCsv(contacts, new File(file));
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if(format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if(format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }
    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) { // Instead of writer.close();
            writer.write(json);
         }
    }
    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) { // Instead of writer.close();
            writer.write(xml);
        }
    }
}
