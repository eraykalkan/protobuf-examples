package com.eraykalkan.protobuf;

import com.eraykalkan.models.Person;

public class DefaultValueDemo {
    public static void main(String[] args) {

        Person person = Person.newBuilder().build();

        // this won't throw NPE
        // there is no null in protobuf
        // it will always give you the default value, blank in this case
        System.out.println("City :" + person.getAddress().getCity());

        // it must be handled with care because someone should set it to blank too
        // to check it, we need to call has methods
        // we can use it as a null check
        // has methods are generated only for composite object types ie. Address, Car
        // it is ot generated for primitives i.e. int32, string, they are always have a default value
        // for int32, default value is 0
        // for repeated, default value is emptyList
        System.out.println(person.hasAddress());



    }
}
