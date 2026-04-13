package com.library.finenotification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.finenotification.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {
}
