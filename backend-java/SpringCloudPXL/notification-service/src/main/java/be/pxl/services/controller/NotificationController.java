package be.pxl.services.controller;

import be.pxl.services.domain.Notification;
import be.pxl.services.services.INotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notification")
public class NotificationController {
    private final INotificationService notificationService;

    @PostMapping
    public void sendMessage(@RequestBody Notification notification) {
        log.info("Sending notification...");
        notificationService.sendMessage(notification);
    }
}
