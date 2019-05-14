package com.eshop.sender;

import com.eshop.domain.Order;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SmsSender {
    public static final String ACCOUNT_SID =
            "AC2fb05feeb0cad0577733d937863bb63a";
    public static final String AUTH_TOKEN =
            "d8e061e42f6d5afbabe048fb9a307c41";

    public static void sendSMS(String phoneNumber, int orderID) {
        String body = "Order  N" + orderID + " confirmed";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber(phoneNumber), // to
                         new PhoneNumber("+12029196756"), // from
                         body)
                .create();
        System.out.println(message.getSid());
    }
}
