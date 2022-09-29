package com.rasitesdmr.springbootsmtp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Email {

    private String recipient;   // Alıcının email'i
    private String messageBody;  // Gönderilen mesaj
    private String subject;      // Basit mi yoksa özelleştirilmiş email'mi
    private String attachment;    // resim yolu ("C:/github.JPG")

}
