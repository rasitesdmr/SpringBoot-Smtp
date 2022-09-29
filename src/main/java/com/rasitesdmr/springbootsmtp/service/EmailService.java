package com.rasitesdmr.springbootsmtp.service;

import com.rasitesdmr.springbootsmtp.model.Email;

public interface EmailService {

    // Alıcıya basit bir metin e-postası göndermekl için kullanılır.
    String sendSimpleMail(Email email);

    // Alıcıya bir ek ile birlikte bir e-posta göndermek için kullanılır.
    String sendMailWithAttachment(Email email);


}
