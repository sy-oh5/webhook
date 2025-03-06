package com.example.webhook;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
public class MailResponseDto {
    private long id;
    private LocalDateTime sendDate;
    private Boolean readStatus;
    private LocalDateTime readDate;
}
