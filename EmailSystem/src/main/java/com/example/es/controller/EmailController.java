package com.example.es.controller;

import com.example.es.model.Email;
import com.example.es.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/12/7.
 */
@RestController
public class EmailController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EmailService emailService;

    @Value("${spring.application.name}")
    private String applicationName;


    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @PostMapping(value = "/sendEmail")
    public void sendMail(@RequestBody Email email) {
        emailService.sendMessage(email.getSubject(),email.getContent(),email.getReceiver());
    }

}
