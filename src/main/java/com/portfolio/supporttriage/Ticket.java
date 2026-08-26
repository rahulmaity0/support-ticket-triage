package com.portfolio.supporttriage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private String content;
    private String category; // Assigned by AI
    private String sentiment; // Assigned by AI
    private String status; // NEW, PROCESSED, RESOLVED
    private LocalDateTime createdAt = LocalDateTime.now();
}
