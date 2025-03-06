package com.example.webhook;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "mail")
@Getter
@Setter
@NoArgsConstructor
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "send_date", nullable = false)
    private LocalDateTime sendDate;

    @Column(name = "read_status", nullable = false)
    private Boolean readStatus = false;

    @Column(name = "read_date")
    private LocalDateTime readDate;
}
