package com.example.webhook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, Long> {
    Optional<Mail> findByIdAndReadStatusFalse(Long id);
}
