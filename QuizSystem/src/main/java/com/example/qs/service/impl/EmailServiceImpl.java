package com.example.qs.service.impl;

import com.example.qs.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;

/**
 * Created by phoebegl on 2017/12/7.
 */
@Service
@Transactional
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
