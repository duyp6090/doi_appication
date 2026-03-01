package com.duydev.backend.infrastructure.rabbitmq;

import com.duydev.backend.application.service.interfaceservice.IRentalDelayRequestConsumer;
import com.duydev.backend.domain.enums.StatusBooking;
import com.duydev.backend.domain.model.BookingEntity;
import com.duydev.backend.domain.repositories.BookingRepository;
import com.duydev.backend.infrastructure.config.RabbitmqConfig;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j(topic = "Rental-Delay-Request-Consumer")
@Service
@RequiredArgsConstructor
public class RentalDelayRequestConsumer implements IRentalDelayRequestConsumer {
    private final BookingRepository bookingRepository;

    @RabbitListener(queues = RabbitmqConfig.DLX_QUEUE_NAME)
    @Transactional
    public void receiveDelayRequest(String message) {
        log.info("Received delay request rental: {}", message);
        // Step by step
        // 1. Convert message to id booking
        Long bookingId = Long.valueOf(message);

        log.info("Processing timeout for booking id: {}", bookingId);

        // 2. Update status booking to CANCELLED
        BookingEntity booking = bookingRepository.findByIdForUpdate(bookingId)
                .orElse(null);
        if (booking != null && booking.getStatus() == StatusBooking.PENDING) {
            booking.setStatus(StatusBooking.CANCELLED);
            bookingRepository.save(booking);
            log.info("Booking id {} has been cancelled due to timeout.", bookingId);
        }
    }

}
