package com.library.notificationservice.service;

import com.library.notificationservice.model.Notification;
import com.library.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification sendNotification(Notification notification) {
        notification.setStatus("SENT");
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByMember(Long memberId) {
        return notificationRepository.findByMemberId(memberId);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}