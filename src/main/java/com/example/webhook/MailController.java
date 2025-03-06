package com.example.webhook;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mails")
@RequiredArgsConstructor
@Tag(name = "Mail API", description = "메일 관련 API")
public class MailController {

    private final MailService mailService;


    @Operation(summary = "메일 보내기", description = "메일 보내기")
    @PostMapping("/send")
    public ResponseEntity<Boolean> sendMail(
            @RequestBody String to
    ) {
        mailService.sendMail(to);
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "메일 읽음 처리", description = "메일 읽음 처리")
    @GetMapping("/{id}/read")
    public ResponseEntity<Boolean> readMail(
            @PathVariable(name = "id") Long id
    )  {
        mailService.readMail(id);
        return ResponseEntity.ok(true);
    }

    @Operation(summary = "메일 결과 확인", description = "메일 결과 확인")
    @GetMapping("/{id}")
    public ResponseEntity<MailResponseDto> getMail(
            @PathVariable(name = "id") Long id
    ) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(mailService.getMail(id));
    }
}
