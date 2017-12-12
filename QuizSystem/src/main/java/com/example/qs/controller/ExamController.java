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
    public ResponseInfo saveStudents(@RequestBody List<User> users) {
        ResponseInfo responseBody = new ResponseInfo();

        int res = userService.saveStudents(users);
        responseBody.setResponseStatus(res);
        return responseBody;
    }

    @PostMapping(value = "/generateQuiz")
    public ResponseInfo generateQuiz(@RequestBody Quiz quiz) {
        ResponseInfo responseBody = new ResponseInfo();
        examService.generateQuiz(quiz);
        return responseBody;
    }

    @GetMapping(value = "/generatePaper")
    public Map<String, Object> generatePaper(@RequestParam String authcode) {
        return examService.generatePaper(authcode);
    }

    @PostMapping(value = "/saveAnswerSheet")
    public ResponseInfo saveAnswer(@RequestBody Map<String, Object> choices) {
        ResponseInfo responseBody = new ResponseInfo();

        examService.saveAnswer(choices);
        return responseBody;
    }

    @GetMapping(value = "/getAnswers")
    public Map<String, Object> getAnswers(@RequestParam int examid,
                                          @RequestParam int userid) {
        return examService.getAnswers(examid, userid);
    }
}
