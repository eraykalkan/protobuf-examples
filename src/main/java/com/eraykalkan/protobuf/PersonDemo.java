package com.eraykalkan.protobuf;

import com.eraykalkan.models.Person;
import com.google.protobuf.Int32Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemo {
    public static void main(String[] args) throws IOException {
        Person eray = Person.newBuilder()
                .setName("Eray")
                .setAge(30)
                // we added wrapper class Integer here
                // we can use has methods for wrapper classes but not for primitives
                .setBirthYear(Int32Value.newBuilder().setValue(1980).build())
                .build();

        //write the object to disk to test serialization / deserialization
        Path path = Paths.get("eray.ser");
        // after writing to disk, comment out this line and read from file as follows
        //Files.write(path,eray.toByteArray());

        byte[] bytes = Files.readAllBytes(path);
        Person newEray = Person.parseFrom(bytes);

        System.out.println(newEray);

        System.out.println(newEray.hasBirthYear());
    }
}
