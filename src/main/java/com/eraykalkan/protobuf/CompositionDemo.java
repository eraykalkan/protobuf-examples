package com.eraykalkan.protobuf;

import com.eraykalkan.models.Address;
import com.eraykalkan.models.Car;
import com.eraykalkan.models.Person;

import java.util.ArrayList;
import java.util.List;

public class CompositionDemo {
    public static void main(String[] args) {
        Address address = Address.newBuilder()
                .setPostbox(3)
                .setCity("London")
                .setStreet("Sesame")
                .build();

        Car accord = Car.newBuilder()
                .setMake("Honda")
                .setModel("Accord")
                .setYear(2020)
                .build();

        Car bmw = Car.newBuilder()
                .setMake("BMW")
                .setModel("335i")
                .setYear(2009)
                .build();

        List<Car> cars = new ArrayList<>();
        cars.add(accord);
        cars.add(bmw);

        Person sam = Person.newBuilder()
                .setName("sam")
                .setAge(25)
                //.addCar(accord)
                //.addCar(bmw)
                .addAllCar(cars)
                .setAddress(address)
                .build();

        System.out.println(sam);

    }
}
