package com.rasitesdmr.springbootsmtp.controller;

import com.rasitesdmr.springbootsmtp.model.Email;
import com.rasitesdmr.springbootsmtp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    // Basit bir email gönderme
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Email email){
        return emailService.sendSimpleMail(email);
    }

    // Özelleştirilmiş email gönderme
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody Email email){
        return emailService.sendMailWithAttachment(email);
    }
}
