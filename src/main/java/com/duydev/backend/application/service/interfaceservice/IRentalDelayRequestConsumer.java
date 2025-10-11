package com.duydev.backend.application.service.interfaceservice;

public interface IRentalDelayRequestConsumer {
    void receiveDelayRequest(String message);
}
