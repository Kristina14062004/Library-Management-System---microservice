package com.library.finenotification.serviceimpl;

import com.library.finenotification.entity.Notification;
import com.library.finenotification.repository.NotificationRepository;
import com.library.finenotification.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repo;

    public NotificationServiceImpl(NotificationRepository repo) {
        this.repo = repo;
    }

    @Override
    public void sendNotification(Long userId, String message) {

        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage(message);
        n.setStatus("SENT");
        n.setCreatedAt(LocalDateTime.now());

        repo.save(n);
    }
}
