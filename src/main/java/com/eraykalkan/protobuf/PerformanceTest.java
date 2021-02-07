package com.eraykalkan.protobuf;

import com.eraykalkan.json.PersonJson;
import com.eraykalkan.models.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.IOException;

/**
 * To test and compare serialization / deserialization performance
 * of json and protobuf
 */
public class PerformanceTest {

    public static void main(String[] args) {

        PersonJson person = new PersonJson();
        person.setName("eray");
        person.setAge(30);
        ObjectMapper mapper = new ObjectMapper();


        Runnable jsonRunnable = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(person);
                System.out.println(bytes.length);
                PersonJson personJson = mapper.readValue(bytes,PersonJson.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };


        Person personProtobuf = Person.newBuilder()
                .setName("Eray")
                .setAge(30)
                .build();


        Runnable protobufRunnable = () -> {
            try {
                byte[] bytes = personProtobuf.toByteArray();
                System.out.println(bytes.length);
                Person eray = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        runPerformanceTest(jsonRunnable,"Json");
        runPerformanceTest(protobufRunnable,"Protobuf");
    }

    private static void runPerformanceTest(Runnable runnable,String method) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 5_000_000; i++) {
            runnable.run();
        }

        long endTime = System.currentTimeMillis();

        System.out.println(method + " took: " + (endTime-startTime) + " ms to execute");
    }

}
