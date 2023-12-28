package pegasus;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

// DISCLAIMER: I could not care less about industry standards.

public class JacksonTest {

    // Write to file
    public static class Writer {
        public static void main(String[] args) {

            // Creates 2 Person objects
            
            Person normalPerson = new Person("Jack", 10, Person.Gender.MALE);
            Person crazyPerson = new Person("John", 11, Person.Gender.PROGRAMMER);

            try {
                // Tries to save to FILEPATH
                JacksonTest.save(crazyPerson, FILEPATH);
            } catch (IOException e) {
                // Prints error stack to System.out
                e.printStackTrace(System.out);
            }

            // Hello world!
            System.out.println("success");
        }
    }

    // Read from file
    public static class Reader {
        public static void main(String[] args) {
            try {
                // Tries to load from FILEPATH
                System.out.println(JacksonTest.load(FILEPATH));
            } catch (IOException e) {
                // Prints error stack to System.out
                e.printStackTrace(System.out);
            }
        }
    }

    //
    // All IO errors are delegated to the main methods of each test class.
    //

    public static void save(Person data, String filepath) throws IOException {
        // New file object constructed
        File file = new File(filepath);

        // Tell mapper to write to file
        MAPPER.writeValue(file, data);
    }

    public static Person load(String filepath) throws IOException {
        // New file object constructed
        File file = new File(filepath);

        // Tell mapper to read from file
        return MAPPER.readValue(file, Person.class);
    }

    // Stuff

    // YAML mapper object. Handles read/write operations. Java boilerplate is the best :)
    private static final ObjectMapper MAPPER = new YAMLMapper();

    // Don't run this on your desktop lol
    private static final String FILEPATH = "test.yml";
}
