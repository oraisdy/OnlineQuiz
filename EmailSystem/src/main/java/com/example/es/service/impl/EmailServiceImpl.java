package com.example.es.service.impl;

import com.example.es.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by phoebegl on 2017/12/7.
 */
@Component
public class EmailServiceImpl implements EmailService {

    private @Value("${email.from}") String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public int sendMessage(String subject, String Message, String candidates) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setTo(candidates);
                messageHelper.setFrom(from);
                messageHelper.setSubject(subject);
                messageHelper.setText(Message, true);
            }
        };
        mailSender.send(preparator);
        return 0;
    }

}
