package com.duydev.backend.infrastructure.util;

import com.duydev.backend.domain.exception.AppException;
import com.duydev.backend.domain.exception.EnumException;
import com.duydev.backend.infrastructure.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

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
                    new PhoneNumber("+84363270979"),
                    twilioConfig.getMessagingServiceSid(),
                    messageBody).create();
            log.info("Message sent to {} with SID: {}", to, message.getSid());
        } catch (Exception e) {
            log.info("Cause: {}", e.getMessage());
            throw new AppException(EnumException.SEND_SMS_FAIL);
        }
    }
}
