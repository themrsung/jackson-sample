package pegasus;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;

// COPYRIGHT: I don't care, do whatever you want with this.

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

/**

OK I'll try to explain what's going on. I'm not waiting for IntelliJ to index just to edit this file, so I'm doing it on GitHub web UI. Forgive any typos.

During the static initialization of the class JacksonTest, a YAMLMapper named MAPPER is created. (Line 75)
A constant FILEPATH is also defined. (Line 78)

Any code can now call either JacksonTest::save or JacksonTest::load. Both methods delegate the IOException to the outer scope. (Meaning you'll have to try-catch)
See the two runnable main methods for how they're used.

Now about the Person class. An arbitrary data type was used to demonstrate Jackson's abilities to serialize and deserialize custom objects.

You can either:

A)
- Add an all-args constructor (it can be private) for *every single variable* of the data class. If the class contains another object as a variable,
the object should have an all-args constructor as well. (until a primitive type or things like String, UUID are reached)
- Do NOT override Object::toString.
- Pray to the Java gods.
- NEVER add a variable EVER.
- Put @JsonIgnore on all methods.

B)
- Override Object::toString to a format of your choice.
- Understand how Jackson handles deserialization, and it's limitations.

When given serialized data and a class, Jackson will first look for custom deserializers defined by the dev.
If no deserializer was designated it will parse the text and look for an all-args constructor with a matching signature.
For example, when given ** {"type": "DOG", "age": 1} ** it will look for a constructor with the signature of (String, Number). <- I think it's in the order of int, long, double but you'll have to check idk

If it cannot find one, it will give up and throw an error. If you can understand that error, why are you reading this lol.
If not, search Google for "custom deserializer jackson yaml" or something. Or just ask the GPT god for a working example.

In the highly improbable event that my explanation is imperfect, clone this repo and send me a pull request so I can push one button and fix it.
Else I will do nothing.

Happy hacking! (I hate React)

*/
