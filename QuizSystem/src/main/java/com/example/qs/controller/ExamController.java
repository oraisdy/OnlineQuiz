package com.example.qs.controller;

import com.example.qs.entity.Quiz;
import com.example.qs.entity.Tag;
import com.example.qs.entity.User;
import com.example.qs.service.ExamService;
import com.example.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/7.
 */
@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostMapping(value = "/saveStudents")
    public void saveStudents(@RequestBody List<User> users) {
        userService.saveStudents(users);
    }

    @PostMapping(value = "/generateQuiz")
    public void generateQuiz(@RequestBody Quiz quiz) {
        examService.generateQuiz(quiz);
    }

}
