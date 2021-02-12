package com.eraykalkan.protobuf;


import com.eraykalkan.models.Credentials;
import com.eraykalkan.models.EmailCredentials;
import com.eraykalkan.models.PhoneOTP;

/**
 * oneof is used to use only one option
 * in this example, let's suppose we have two credentials to login
 * one is email, the other is via phone otp
 * if we set both in the credentials, the result will be the last set one
 * previously set objects will be erased
 * we only need to set one and use it for oneof
 * we can also add an error to the object to show in default case
 */

public class OneOfDemo {
    public static void main(String[] args) {

        EmailCredentials emailCredential = EmailCredentials.newBuilder()
                .setEmail("test@test.com")
                .setPassword("admin123")
                .build();

        PhoneOTP phoneOTP = PhoneOTP.newBuilder()
                .setNumber(1234123)
                .setCode(456)
                .build();

        Credentials credentials = Credentials.newBuilder()
                .setEmailMode(emailCredential)
                .setPhoneMode(phoneOTP)
                .build();

        login(credentials);

    }

    private static void login(Credentials credentials){

        System.out.println(credentials.getEmailMode());

        // how to know which mode is set?
        // protobuf automatically creates enum for us
        switch (credentials.getModeCase()) {
            case EMAILMODE:
                System.out.println(credentials.getEmailMode());
                break;
            case PHONEMODE:
                System.out.println(credentials.getPhoneMode());
                break;
            default:
                System.out.println("no mode is selected");
        }

    }
}
