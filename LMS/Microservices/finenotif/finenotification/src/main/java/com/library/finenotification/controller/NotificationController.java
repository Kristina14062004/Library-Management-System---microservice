package com.library.finenotification.controller;

import com.library.finenotification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public void send(
            @RequestParam Long userId,
            @RequestParam String message) {

        service.sendNotification(userId, message);
    }
}
