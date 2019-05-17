package com.eshop.sender;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.logging.Logger;

/**
 * Class for SMS messaging.
 */
public class SmsSender {

    public static final String ACCOUNT_SID =
            "AC2fb05feeb0cad0577733d937863bb63a";
    public static final String AUTH_TOKEN =
            "d8e061e42f6d5afbabe048fb9a307c41";

    private static Logger logger = Logger.getLogger("logger");

    private SmsSender() {
    }

    /**
     * Sends SMS message about order to client phone number.
     * @param phoneNumber client's phone number
     * @param orderID id of the client's order
     */
    public static void sendSMS(String phoneNumber, int orderID) {
        String body = "Order  N" + orderID + " confirmed";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(phoneNumber),
                        new PhoneNumber("+12029196756"),
                        body).create();
        logger.info("SMS has been sent");
    }
}
