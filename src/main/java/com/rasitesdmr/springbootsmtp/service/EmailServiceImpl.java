package com.rasitesdmr.springbootsmtp.service;

import com.rasitesdmr.springbootsmtp.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;      // mesaji gönderen


    @Override
    public String sendSimpleMail(Email email) {

        try {
            // Basit bir e-posta oluşturma
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);  // kimden
            mailMessage.setTo(email.getRecipient());
            mailMessage.setText(email.getMessageBody());
            mailMessage.setSubject(email.getSubject());

            // Oluşturduğumuz e-postayı gönderme
            javaMailSender.send(mailMessage);
            log.info("Gönderilen Email" + mailMessage);
            return "Email başarıyla gönderildi";

        } catch (Exception exception) {
            log.error("Alınan Hata" + exception);
            return "Email gönderilirken hata oluştu";
        }
    }

    @Override
    public String sendMailWithAttachment(Email email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setText(email.getMessageBody());
            mimeMessageHelper.setSubject(email.getSubject());

            FileSystemResource fileSystemResource = new FileSystemResource(new File(email.getAttachment()));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            // Özelleştirilmiş email'i gönderme
            javaMailSender.send(mimeMessage);
            log.info("Gönderilen Email" + mimeMessage);
            return "Email başarıyla gönderildi";

        } catch (Exception exception) {
            log.error("Alınan Hata" + exception);
            return "Email gönderilirken hata oluştu";
        }
    }
}
