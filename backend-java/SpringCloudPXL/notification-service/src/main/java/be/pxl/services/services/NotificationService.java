package be.pxl.services.services;

import be.pxl.services.domain.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService implements INotificationService {
    public void sendMessage(Notification notification) {
        log.info("Receiving notification...");
        log.info("From: " + notification.getFrom());
        log.info("To: " + notification.getTo());
        log.info("Message: " + notification.getMessage());
    }
}
