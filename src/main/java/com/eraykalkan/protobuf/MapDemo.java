package com.eraykalkan.protobuf;

import com.eraykalkan.models.BodyStyle;
import com.eraykalkan.models.Car;
import com.eraykalkan.models.Dealer;

public class MapDemo {

    public static void main(String[] args) {

        Car accord = Car.newBuilder()
                .setMake("Honda")
                .setModel("Accord")
                .setYear(2020)
                .setBodyStyle(BodyStyle.SEDAN)
                .build();

        Car bmw = Car.newBuilder()
                .setMake("BMW")
                .setModel("335i")
                .setYear(2009)
                .build();

        Dealer dealer = Dealer.newBuilder()
                .putModel(2009,bmw)
                .putModel(2020,accord)
                .build();

        System.out.println(dealer.getModelOrThrow(2009));

        System.out.println(dealer.getModelMap());

        // if we don't set a value for enum, the first index will be picked
        // it is going to output SEDAN which is at the 0th index
        // we need a default case for enum ie. UNNOWN = 0
        System.out.println(dealer.getModelOrThrow(2009).getBodyStyle());

    }

}
