package com.library.notificationservice.repository;

import com.library.notificationservice.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByMemberId(Long memberId);
    List<Notification> findByStatus(String status);
}