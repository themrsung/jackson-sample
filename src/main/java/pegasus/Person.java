package pegasus;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Person(
        @JsonProperty
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
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
