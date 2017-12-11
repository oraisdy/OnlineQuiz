package com.example.es.util;

import com.example.es.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;
import java.util.Properties;

/**
 * Created by phoebegl on 2017/12/7.
 */
@Configuration
public class EmailUtil {

    private @Value("${email.host}") String emailHost;
    private @Value("${email.username}") String userName;
    private @Value("${email.password}") String password;
    private @Value("${email.smtp.auth}") String mailAuth;

    @Bean("mailSender")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setProtocol("smtp");
        mailSender.setHost(emailHost);
        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        Properties property = new Properties();
        property.setProperty("mail.smtp.auth",mailAuth);
        mailSender.setJavaMailProperties(property);
        return mailSender;

    }
}
