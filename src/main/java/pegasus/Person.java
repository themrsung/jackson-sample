package pegasus;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Person(
        @JsonProperty // Annotation not required, but recommended. See also @JsonIgnore if you're interested.
        String name,

        @JsonProperty
        int age,

        @JsonProperty
        Gender gender
) {
    public static enum Gender {
        MALE,
        FEMALE,
        PROGRAMMER
    }

    @Override
    public String toString() {
        // String conversion is (sort of) automatic, but manual serialization is recommended. Either mess around doing stuff or read the docs (I didn't btw)
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

// Records don't work in Java 8. They didn't exist back then. Upgrade to Java 17 or 21.
