package com.duydev.backend.util;

import org.springframework.stereotype.Component;

import com.duydev.backend.domain.enums.StatusBooking;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "NotificationUtil")
@Component
public class NotificationUtil {
    public void sendNotification(StatusBooking statusBooking, String email) {

    }
}
