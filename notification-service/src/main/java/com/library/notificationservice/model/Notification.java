package com.library.notificationservice.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String type;
    // e.g. "BORROW_CONFIRMATION", "DUE_REMINDER", "OVERDUE_ALERT", "RESERVATION_CONFIRMATION"

    private String message;

    private String status;
    // e.g. "SENT", "PENDING", "FAILED"

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}