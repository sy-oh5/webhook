package com.example.webhook;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;
    private final TemplateEngine templateEngine;

    @Value("${url.ngrok}")
    private String ngrokUrl;
    public MailResponseDto getMail(Long id) throws ChangeSetPersister.NotFoundException {
        Mail mail = mailRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        return MailResponseDto.builder()
                .id(mail.getId())
                .readStatus(mail.getReadStatus())
                .sendDate(mail.getSendDate())
                .readDate(mail.getReadDate())
                .build();
    }

    public void readMail(Long id) {
        Optional<Mail> optionalMail = mailRepository.findByIdAndReadStatusFalse(id);
        if(optionalMail.isPresent()){
            Mail mail = optionalMail.get();
            mail.setReadStatus(true);
            mail.setReadDate(LocalDateTime.now());
            mailRepository.save(mail);
        }

    }

    @Transactional
    public void sendMail(String to) {
        try {
            // ✅ 1. 메일 데이터 저장 (DB에 메일 정보 저장)
            Mail mail = new Mail();
            mail.setSendDate(LocalDateTime.now());
            Long id = mailRepository.save(mail).getId();

            // ✅ 2. 웹훅 URL 설정 (메일 읽음 자동 처리 API)
            String webhookUrl = ngrokUrl + "/api/mails/" + id + "/read";

            // ✅ 3. Thymeleaf로 HTML 템플릿 렌더링
            Context context = new Context();
            context.setVariable("id", id);
            context.setVariable("webhookUrl", webhookUrl);
            String htmlContent = templateEngine.process("email-template", context);

            // ✅ 4. 이메일 전송
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("📩 메일 알림");
            helper.setText(htmlContent, true);  // ✅ HTML 본문 설정

            mailSender.send(message);
            System.out.println("✅ 이메일 전송 완료: " + to);

        } catch (MessagingException e) {
            System.err.println("❌ 이메일 전송 실패: " + e.getMessage());
        }
    }
}