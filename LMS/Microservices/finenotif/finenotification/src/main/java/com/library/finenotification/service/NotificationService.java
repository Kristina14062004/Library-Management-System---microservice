package com.library.finenotification.service;

public interface NotificationService {
    void sendNotification(Long userId, String message);
}
