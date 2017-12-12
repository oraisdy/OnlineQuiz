package com.example.qs.controller;

import com.example.qs.entity.Quiz;
import com.example.qs.entity.ResponseInfo;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    public List<User> saveStudents(@RequestBody MultipartFile file) {
        ResponseInfo responseBody = new ResponseInfo();

        List<User> res = userService.saveStudents(file);
        return res;
    }

    @PostMapping(value = "/generateQuiz")
    public ResponseInfo generateQuiz(@RequestBody Quiz quiz) {
        return examService.generateQuiz(quiz);
    }

    @GetMapping(value = "/generatePaper")
    public Map<String, Object> generatePaper(@RequestParam String authcode) {
        return examService.generatePaper(authcode);
    }

    @PostMapping(value = "/saveAnswerSheet")
    public ResponseInfo saveAnswer(@RequestBody Map<String, Object> choices) {
        return examService.saveAnswer(choices);
    }

    @GetMapping(value = "/getAnswers")
    public Map<String, Object> getAnswers(@RequestParam int examid,
                                          @RequestParam int userid) {
        return examService.getAnswers(examid, userid);
    }
}
