package com.duydev.backend.util;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.duydev.backend.config.TwilioConfig;
import com.duydev.backend.exception.AppException;
import com.duydev.backend.exception.EnumException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "TwilioUtil")
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(TwilioConfig.class)
public class TwilioUtil {
    private final TwilioConfig twilioConfig;

    public void sendMessageSMS(String to, String messageBody) {
        // Init Twilio
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        // Create message to send
        try {
            Message message = Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(twilioConfig.getPhoneNumber()),
                    messageBody).create();
            log.info("Message sent to {} with SID: {}", to, message.getSid());
        } catch (Exception e) {
            log.error("Failed to send message to {}: {}", to, e.getMessage());
            throw new AppException(EnumException.SEND_SMS_FAIL);
        }
    }
}
