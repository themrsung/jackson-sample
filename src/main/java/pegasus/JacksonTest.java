package pegasus;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

public class JacksonTest {

    // Write to file
    public static class Writer {
        public static void main(String[] args) {
            Person normalPerson = new Person("Jack", 10, Person.Gender.MALE);
            Person crazyPerson = new Person("John", 11, Person.Gender.PROGRAMMER);

            try {
                JacksonTest.save(crazyPerson, FILEPATH);
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }

            System.out.println("success");
        }
    }

    // Read from file
    public static class Reader {
        public static void main(String[] args) {
            try {
                System.out.println(JacksonTest.load(FILEPATH));
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public static void save(Person data, String filepath) throws IOException {
        File file = new File(filepath);
        MAPPER.writeValue(file, data);
    }

    public static Person load(String filepath) throws IOException {
        File file = new File(filepath);
        return MAPPER.readValue(file, Person.class);
    }

    // Stuff

    private static final ObjectMapper MAPPER = new YAMLMapper();
    private static final String FILEPATH = "test.yml";
}
